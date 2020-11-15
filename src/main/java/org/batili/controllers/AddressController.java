package org.batili.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.batili.requests.AddressRequest;
import org.batili.responses.AddressResponse;
import org.batili.services.AddressService;
import org.batili.shared.dto.AdressesDto;
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
@RequestMapping("/addresses")
public class AddressController {
	@Autowired
	AddressService addressService;
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<AddressResponse>> getAddresses(Principal principal){
		List<AddressResponse> addressesResponse=new ArrayList<>();
		List<AdressesDto> addresses = addressService.getAddresses(principal.getName());
		for (AdressesDto ad : addresses) {
			AddressResponse addressResponse = new AddressResponse();
			BeanUtils.copyProperties(ad, addressResponse);
			addressesResponse.add(addressResponse);			
		}		
		return new ResponseEntity<List<AddressResponse>>(addressesResponse,HttpStatus.OK);
	}
	@PostMapping(
			consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, 
		    produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<AddressResponse> StoreAddresse(@RequestBody AddressRequest addressRequest, Principal principal) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		AdressesDto addressDto = modelMapper.map(addressRequest, AdressesDto.class);
		AdressesDto createAddress = addressService.createAddress(addressDto, principal.getName());
		
		AddressResponse newAddress = modelMapper.map(createAddress, AddressResponse.class);
		
		return new ResponseEntity<AddressResponse>(newAddress, HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public  ResponseEntity<AddressResponse> getOneAddresse(@PathVariable(name="id") String addressId) {
		
		AdressesDto addressDto = addressService.getAddress(addressId);
		
		ModelMapper modelMapper = new ModelMapper();
		
		AddressResponse addressResponse = modelMapper.map(addressDto, AddressResponse.class);
		
		return new ResponseEntity<AddressResponse>(addressResponse, HttpStatus.OK);
	}
	
//	@PutMapping("/{id}")
//	public ResponseEntity<String> updatreAddresse(@PathVariable(name="id") String addressId) {
//		return new ResponseEntity<>("update addresses", HttpStatus.ACCEPTED);
//	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAddresse(@PathVariable(name="id") String adressId) {
		
		addressService.deleteAddress(adressId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
