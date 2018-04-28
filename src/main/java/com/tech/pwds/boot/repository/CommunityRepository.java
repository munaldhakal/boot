package com.tech.pwds.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.pwds.boot.model.Community;
@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

	List<Community> findAllByUserId(Long searchId);

}
