package com.repo;

import com.model.Yahoo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YahooRepo extends JpaRepository<Yahoo, Long> {

}
