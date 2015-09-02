package com.exposit.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.exposit.domain.model.ProductCatalogUnit;
import com.exposit.domain.service.ProductCatalogUnitService;

@Component
public class StringToProductCatalogUnitConverter implements
		Converter<String, ProductCatalogUnit> {

	@Autowired
	private ProductCatalogUnitService productCatalogUnitService;

	@Override
	public ProductCatalogUnit convert(String id) {
		return productCatalogUnitService.findById(Integer.valueOf(id));
	}

}
