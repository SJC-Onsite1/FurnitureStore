package com.exposit.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.exposit.domain.model.Module;
import com.exposit.domain.service.ModuleService;

@Component
public class StringToModuleConverter implements Converter<String, Module> {

	@Autowired
	private ModuleService moduleService;

	@Override
	public Module convert(String moduleId) {
		return moduleService.findById(Integer.parseInt(moduleId));
	}

}
