package com.gremadex.constructionloanmanager.controller;

import com.gremadex.constructionloanmanager.model.LoanDetails;
import com.gremadex.constructionloanmanager.persistance.domain.Address;
import com.gremadex.constructionloanmanager.persistance.domain.ConstructionGuideline;
import com.gremadex.constructionloanmanager.persistance.repository.AddressDao;
import com.gremadex.constructionloanmanager.persistance.repository.ConstructionPhaseDao;
import com.gremadex.constructionloanmanager.persistance.repository.GuidelineDao;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gremadex.constructionloanmanager.persistance.domain.Guideline;
import com.gremadex.constructionloanmanager.persistance.domain.ConstructionPhase;

/**
 * Created by Shashank on 13/9/2017.
 */
@Controller
@RequestMapping("/construction/loan")
public class LoanController {

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private GuidelineDao guidelineDao;

    @Autowired
    private ConstructionPhaseDao constructionPhaseDao;

    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody
    LoanDetails getLoanDetails() {
        LoanDetails loanDetails = new LoanDetails();
        loanDetails.setBankName("DBS");
        loanDetails.setAmount(1000.0);

        return loanDetails;
    }

    @RequestMapping(value="/saveAddress", method= RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE} )
    public @ResponseBody String saveAddress(@RequestBody Address address) {

//        Address address = new Address();
//        address.setCity("Alld");
//        address.setCountryCode("SG");
//        address.setLatitude("56.9");
//        address.setLongitude("58.1");
//        address.setStreet("street1");
//        address.setZipCode("211003");
//        address.setId(new Long(123));

        addressDao.save(address);

        return "Address saved";
    }

    @RequestMapping(value = "/saveGuideline", method = RequestMethod.POST, consumes ={MediaType.APPLICATION_JSON_VALUE} )
    public  @ResponseBody String saveGuideline(@RequestBody Guideline guideline)
    {
        guidelineDao.save(guideline);

        return "Guideline saved";
    }

    @RequestMapping(value = "/saveGuideline2", method = RequestMethod.POST, consumes ={MediaType.APPLICATION_JSON_VALUE} )
    public  @ResponseBody String saveGuideline2(@RequestBody ConstructionGuideline constructionGuideline)
    {
        constructionGuideline.getAddress();
        Address address = addressDao.save(constructionGuideline.getAddress());

        Guideline guideline = new Guideline();
        guideline.setAddressId(address.getId());
        guideline.setEndDate(constructionGuideline.getEndDate());
        guideline.setProjectName(constructionGuideline.getProjectName());
        guideline.setStartDate(constructionGuideline.getStartDate());
        guidelineDao.save(guideline);
        return "Construction Guideline saved";
    }

    @RequestMapping(value = "/saveConstructionPhase", method = RequestMethod.POST, consumes ={MediaType.APPLICATION_JSON_VALUE} )
    public  @ResponseBody String saveConstructionPhase(@RequestBody ConstructionPhase constructionPhase)
    {


        constructionPhaseDao.save(constructionPhase);

        return "Construction Phase saved";
    }

}
