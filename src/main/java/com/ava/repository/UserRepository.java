package com.ava.repository;

import com.ava.entity.SearchRecord;
import com.ava.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findUserByEmail(String email);

	Optional<User> findUserById(Long id);

	Boolean existsUsersByEmailAndIdNotLike(String email, Long id);

	@Query(value ="SELECT * FROM users u " +
			"WHERE " +
			"(u.first_name LIKE %:#{#searchRecord.firstName}% OR :#{#searchRecord.firstName} IS NULL) " +
			"AND (u.last_name LIKE %:#{#searchRecord.lastName}% OR :#{#searchRecord.lastName} IS NULL) " +
			"AND (u.email LIKE %:#{#searchRecord.email}% OR :#{#searchRecord.email} IS NULL) " +
			"AND (u.address LIKE %:#{#searchRecord.address}% OR :#{#searchRecord.address} IS NULL) " +
			"AND (u.country LIKE %:#{#searchRecord.country}% OR :#{#searchRecord.country} IS NULL) " +
			"AND (u.role = ?1 OR ?1 IS NULL) ", nativeQuery = true)
	List<User> findBy(String role, @Param("searchRecord") SearchRecord searchRecord);

}
