package com.exposit.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.exposit.domain.model.Order;
import com.exposit.domain.service.OrderService;

@Component
public class StringToOrderConverter implements Converter<String, Order> {

	@Autowired
	private OrderService orderService;

	@Override
	public Order convert(String orderId) {
		return orderService.getOrderById(Integer.parseInt(orderId));
	}

}
