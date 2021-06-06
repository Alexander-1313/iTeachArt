package com.itechart.stock.repository;

import com.itechart.stock.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    Subscribe findByType(String type);
}
