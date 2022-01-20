package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.Inventory;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface InventoryRepo extends PagingAndSortingRepository<Inventory, Long> {
    Page<Inventory> findAll(@NotNull Pageable pageable);
}
