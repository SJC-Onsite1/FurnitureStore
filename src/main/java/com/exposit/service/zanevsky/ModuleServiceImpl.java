package com.exposit.service.zanevsky;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exposit.domain.model.dobrilko.Provider;
import com.exposit.domain.model.dobrilko.RequestUnit;
import com.exposit.domain.model.dobrilko.ShipmentUnit;
import com.exposit.domain.model.dobrilko.StorageModuleUnit;
import com.exposit.domain.model.sorokin.Order;
import com.exposit.domain.model.zanevsky.Module;
import com.exposit.domain.model.zanevsky.ProductCatalogUnit;
import com.exposit.domain.model.zanevsky.ProductTemplate;
import com.exposit.domain.service.dobrilko.StorageModuleUnitService;
import com.exposit.domain.service.sorokin.UserService;
import com.exposit.domain.service.zanevsky.ModuleService;
import com.exposit.domain.service.zanevsky.OrderUnitService;
import com.exposit.domain.service.zanevsky.ProductCatalogUnitService;
import com.exposit.domain.service.zanevsky.ProductTemplateService;
import com.exposit.repository.dao.dobrilko.ProviderDao;
import com.exposit.repository.dao.zanevsky.ModuleDao;

@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleDao moduleRepository;

	@Autowired
	private ProviderDao providerRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderUnitService orderUnitService;
	@Autowired
	private ProductCatalogUnitService productCatalogUnitService;
	@Autowired
	private ProductTemplateService productTemplateService;

	@Autowired
	StorageModuleUnitService storageModuleUnitService;

	@Override
	public Module findById(int id) {
		return this.moduleRepository.findById(id);
	}

	@Override
	public List<Module> getModules(Provider provider) {
		return this.moduleRepository
				.getModules(providerRepository.findById(provider.getId()));
	}

	@Override
	public List<Module> getModules(ProductCatalogUnit productCatalogUnit) {
		return this.moduleRepository.getModules(productCatalogUnit);
	}

	@Override
	public Module getModule(ShipmentUnit shipmentUnit) {
		return this.moduleRepository.getModule(shipmentUnit);
	}

	@Override
	public Module getModule(RequestUnit requestUnit) {
		return this.moduleRepository.getModule(requestUnit);
	}

	@Override
	public Module getModule(StorageModuleUnit storageModuleUnit) {
		return this.moduleRepository.getModule(storageModuleUnit);
	}

	@Override
	public Module getModule(ProductTemplate template) {
		return this.moduleRepository.getModule(template);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public void deleteModuleFromProviderList(Integer id, Provider provider) {

		Module module = this.findById(id);
		Provider pr = providerRepository.findById(provider.getId());
		List<Module> modules = this.getModules(pr);

		List<Provider> providers = providerRepository.getProviders(module);

		modules.remove(module);

		providers.remove(pr);

		module.setProvider(providers);
		pr.setModules(modules);

		providerRepository.update(pr);
		moduleRepository.update(module);

		List<Provider> prs = providerRepository.getProviders(module);
		List<Module> mdls = moduleRepository.getModules(pr);

		return;
	}

	@Override
	public HashMap<Module, Integer> getAbsentProductTemplates(Order order) {

		List<ProductTemplate> productTemplates = new ArrayList<ProductTemplate>();

		HashMap<Module, Integer> returnMap = new HashMap<Module, Integer>();
		List<ProductCatalogUnit> catalogUnits = productCatalogUnitService
				.getProducts(order);
		for (ProductCatalogUnit productCatalogUnit : catalogUnits) {
			for (ProductTemplate productTemplate : productTemplateService
					.getProductTemplates(productCatalogUnit)) {
				Module module = this.getModule(productTemplate);
				Integer storageCount = storageModuleUnitService
						.getStorageModuleUnit(module).getCount();
				Integer templateCount = productTemplate.getCount();
				if (storageCount < templateCount) {
					returnMap.put(module, templateCount - storageCount);
				}

			}
		}
		return returnMap;

	}

	@Override
	public HashMap<Module, Integer> getMapOfModulesInOrder(Order order) {
		HashMap<Module, Integer> modules = new HashMap<Module, Integer>();

		for (ProductCatalogUnit product : productCatalogUnitService
				.getProducts(order)) {
			for (ProductTemplate template : productTemplateService
					.getProductTemplates(product)) {
				Module oneModule = this.getModule(template);
				if (modules.containsKey(oneModule)) {
					int modulesCount = modules.get(oneModule);
					modulesCount += template.getCount();
					modules.put(oneModule, modulesCount);
				} else
					modules.put(oneModule, template.getCount());
			}
		}

		return modules;
	}
}
