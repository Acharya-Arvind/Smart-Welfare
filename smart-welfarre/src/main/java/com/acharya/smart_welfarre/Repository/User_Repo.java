package com.acharya.smart_welfarre.Repository;

import com.acharya.smart_welfarre.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface User_Repo extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM User WHERE email = :email", nativeQuery = true)
    User findByemail(@Param("email") String email);

    @Query(value = "SELECT * FROM User", nativeQuery = true)
    List<User> fetchalluser();

    @Transactional
    @Modifying
    @Query(value = "UPDATE User SET status = CASE status WHEN 'VERIFIED USER' THEN 'BANNED' WHEN 'BANNED' THEN 'VERIFIED USER' ELSE status END WHERE id = ?;", nativeQuery = true)
    void changestatus(@Param("ID") Long id);

    @Query(value = "SELECT password FROM User WHERE email = :mail", nativeQuery = true)
    String checkpass(@Param("mail") String email);

    @Query(value = "SELECT name FROM User WHERE email = :mail", nativeQuery = true)
    String getusername(@Param("mail") String email);

    @Query(value = "SELECT COUNT(*) FROM User WHERE email = :mail", nativeQuery = true)
    int checkuserexist(@Param("mail") String email);
}
