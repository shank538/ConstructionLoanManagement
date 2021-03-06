package com.gremadex.constructionloanmanager.persistance.repository;

import com.gremadex.constructionloanmanager.persistance.domain.Address;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Shashank on 19/9/2017.
 */
public interface AddressDao extends CrudRepository<Address, Long> {

//    Address findByCityAndName(Address address, String name);

    @Query(value = "select id,  name, street, zip_code, city, country_code, latitude, longitude from address where name  = ?1", nativeQuery = true)
    public List<Object> fetchAddress(String name);

}
