package com.PrestamosPrima.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PrestamosPrima.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
