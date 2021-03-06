package com.surfilter.boot.ssm.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surfilter.boot.component.scheduler.QuartzEating;
import com.surfilter.boot.ssm.model.SmUser;
import com.surfilter.boot.ssm.service.QuartzUserService;
import com.surfilter.boot.ssm.service.UserService;

@Controller
@RequestMapping(value = "/sm")
public class UserController {
	
	@Autowired    
	javax.sql.DataSource dataSource;
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected QuartzUserService quartzUserService;
	
	@ResponseBody
	@RequestMapping(value = "selectSmUserById.action",method = RequestMethod.POST)
	public SmUser selectSmUserById(@RequestBody Integer userId){
		//查看当前数据源
		System.out.println(dataSource.getClass());
		SmUser user = userService.selectSmUserById(userId);
		System.out.println(user);
		quartzUserService.addJob("jname"+user.getUserId(), "jgroup"+user.getUserId(), "tname"+user.getUserId(), "tgroup"+user.getUserId(), QuartzEating.class, "0/30 * * * * ?");
		return user;
	}
	@ResponseBody
	@RequestMapping(value = "updateSmUser.action",method = RequestMethod.POST)
	public void updateSmUser(SmUser smUser){
		userService.updateSmUser(smUser);
	}
	@ResponseBody
	@RequestMapping(value = "deleteSmUserById.action",method = RequestMethod.POST)
	public void deleteSmUserById(Integer userId){
		userService.deleteSmUserById(userId);
	}
	@ResponseBody
	@RequestMapping(value = "insertSmUser.action",method = RequestMethod.POST)
	public void insertSmUser(@RequestBody SmUser smUser){
		userService.insertSmUser(smUser);
	}
}
