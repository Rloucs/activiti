package com.activiti.service;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.activiti.constant.ConstantVO;
import com.activiti.mapper.UserMapper;
import com.activiti.pojo.ReturnObject;
import com.activiti.pojo.User;
import com.activiti.util.RedisUtil;
@Service
public class LoginServiceImpl implements ILoginService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public ReturnObject login(Map<String, String> map) {
		// TODO Auto-generated method stub
		ReturnObject returnObj = new ReturnObject();
		String userName = map.get("userName");
		String password = map.get("password");
		
		User user = new User();
		if(StringUtils.isEmpty(userName)) {
			returnObj.setSuccess(ConstantVO.RETURN_BOOLEAN_FALSE);
			returnObj.setMessage("用户名为空！");
			return returnObj;
		}
		if(StringUtils.isEmpty(password)) {
			returnObj.setSuccess(ConstantVO.RETURN_BOOLEAN_FALSE);
			returnObj.setMessage("密码为空！");
			return returnObj;
		}
		
		user.setUserName(userName);
		user.setPassword(password);
		User reUser = new User();
		try {
			reUser = userMapper.selectUserByParam(user);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnObj.setSuccess(ConstantVO.RETURN_BOOLEAN_FALSE);
			returnObj.setMessage("系统繁忙，请稍后再试！");
			return returnObj;
		}
		if(Objects.equals(reUser, null)) {
			returnObj.setSuccess(ConstantVO.RETURN_BOOLEAN_FALSE);
			returnObj.setMessage("用户名或者密码不正确！");
		}else{
			returnObj.setSuccess(ConstantVO.RETURN_BOOLEAN_TRUE);
			returnObj.setMessage("OK");
			returnObj.setData(reUser);
		}
		return returnObj;
	}

}
