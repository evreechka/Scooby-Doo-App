package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.*;
import com.example.scoobydoo.repos.InventoryRepo;
import com.example.scoobydoo.repos.ItemRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    public List<Item> makeOrder(long id) {
        Investigator investigator = investigatorService.getInvestigatorById(id);
        List<MonsterType> monster_types = investigator.getCrimes().stream().flatMap(x -> x.getCriminalCases().stream()).map(x -> x.getMonster().getMonsterType()).collect(Collectors.toList());
        List<Item> items = itemRepo.findAll();
        List<Item> col = items.stream().filter(x -> x.getMonsterTypes().stream().anyMatch(monster_types::contains)).collect(Collectors.toList());
        col.sort(Comparator.comparing(Item::getTrapItems, (s1, s2) -> s1.stream().map(x -> x.getTrapCase().getUsefulness()).max(Integer::compareTo).get() >
                s2.stream().map(x -> x.getTrapCase().getUsefulness()).max(Integer::compareTo).get() ? 1 : 0));
        BankAccount bank_acc = bankAccountService.findBankAccountByOwner(investigator);
        int sum = 0;
        List<Item> inv = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (sum < bank_acc.getBalance() && col.size() < i)
                inv.add(col.get(0));
        }
        bankAccountService.deleteBankAccount(bank_acc.getId());
        bankAccountService.createAccountWithBalance(investigator, bank_acc.getBalance() - sum);
        putToInventory(LocalDateTime.now().plusDays(3), inv);
        System.out.println("yes");
        return inv;
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
        System.out.println("da");
    }
}
