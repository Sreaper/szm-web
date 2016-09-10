package com.lm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lm.entity.UserEntity;
import com.lm.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.net.URLEncoder;

@Controller
public class UserController {

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//http://localhost:8085/user_cms/userController/1/showUser.do
	@RequestMapping("/{id}/showUser")
	public String showUser(@PathVariable String id, ModelMap modelMap ,HttpServletRequest request) {
		UserEntity u = userService.getUserEntityById(id);
	    String temp ="艺人";
		if(u == null){
			u = new UserEntity();
			u.setEmail("");
			u.setUserId("");
		}
		modelMap.put("user", u);
		return "showUser";
	}
	
	//http://localhost:8085/user_cms/userController/showUser.do?id=1
	@RequestMapping("showUser")
	public String showUserEntity(String id, ModelMap modelMap,HttpServletRequest request) {
		UserEntity u = userService.getUserEntityById(id);
		if(u == null){
			u = new UserEntity();
			u.setEmail("");
			u.setUserId("");
		}
		modelMap.put("user", u);
		return "showUser";
	}
	
	@RequestMapping("/showUserExample")
	public String showUsers(Model model){
		return "redirect:/1/showUser.do";
	}
	
    @RequestMapping("/userList")
    public String list(ModelMap model) {
        model.put("users", userService.getUserEntities());

        //model.put("escapseStr", HtmlUtils.htmlEscape("song<sss>zhimao"));
        model.put("escapseStr", "song<sss>zhimao");
        return "list";
    }
    
    @RequestMapping("/user/{id}")
    public String detail(@PathVariable(value = "id") String id, ModelMap model) {
        model.put("user", userService.getUserEntityById(id));
        return "detail";
    }
    @ResponseBody
    @RequestMapping("/jsonp/flightResult")
    public String flightResult(String code,String callback, ModelMap model,HttpServletResponse response) throws Exception{
        CookieUtil.setCookie("name", URLEncoder.encode("songzhimao", "UTF-8"), ".szm.com", "/", response);
        String result = callback+"({\"code\": \"CA1998\",\"price\": 1780,\"tickets\": 6})";

//        result = "\""+ result+"\"";
//        response.getWriter().print(result);
//        return result.toString();
        String temp="sz\"\"m";
        return result;
    }
    @RequestMapping("/jsonp/{1}")
    public String demo() throws Exception{
        return "jsonp/demo1";
    }

}