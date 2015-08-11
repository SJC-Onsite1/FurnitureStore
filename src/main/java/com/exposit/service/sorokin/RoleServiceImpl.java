package com.exposit.service.sorokin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exposit.domain.model.sorokin.Role;
import com.exposit.domain.service.sorokin.RoleService;
import com.exposit.repository.dao.sorokin.RoleDao;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleRepository;

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();

	}

	@Override
	public Role getRoleById(Integer id) {
		return roleRepository.findById(id);
	}

}
