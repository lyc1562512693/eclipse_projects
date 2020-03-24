package com.surfilter.boot.ssm.dao;

import org.apache.ibatis.annotations.Mapper;

import com.surfilter.boot.ssm.model.SmUser;

public interface SmUserMapper {

	SmUser selectSmUserById(Integer userId);
	
	void updateSmUser(SmUser user);
	
	void insertSmUser(SmUser user);
	
	void deleteSmUserById(Integer userId);
}
