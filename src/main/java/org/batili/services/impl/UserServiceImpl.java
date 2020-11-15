package org.batili.services.impl;
import java.util.ArrayList;
import java.util.List;
import org.batili.entities.UserEntity;
import org.batili.repositories.UserRepository;
import org.batili.services.UserService;
import org.batili.shared.Utils;
import org.batili.shared.dto.AdressesDto;
import org.batili.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils utils;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public UserDto createUser(UserDto user) {
		UserEntity check = userRepository.findByEmail(user.getEmail());
		if(check!=null) throw new RuntimeException("this user exists already!");
//		UserEntity userEntity = new UserEntity();
//		BeanUtils.copyProperties(user, userEntity);
		for (int i = 0; i< user.getAdresses().size() ; i++) {
			AdressesDto adress=user.getAdresses().get(i);//creer adress a partir de user
			adress.setUser(user);//lier user a adress
			adress.setAdressId(utils.generateStringId(16));//generer adressId
			user.getAdresses().set(i, adress);//mise a jour de l'adresse de user apres l'ajout de adressid	
		}
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity=modelMapper.map(user, UserEntity.class);
		userEntity.setPasswordEncrypted(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(utils.generateStringId(16)); 
		UserEntity newUser = userRepository.save(userEntity);
//		UserDto userDto = new UserDto();
//		BeanUtils.copyProperties(newUser, userDto);
		UserDto userDto = modelMapper.map(newUser, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userById=userRepository.findByUserId(userId);
		if(userById==null) throw new RuntimeException("user not exist");
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userById, userDto);	
		return userDto;
	}

	@Override
	public UserDto updateUser(String id,UserDto user) {
		UserEntity userById=userRepository.findByUserId(id);
		if(userById==null) throw new RuntimeException("user not exist");
		userById.setFirstName(user.getFirstName());
		userById.setLastName(user.getLastName());
		UserEntity userUpdated = userRepository.save(userById);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userUpdated, userDto);
		return userDto;
	}

	@Override
	public void deleteUser(String id) {
	UserEntity userById=userRepository.findByUserId(id);
		if(userById==null) throw new RuntimeException("user not exist");
		userRepository.delete(userById);
	}
	
	@Override
	public List<UserDto> getUsers() {	
		List<UserDto> users=new ArrayList<>();
		List<UserEntity> findAll = (List<UserEntity>) userRepository.findAll();
		for (UserEntity userEntity : findAll) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			users.add(userDto);		
		}	
		return users;
	}
// recupere l'utilisateur authentifié de la BdD
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity==null) throw new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(), userEntity.getPasswordEncrypted(), new ArrayList<>());
	}

	@Override
	public UserDto getUserByEmail(String email) {
		UserEntity userByEmail=userRepository.findByEmail(email);
		if(userByEmail==null) throw new UsernameNotFoundException(email);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userByEmail, userDto);	
		return userDto;
	}
}
