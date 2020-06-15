package com.learning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learning.model.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer>{
//
//	@Query("select r.user from Role r inner join r.user where r.role_id = :roleId")
//	List<Rate> getUsersByRole(@Param("roleId") int roleId);

}
