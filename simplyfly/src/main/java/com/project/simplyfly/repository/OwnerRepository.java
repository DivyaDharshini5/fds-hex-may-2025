package com.project.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.simplyfly.model.Owner;

public interface OwnerRepository  extends JpaRepository<Owner,Integer>{
    @Query("SELECT o from Owner o where  o.user.username=?1")
	Owner getOwnerByUsername(String username);

}
