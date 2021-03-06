package com.exposit.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.exposit.domain.model.Module;
import com.exposit.domain.model.Provider;
import com.exposit.domain.model.Request;
import com.exposit.domain.model.Shipment;
import com.exposit.domain.service.ModuleService;
import com.exposit.domain.service.ProviderService;
import com.exposit.repository.dao.ProviderDao;

@Service
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private ProviderDao providerDao;
	@Autowired
	private ModuleService moduleService;

	@Transactional
	@Override
	public Integer saveProvider(Provider provider) {
		return providerDao.save(provider);
	}

	@Transactional
	@Override
	public void deleteProvider(Integer id) {
		providerDao.delete(id);

	}

	@Override
	public Provider getProviderByName(String providerName) {
		return providerDao.getProviderByName(providerName);
	}

	
	@Override
	public List<Provider> getProviders() {
		return providerDao.findAll();
	}

	
	@Override
	public Provider getProviderById(Integer id) {
		return providerDao.findById(id);
	}

	@Transactional
	@Override
	public void updateProvider(Provider provider) {
		providerDao.update(provider);
	}

	
	@Override
	public Provider getProvider(Request request) {
		return providerDao.getProvider(request);
	}

	
	@Override
	public Provider getProvider(Shipment shipment) {
		return providerDao.getProvider(shipment);
	}

	@Override
	public List<Provider> getProviders(Module module) {
		return providerDao.getProviders(module);
	}

	@Override
	public void setChangedFields(Provider loggedProvider,
			Provider editedProvider, MultipartFile avatar) {
		loggedProvider.setEmail(editedProvider.getEmail());
		loggedProvider.setProviderName(editedProvider.getProviderName());
		loggedProvider
				.setPassword(encoder.encode(editedProvider.getPassword()));
		loggedProvider.setPhone(editedProvider.getPhone());
		loggedProvider.setZipCode(editedProvider.getZipCode());
		if (avatar != null) {
			try {
				loggedProvider.setAvatar(avatar.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
