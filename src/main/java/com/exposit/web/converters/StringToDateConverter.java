package com.exposit.web.converters;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDateConverter implements Converter<String, Date> {

	@SuppressWarnings("deprecation")
	@Override
	public Date convert(String dateString) {
		Date date = new Date();
		String[] elements = dateString.split("/");
		date.setMonth(Integer.parseInt(elements[0]));

		date.setDate(Integer.parseInt(elements[1]));

		date.setYear(Integer.parseInt(elements[2]));

		return date;

	}
}
