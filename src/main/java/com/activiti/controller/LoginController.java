package com.activiti.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.activiti.constant.ConstantVO;
import com.activiti.pojo.ReturnObject;
import com.activiti.pojo.User;
import com.activiti.service.ILoginService;
import com.activiti.util.ImageYZM;
import com.activiti.util.RedisUtil;

@Controller
public class LoginController {
	
	@Resource
	private ILoginService loginService;
	
	/*@Autowired
	private Producer producer;*/
//	@Autowired
//	private RedisUtil redisUtil;
	
	@RequestMapping("/service/login")
	@ResponseBody
	public ReturnObject login(HttpServletRequest req,HttpServletResponse resp) {
		ReturnObject returnObj = new ReturnObject();
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
//		String yzm = req.getParameter("yzm");
		Map<String, String> map = new HashMap<String,String>(3);
		map.put("userName", userName);
		map.put("password", password);
//		map.put("yzm", yzm);
		returnObj  = loginService.login(map);
		Cookie userCookie = new Cookie(ConstantVO.ACTIVITI_USER_ID, String.valueOf(((User)returnObj.getData()).getId()));
		resp.addCookie(userCookie);
		return returnObj;
	}
	
	@RequestMapping("service/getYZM")
//	@ResponseBody
	public void getYZM(HttpServletRequest req , HttpServletResponse resp) throws IOException {
		ImageYZM image = new ImageYZM();
		String text = image.getYZM(resp);
		String date = req.getParameter("date");
		boolean flag = RedisUtil.set(ConstantVO.LOGIN_YZM_FLAG+"_"+date, text);
		/*resp.setHeader("Cache-Control", "no-store, no-cache");
		resp.setContentType("image/jpeg");
		String text = producer.createText();
		BufferedImage bfi = producer.createImage(text);
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(bfi, "jpg", out);*/
	}
	
	@RequestMapping("/service/test")
	@ResponseBody
	public ReturnObject commonTest() {
		ReturnObject returnObj = new ReturnObject();
		returnObj.setSuccess(ConstantVO.RETURN_BOOLEAN_TRUE);
		returnObj.setMessage("common test is OK！");
		return returnObj;
	}
	
	@RequestMapping("/service/matchCode")
	@ResponseBody
	public ReturnObject matchCode(HttpServletRequest req, HttpServletResponse resp) {
		ReturnObject returnObj = new ReturnObject();
		String date = req.getParameter("date");
		String code = req.getParameter("code");
		String str =(String) RedisUtil.get(ConstantVO.LOGIN_YZM_FLAG+"_"+date);
		
		if(!str.toUpperCase().equals(code.toUpperCase())) {
			returnObj.setSuccess(ConstantVO.RETURN_BOOLEAN_FALSE);
			returnObj.setMessage("验证码错误！");
			return returnObj;
		}
		returnObj.setSuccess(ConstantVO.RETURN_BOOLEAN_TRUE);
		return returnObj;
	}
	
	@RequestMapping("/service/getLoginInfo")
	@ResponseBody
	public ReturnObject getLoginInfo(HttpServletRequest req, HttpServletResponse resp) {
		ReturnObject returnObj = new ReturnObject();
		Cookie[] cooks = req.getCookies();
		String str="";
		for(Cookie co:cooks) {
			if(co.getName().equals(ConstantVO.ACTIVITI_USER_ID)) {
				str=co.getValue();
			}
		}
		returnObj.setSuccess(ConstantVO.RETURN_BOOLEAN_TRUE);
		returnObj.setData(str);
		return returnObj;
	}
	
}
