package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.*;
import com.example.scoobydoo.repos.InventoryRepo;
import com.example.scoobydoo.repos.ItemRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public OrderService(TrapCaseService trapCaseService, MonsterService monsterService, BankAccountService bankAccountService, InventoryService inventoryService, InventoryRepo inventoryRepo, ItemRepo itemRepo, InvestigatorService investigatorService) {
        this.trapCaseService = trapCaseService;
        this.monsterService = monsterService;
        this.bankAccountService = bankAccountService;
        this.inventoryService = inventoryService;
        this.inventoryRepo = inventoryRepo;
        this.itemRepo = itemRepo;
        this.investigatorService = investigatorService;
    }

    public List<Item> inventorySelection(long inv_id, long c_id) {
        Investigator investigator = investigatorService.getInvestigatorById(inv_id);
        Crime crime = investigator.getCrimes().stream().filter(x -> x.getId() == c_id).findFirst().get();
        List<MonsterType> monster_types = crime.getCriminalCases().stream().map(x -> x.getMonster().getMonsterType()).collect(Collectors.toList());
        List<Item> items = itemRepo.findAll();
        List<Item> col = items.stream().filter(x -> x.getMonsterTypes().stream().anyMatch(monster_types::contains)).collect(Collectors.toList());
        col.sort(Comparator.comparing(Item::getTrapItems, (s1, s2) -> s1.stream().map(x -> x.getTrapCase().getUsefulness()).max(Integer::compareTo).get() >
                s2.stream().map(x -> x.getTrapCase().getUsefulness()).max(Integer::compareTo).get() ? 1 : 0));
        if (col.size() > 10)
            col = col.stream().limit(10).collect(Collectors.toList());
        return col;
    }

    public boolean makeOrder(long inv_id, List<Long> ids) {
        double sum = ids.stream().mapToDouble(x -> x).sum();
        BankAccount ba = bankAccountService.findBankAccountByOwner(investigatorService.getInvestigatorById(inv_id));
        if (ba.getBalance() < sum)
            return false;
        List<Optional<Item>> collect = ids.stream().map(x -> itemRepo.findById(x)).collect(Collectors.toList());
        putToInventory(LocalDateTime.now(), collect);
        bankAccountService.setBalance(ba.getId(), (float) (ba.getBalance() - sum));
        return true;
    }


    private void putToInventory(LocalDateTime date, List<Optional<Item>> inv) {
        executor.schedule(() -> {
            List<Inventory> collect = inv.stream().map(x -> {
                Inventory inventory = new Inventory();
                inventory.setDepositDate(date);
                inventory.setItem(x.get());
                inventory.setAvailable(true);
                return inventory;
            }).collect(Collectors.toList());
            inventoryRepo.saveAll(collect);
        }, 3, TimeUnit.SECONDS);
    }
}
