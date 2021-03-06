package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ItemRepo extends JpaRepository<Item, Long> {
    Item findItemByName(String name);
}
