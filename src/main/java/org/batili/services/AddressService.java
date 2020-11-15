package org.batili.services;

import java.util.List;

import org.batili.shared.dto.AdressesDto;

public interface AddressService {

	List<AdressesDto> getAddresses(String email);

	AdressesDto createAddress(AdressesDto addressDto, String email);

	AdressesDto getAddress(String adressId);

	void deleteAddress(String adressId);

}
