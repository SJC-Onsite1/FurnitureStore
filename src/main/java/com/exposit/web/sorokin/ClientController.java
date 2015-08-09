package com.exposit.web.sorokin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exposit.domain.model.sorokin.Order;
import com.exposit.domain.model.sorokin.User;
import com.exposit.domain.service.sorokin.OrderService;
import com.exposit.domain.service.sorokin.UserService;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private UserService userRepository;

	@Autowired
	private OrderService orderRepository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String initClient(HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Order> orders = orderRepository.getOrders(user);
		user.setOrders(orderRepository.getOrders(user));
		session.setAttribute("orderList", orders);// TODO 2 params of
													// orderList???
		return "redirect:profile/";

	}

	@RequestMapping(value = "profile/", method = RequestMethod.GET)
	public String showProfile() {
		return "client.profile";
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String showOrders() {
		return "client.orders";
	}

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
	public String showOrder(@PathVariable("id") Integer id, Model model) {
		Order order = orderRepository.getOrderById(id);
		model.addAttribute("order", order);
		return "client.order";
	}
}