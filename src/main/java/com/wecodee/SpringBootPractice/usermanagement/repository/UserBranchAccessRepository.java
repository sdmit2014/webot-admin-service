package com.wecodee.SpringBootPractice.usermanagement.repository;

import java.util.List;
import com.wecodee.SpringBootPractice.admin.repository.BaseRepository;
import com.wecodee.SpringBootPractice.usermanagement.model.UserBranchAccess;

public interface UserBranchAccessRepository extends BaseRepository<UserBranchAccess> {

	List<UserBranchAccess> getByUserId(String userId);

}
