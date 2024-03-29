package com.lbaburic.learning.cardcreator.repository;

import com.lbaburic.learning.cardcreator.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Integer> {

    @Query("SELECT c FROM CardEntity c WHERE c.oib = :oib")
    Optional<CardEntity> findByOib(@Param("oib") String oib);
}
