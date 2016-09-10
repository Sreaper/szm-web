package com.lm.mapping;

import java.util.List;

import com.lm.entity.UserEntity;

public interface UserMapper {
	
	UserEntity getUserEntityById(String userId);
	
	List<UserEntity> getUserEntities();
	
	int insertUser(UserEntity userEntity);
}
