package com.service;


import com.model.Yahoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YahooRepository extends JpaRepository<Yahoo, Long> {


}
