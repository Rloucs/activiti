package com.activiti.service;

import java.util.Map;

import com.activiti.pojo.ReturnObject;

public interface ILoginService {
	
	public ReturnObject login(Map<String, String> map);
}
