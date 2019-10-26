package com.Three2one.elearning.preconfig;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.Three2one.elearning.dao.RoleRepository;
import com.Three2one.elearning.dto.StudentForm;
import com.Three2one.elearning.exception.custom.StudentAlreadyExistException;
import com.Three2one.elearning.model.Role;
import com.Three2one.elearning.service.StudentManagementService;

@Component
public class OnApplicationStartUp {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentManagementService studentManagementService;

	@Autowired
	private RoleRepository roleRepo;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) throws StudentAlreadyExistException {
		logger.info("Start Of onApplicationEvent");
		fillRoleTable();
		createAdminUser();
		logger.info("End Of onApplicationEvent");

	}

	/**
	 * @throws StudentAlreadyExistException
	 * 
	 */
	private void createAdminUser() throws StudentAlreadyExistException {

		StudentForm studentForm = new StudentForm();
		studentForm.setName("admin");
		studentForm.setUsername("admin");
		studentForm.setEmail("admin@gmail.com");
		studentForm.setPassword("admin");
		studentManagementService.addAdminUser(studentForm);
	}

	/**
	 * 
	 */
	private void fillRoleTable() {
		logger.info("Start Of fillRoleTable");

		List<Role> roles = roleRepo.findAll();

		if (roles == null || roles.isEmpty()) {
			logger.debug("No Roles In a table ");
			roleRepo.save(new Role("ROLE_USER"));
			logger.debug("Role .. ROLE_USER inserted");
			roleRepo.save(new Role("ROLE_ADMIN"));
			logger.debug("Role .. ROLE_ADMIN inserted");

		}
		logger.info("End Of fillRoleTable");

	}

}