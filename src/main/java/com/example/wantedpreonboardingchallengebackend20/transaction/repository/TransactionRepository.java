package com.example.wantedpreonboardingchallengebackend20.transaction.repository;

import com.example.wantedpreonboardingchallengebackend20.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
