package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.*;
import com.example.scoobydoo.repos.InventoryRepo;
import com.example.scoobydoo.repos.ItemRepo;
import com.example.scoobydoo.repos.ProfileRepo;
import com.example.scoobydoo.repos.TrapCaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * подобрать ловушку -> makeOrder -> списать средства -> putToInventory on time
 */


@Service
public class OrderService {
    private TrapCaseService trapCaseService;
    private MonsterService monsterService;
    private BankAccountService bankAccountService;
    private InventoryService inventoryService;
    private InventoryRepo inventoryRepo;
    private ItemRepo itemRepo;
    private InvestigatorService investigatorService;
    private CriminalCaseService criminalCaseService;
    @Autowired
    private TrapCaseRepo trapCaseRepo;
    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    ProfileRepo profileRepo;

    public OrderService(TrapCaseService trapCaseService, MonsterService monsterService, BankAccountService bankAccountService, InventoryService inventoryService, InventoryRepo inventoryRepo, ItemRepo itemRepo, InvestigatorService investigatorService, CriminalCaseService criminalCaseService) {
        this.trapCaseService = trapCaseService;
        this.monsterService = monsterService;
        this.bankAccountService = bankAccountService;
        this.inventoryService = inventoryService;
        this.inventoryRepo = inventoryRepo;
        this.itemRepo = itemRepo;
        this.investigatorService = investigatorService;
        this.criminalCaseService = criminalCaseService;
    }

    public Map<String, Integer> inventorySelection(long cc_id) {
        CriminalCase criminal_case = criminalCaseService.getCriminalCaseById(cc_id);
        MonsterType monsterType = criminal_case.getMonster().getMonsterType();
        List<Item> items = itemRepo.findAll();
        List<Item> col = items.stream().filter(x -> x.getMonsterTypes().stream().anyMatch(y -> y.equals(monsterType))).collect(Collectors.toList());
        if (col.size() > 10)
            col = col.stream().limit(10).collect(Collectors.toList());
        return col.stream().collect(Collectors.toMap(Item::getName, y -> ThreadLocalRandom.current().nextInt(5)));
    }

    public boolean makeOrder(UserDetails userDetails, String name, Map<String, String> itemCount, Long criminal_case_id) {
        long inv_id = profileRepo.findProfileByUsername(userDetails.getUsername()).getUser().getId();
        Stream<Item> itemStream = itemCount.keySet().stream().map(x -> itemRepo.findItemByName(x));
        List<Item> items = itemStream.collect(Collectors.toList());
        double sum = itemStream.mapToDouble(Item::getCost).sum();
        BankAccount ba = bankAccountService.findBankAccountByOwner(investigatorService.getInvestigatorById(inv_id));
        if (ba.getBalance() < sum)
            return false;
        TrapCase trapCase = new TrapCase();
        trapCase.setCriminalCase(criminalCaseService.getCriminalCaseById(criminal_case_id));
        trapCase.setName(name);
        trapCase.setSelected(true);
        trapCase.setUsefulness((int)Math.round(Math.random() * 10));
        trapCaseRepo.save(trapCase);
        bankAccountService.setBalance(ba.getId(), (float) (ba.getBalance() - sum));
        putToInventory(LocalDateTime.now(), items);
        return true;
    }


    private void putToInventory(LocalDateTime date, List<Item> inv) {
        executor.schedule(() -> {
            List<Inventory> collect = inv.stream().map(x -> {
                Inventory inventory = new Inventory();
                inventory.setDepositDate(date);
                inventory.setItem(x);
                inventory.setAvailable(true);
                return inventory;
            }).collect(Collectors.toList());
            inventoryRepo.saveAll(collect);
        }, 3, TimeUnit.SECONDS);
    }
}
