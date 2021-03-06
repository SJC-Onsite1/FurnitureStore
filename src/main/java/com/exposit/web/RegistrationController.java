package com.exposit.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.validation.Valid;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exposit.domain.exceptions.DuplicateUserAccountException;
import com.exposit.domain.model.Client;
import com.exposit.domain.model.RoleType;
import com.exposit.domain.service.BonusService;
import com.exposit.domain.service.MailService;
import com.exposit.domain.service.RoleService;
import com.exposit.domain.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private BonusService bonusService;

	@Autowired
	private MailService mailService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String showRegisterForm(Model model) {
		model.addAttribute("client", new Client());
		return "register";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String doRegistration(@Valid Client client, BindingResult result,
			RedirectAttributes redirectAttributes, @RequestParam(
					value = "image") MultipartFile avatar) throws SQLException,
			IOException {
		String resultView = "redirect:/login";
		if (result.hasErrors()) {
			return "register";
		}
		registerNewClient(client, avatar);
		redirectAttributes.addFlashAttribute("new_client", client);
		return resultView;
	}

	private void registerNewClient(Client client, MultipartFile avatar)
			throws IOException, SQLException {
		String password = RandomStringUtils.randomAlphabetic(8);
		String cryptedPassword = encoder.encode(password);
		client.setTotalSpent(0.0);
		client.setRole(roleService.getRoleByRoleType(RoleType.CLIENT));
		client.setBonus(bonusService.getCurrentDefaultBonus());
		client.setPassword(cryptedPassword);
		try {
			client.setAvatar(avatar.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IOException();
		}

		try {
			userService.createNewUser(client);
		} catch (Exception e) {
			throw new DuplicateUserAccountException(
					"Such Login or Email exists in database! Try again");
		}
		try {
			mailService.sendRegistrationMail(client.getLogin(), password,
					client.getEmail());
		} catch (Exception e) {
			Logger.getLogger(mailService.getClass()).info(
					"Email sending error!");
		}
		Logger.getLogger(Client.class).error("New user registered!");

	}
}
