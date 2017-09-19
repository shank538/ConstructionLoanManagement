package com.gremadex.constructionloanmanager.controller;

import com.gremadex.constructionloanmanager.model.LoanDetails;
import com.gremadex.constructionloanmanager.persistance.domain.Address;
import com.gremadex.constructionloanmanager.persistance.repository.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by Shashank on 13/9/2017.
 */
@Controller
@RequestMapping("/construction/loan")
public class LoanController {

    @Autowired
    private AddressDao addressDao;

    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody
    LoanDetails getLoanDetails() {
        LoanDetails loanDetails = new LoanDetails();
        loanDetails.setBankName("DBS");
        loanDetails.setAmount(1000.0);

        return loanDetails;
    }

    @RequestMapping(value="/saveAddress", method= RequestMethod.GET)
    public @ResponseBody String saveAddress() {

        Address address = new Address();
        address.setCity("Alld");
        address.setCountryCode("SG");
        address.setLatitude("56.9");
        address.setLongitude("58.1");
        address.setStreet("street1");
        address.setZipCode("211003");
//        address.setId(new Long(123));

        addressDao.save(address);

        return "Address saved";
    }
}
