package com.vietis.task.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vietis.task.converter.UserConverter;
import com.vietis.task.dto.UserDTO;
import com.vietis.task.model.Exercise;
import com.vietis.task.model.ExerciseQuest;
import com.vietis.task.model.Result;
import com.vietis.task.model.Role;
import com.vietis.task.model.User;
import com.vietis.task.repository.ExerciseQuestRepository;
import com.vietis.task.repository.ExerciseRepository;
import com.vietis.task.repository.ResultRepository;
import com.vietis.task.repository.RoleRepository;
import com.vietis.task.repository.UserRepository;
import com.vietis.task.service.GenericService;
import com.vietis.task.service.UserService;

@Service
public class UserServiceImpl implements UserService, GenericService<UserDTO> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@Autowired
	private ExerciseQuestRepository exerciseQuestRepo;
	
	@Autowired
	private ResultRepository resultRepo;
	
	@Autowired
	private UserConverter userConverter;
	
	@Override
	public List<UserDTO> findAll() {
		List<User> userList = userRepository.findAll();
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		for (User entity : userList) {
			UserDTO dto = userConverter.toDTO(entity);
			Role role = roleRepository.findById(dto.getRoleId()).get();
			dto.setRoleName(role.getName());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public Page<UserDTO> findAll(Pageable pageable) {
		Page<User> users = userRepository.findAll(pageable);
		List<UserDTO> dtos = userConverter.toDTOList(users.getContent());
		return new PageImpl<UserDTO>(dtos, pageable, users.getTotalElements());
	}

	@Override
	public UserDTO save(UserDTO dto) {
		if (dto.getId() == null) {
			dto.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
		}
		User user = userRepository.save(userConverter.toEntity(dto));
		return userConverter.toDTO(user);
	}

	@Override
	public UserDTO findOne(Integer id) {
		return userConverter.toDTO(userRepository.findById(id).get());
	}

	@Override
	public String delete(Integer id) {
		List<Exercise> exerList = exerciseRepository.findByUserid(id);
		for (Exercise entity : exerList) {
			List<ExerciseQuest> exerciseQuestList = exerciseQuestRepo.findByExerciseid(entity.getId());
			exerciseQuestRepo.deleteAll(exerciseQuestList);
			
			List<Result> resultList = resultRepo.findByExerciseid(entity.getId());
			resultRepo.deleteAll(resultList);
			
			exerciseRepository.delete(entity);
		}		
		userRepository.deleteById(id);	
		return "";
	}

	@Override
	public List<UserDTO> findByRoleId(Integer id) {
		return userConverter.toDTOList(userRepository.findByRoleid(id));
	}

}
