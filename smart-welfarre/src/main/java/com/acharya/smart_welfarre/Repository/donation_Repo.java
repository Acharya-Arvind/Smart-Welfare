package com.acharya.smart_welfarre.Repository;

import com.acharya.smart_welfarre.Model.Donation;
import com.acharya.smart_welfarre.Model.Government_Scheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface donation_Repo extends JpaRepository<Donation,Long> {

    @Query("SELECT d FROM Donation d WHERE d.id = :id")
    Optional<Donation> getDonationById(@Param("id") Long id);
    @Query(value = "SELECT * FROM Donation WHERE status= 'PENDING'", nativeQuery = true)
    List<Donation> getAllDonation();
    @Query(value = "SELECT * FROM Donation WHERE id=:ID", nativeQuery = true)
    Donation getDonation(@Param("ID") Long id);

    @Query(value = "SELECT donationname FROM Donation WHERE id=:ID", nativeQuery = true)
    String getDonationname(@Param("ID") Long id);

    @Query(value = "SELECT email FROM Donation WHERE id=:ID", nativeQuery = true)
    String getDonationmail(@Param("ID") Long id);

    @Transactional
    @Modifying // Add this annotation
    @Query(value = "UPDATE Donation SET status = DATE_FORMAT(CURDATE(), '%Y-%m-%d'), application_status = 'APPROVED' WHERE id = :ID", nativeQuery = true)
    void update_aprove(@Param("ID") Long id);

    @Transactional
    @Modifying // Add this annotation
    @Query(value = "UPDATE Donation SET status = DATE_FORMAT(CURDATE(), '%Y-%m-%d'), application_status = 'REJECTED' WHERE id = :ID", nativeQuery = true)
    void update_reject(@Param("ID") Long id);

    @Query(value = "SELECT * FROM Donation WHERE amount > 0;", nativeQuery = true)
    List<Donation> getdonations();

    @Transactional
    @Modifying
    @Query(value = "UPDATE Donation SET amount = amount - :amountToSubtract WHERE id = :donationId", nativeQuery = true)
    void subamount(@Param("donationId") Long donationId, @Param("amountToSubtract") Long amountToSubtract);

    @Query(value = "SELECT * FROM Donation;", nativeQuery = true)
    List<Donation> getentiredonation();

}
