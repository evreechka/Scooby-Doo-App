package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.TrapCase;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TrapCaseRepo extends PagingAndSortingRepository<TrapCase, Long> {
    Page<TrapCase> findAll(@NotNull Pageable pageable);
}
