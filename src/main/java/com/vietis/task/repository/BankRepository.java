package com.vietis.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vietis.task.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {

}
