package org.batili.repositories;

import java.util.List;

import org.batili.entities.AdressEntity;
import org.batili.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AddressRepository extends CrudRepository<AdressEntity, Long> {

	List<AdressEntity> findByUser(UserEntity currentUser);

	AdressEntity findByAdressId(String adressId);

}
