package org.batili.controllers;

import java.util.ArrayList;
import java.util.List;
import org.batili.exceptions.UserException;
import org.batili.requests.UserRequest;
import org.batili.responses.ErroMessages;
import org.batili.responses.UserResponse;
import org.batili.services.UserService;
import org.batili.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) throws Exception {
		if(userRequest.getFirstName().isEmpty()) throw new UserException(ErroMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
//		UserDto userDto=new UserDto();
//		BeanUtils.copyProperties(userRequest, userDto);

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		UserDto create = userService.createUser(userDto);
//		UserResponse userResponse = new UserResponse(); 
//		BeanUtils.copyProperties(create, userResponse);
		UserResponse userResponse = modelMapper.map(create, UserResponse.class);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED);
		
	}
	@GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		UserDto userByUserId = userService.getUserByUserId(id);		
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userByUserId, userResponse);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
	}
	@PutMapping(path = "/{id}" )
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id,@RequestBody UserRequest userRequest) {
		UserDto userDto=new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		UserDto update = userService.updateUser(id,userDto);
		UserResponse userResponse = new UserResponse(); 
		BeanUtils.copyProperties(update, userResponse);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.ACCEPTED);
	}
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	 public List<UserResponse> getAllUsers(){
		List<UserResponse> usersResponse=new ArrayList<>();
		List<UserDto> users = userService.getUsers();
		for (UserDto userDto : users) {
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(userDto, userResponse);
			usersResponse.add(userResponse);			
		}		
		return usersResponse;
	}
}
