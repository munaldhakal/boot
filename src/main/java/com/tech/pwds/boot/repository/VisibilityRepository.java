package com.tech.pwds.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.pwds.boot.model.UserVisibility;
import com.tech.pwds.boot.utilities.VisibilityStatus;
@Repository
public interface VisibilityRepository extends JpaRepository<UserVisibility, Long> {

	List<UserVisibility> findAllByStatus(VisibilityStatus on);
	
}
