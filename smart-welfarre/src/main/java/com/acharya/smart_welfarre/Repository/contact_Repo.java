package com.acharya.smart_welfarre.Repository;

import com.acharya.smart_welfarre.Model.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface contact_Repo extends JpaRepository<ContactUs,Long> {
}
