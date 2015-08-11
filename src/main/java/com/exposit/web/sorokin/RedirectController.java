package com.exposit.web.sorokin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exposit.domain.model.sorokin.User;
import com.exposit.domain.service.sorokin.UserService;

@Controller
@RequestMapping("/redirector")
public class RedirectController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String redirect(HttpSession session, Authentication auth) {
		User user = (User) userService.findUserByName(auth.getName());
		Authentication auth1 = auth;
		return "redirect:/" + user.getRole().getName().toString().toLowerCase()+ "/";
	}
}
