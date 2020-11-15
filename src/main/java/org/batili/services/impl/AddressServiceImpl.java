package org.batili.services.impl;

import java.lang.reflect.Type;
import java.util.List;
import org.batili.entities.AdressEntity;
import org.batili.entities.UserEntity;
import org.batili.repositories.AddressRepository;
import org.batili.repositories.UserRepository;
import org.batili.services.AddressService;
import org.batili.shared.Utils;
import org.batili.shared.dto.AdressesDto;
import org.batili.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils util;
	@Override
	public List<AdressesDto> getAddresses(String email) {
		UserEntity currentUser = userRepository.findByEmail(email);
		List<AdressEntity> findAll =currentUser.getAdmin()==true ? (List<AdressEntity>) addressRepository.findAll() :(List<AdressEntity>) addressRepository.findByUser(currentUser);
		ModelMapper modelMapper = new ModelMapper();
		Type listType =  new TypeToken<List<AdressesDto>>() {}.getType(); //Type import to java.lang.reflect
		List<AdressesDto> adresses = modelMapper.map(findAll, listType);
		return adresses;
	}
	@Override
	public AdressesDto createAddress(AdressesDto address, String email) {
		
		UserEntity currentUser = userRepository.findByEmail(email);
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(currentUser, UserDto.class);
		
		address.setAdressId(util.generateStringId(30));
		address.setUser(userDto);
		
		AdressEntity addressEntity = modelMapper.map(address, AdressEntity.class); 
		
		AdressEntity newAddress = addressRepository.save(addressEntity);
		
		AdressesDto addressDto = modelMapper.map(newAddress, AdressesDto.class);
		
		return addressDto;
	}
	@Override
public AdressesDto getAddress(String adressId) {
		
		AdressEntity addressEntity = addressRepository.findByAdressId(adressId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		AdressesDto addressDto = modelMapper.map(addressEntity, AdressesDto.class);
		
		return addressDto;
	}
	@Override
	public void deleteAddress(String adressId) {
		
		AdressEntity address = addressRepository.findByAdressId(adressId);
		
		if(address == null) throw new RuntimeException("Address not found");
		
		addressRepository.delete(address);
	
	}

}
