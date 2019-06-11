package com.busyqa.crm.repo;

import com.busyqa.crm.model.PaymentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentPlanRepository extends JpaRepository<PaymentPlan, Long> {
        Optional<PaymentPlan> findByname(String name);
        List<PaymentPlan> findAll();
}
