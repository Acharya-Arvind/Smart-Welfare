package com.acharya.smart_welfarre.Repository;

import com.acharya.smart_welfarre.Model.Government_Scheme;
import com.acharya.smart_welfarre.Model.Schemes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface g_scheme_Repo extends JpaRepository<Government_Scheme,Long> {
    @Query(value = "SELECT * FROM Government_Scheme WHERE usermail = :email", nativeQuery = true)
    List<Government_Scheme> findByemail(@Param("email") String email);
    @Query(value = "SELECT * FROM Government_Scheme WHERE status= 'PENDING'", nativeQuery = true)
    List<Government_Scheme> get_non_applied();

    @Query(value = "SELECT scheme_name FROM Government_Scheme WHERE id=:ID", nativeQuery = true)
    String getschemename(@Param("ID") Long id);

    @Query(value = "SELECT usermail FROM Government_Scheme WHERE id=:ID", nativeQuery = true)
    String getusermail(@Param("ID") Long id);

    @Transactional
    @Modifying // Add this annotation
    @Query(value = "UPDATE Government_Scheme SET status = DATE_FORMAT(CURDATE(), '%Y-%m-%d'), application_status = 'APPROVED' WHERE id = :ID", nativeQuery = true)
    void update_aprove(@Param("ID") Long id);

    @Transactional
    @Modifying // Add this annotation
    @Query(value = "UPDATE Government_Scheme SET status = DATE_FORMAT(CURDATE(), '%Y-%m-%d'), application_status = 'REJECTED' WHERE id = :ID", nativeQuery = true)
    void update_reject(@Param("ID") Long id);

    @Query(value = "SELECT * FROM Government_Scheme", nativeQuery = true)
    List<Government_Scheme> getschemes();
}
