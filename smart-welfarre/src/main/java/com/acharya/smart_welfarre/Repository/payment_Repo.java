package com.acharya.smart_welfarre.Repository;

import com.acharya.smart_welfarre.Model.Donation;
import com.acharya.smart_welfarre.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface payment_Repo extends JpaRepository<Payment,Long> {

    @Query(value = "SELECT * FROM Payment", nativeQuery = true)
    List<Payment> getAlltrans();

    @Query(value = "SELECT * FROM Payment WHERE usermail=:user", nativeQuery = true)
    List<Payment> getfewtrans(@Param("user") String user);
}
