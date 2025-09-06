package com.acharya.smart_welfarre.Repository;

import com.acharya.smart_welfarre.Model.Donation;
import com.acharya.smart_welfarre.Model.Schemes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface scheme_Repo extends JpaRepository<Schemes,Long> {
    @Query(value = "SELECT * FROM Schemes", nativeQuery = true)
    List<Schemes> getAllscheme();

}
