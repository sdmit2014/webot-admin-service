package com.wecodee.SpringBootPractice.usermanagement.repository;

import java.awt.print.Pageable;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wecodee.SpringBootPractice.admin.repository.BaseRepository;
import com.wecodee.SpringBootPractice.usermanagement.model.User;

@Repository
public interface UsersRepository extends BaseRepository<User> {

	User getByUserId(String userId);

	@Transactional
	@Modifying()
	@Query("update User set password=:pwd, chgPassFlg=1 where userId=:userId")
	public int resetPassword(@Param("pwd") String userPwd, @Param("userId") String userId);

	@Transactional
	@Modifying()
	@Query("update User set noOfAttempts=:noOfAttempts where userId=:userId")
	public int updateNoOfAttempts(@Param("noOfAttempts") int noOfAttempts, @Param("userId") String userId);

	@Transactional
	@Modifying()
	@Query("update User set chgPassFlg=0, password=:newpwd ,validateDate=:validateDate, loginStatus=0  where userId=:uid")
	public int changePassword(@Param("uid") String userId, @Param("newpwd") String newPassword,
			@Param("validateDate") Date validateDate);

	@Transactional
	@Modifying()
	@Query("update User set approved=:approve where userId=:userId")
	public int approve(@Param("approve") String approve, @Param("userId") String userId);

	@Transactional
	@Modifying
	@Query("update User set recordStatus='D' where userId=:userId")
	public int invalidateUser(@Param("userId") String userId);

	@Transactional
	@Modifying
	@Query("update User set pendingApproval=:pendingApproval where userId=:uid")
	public int updatePendingApproval(@Param("uid") String userId, @Param("pendingApproval") boolean pendingApproval);

	public Page<User> findByPendingApproval(boolean pendingApproval, Pageable pageable);

	public User getByUserIdAndApproved(String userId, Boolean approved);

}
