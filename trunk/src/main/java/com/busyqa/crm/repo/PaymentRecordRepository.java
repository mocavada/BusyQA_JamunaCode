package com.busyqa.crm.repo;

import com.busyqa.crm.model.ClientStatus;
import com.busyqa.crm.model.PaymentRecord;
import com.busyqa.crm.model.Team;
import com.busyqa.crm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  PaymentRecordRepository extends JpaRepository<PaymentRecord, Long> {

    List<PaymentRecord> findByTeam(Team team);
    List<PaymentRecord> findByClientStatus(ClientStatus clientStatus);
    Optional<PaymentRecord> findByUser(User user);
    List<PaymentRecord> findAll();
    PaymentRecord save(PaymentRecord paymentRecord);
}
