package com.gremadex.constructionloanmanager.controller;

import com.gremadex.constructionloanmanager.model.LoanDetails;
import com.gremadex.constructionloanmanager.persistance.domain.*;
import com.gremadex.constructionloanmanager.persistance.repository.*;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;
import com.gremadex.constructionloanmanager.persistance.domain.Guideline;
import com.gremadex.constructionloanmanager.persistance.domain.ConstructionPhase;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private IndividualDao individualDao;

    @Autowired
    private InspectionDao inspectionDao;

    @Autowired
    private LoanDao loanDao;

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
        guideline.setAddressId(address.getId().intValue());
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

    @RequestMapping(value = "/getGuideline/{projectName}", method = RequestMethod.GET)
    public @ResponseBody ConstructionGuideline getGuideline(@PathVariable("projectName") String projectName,Model model)
    {
//        Guideline guideline = guidelineDao.fetchGuideline(projectName);
//        Guideline guideline = guidelineDao.findOne((long)guidelineId);
//        model.addAttribute("guidelineOutput",guideline);
        Object[] guidelineResult = (Object[])guidelineDao.fetchGuideline(projectName).get(0);

        ConstructionGuideline guideline = new ConstructionGuideline();
//        Guideline guideline = new Guideline();
//        guideline.setId((Integer)(guidelineResult[0]));
        Integer addressId = (Integer)(guidelineResult[0]);
        guideline.setProjectName((String)(guidelineResult[1]));
        guideline.setStartDate((Date)(guidelineResult[2]));
        guideline.setEndDate((Date)(guidelineResult[3]));

        Address address = addressDao.findOne(addressId.longValue());
        guideline.setAddress(address);

        return guideline;
    }

    @RequestMapping(value = "/getAddress/{name}", method = RequestMethod.GET)
    public @ResponseBody Address getAddress(@PathVariable("name") String name,Model model)
    {

        Object[] addressResult = (Object[])addressDao.fetchAddress(name).get(0);

        Address address = new Address();

         address.setId((Integer) (addressResult[0]));
       // Integer id = (Integer)(addressResult[0]);
       // address.setId(((Integer)(addressResult[0])).intValue());
        address.setName((String) (addressResult[1]));
        address.setStreet((String) (addressResult[2]));
        address.setZipCode((String) (addressResult[3]));
        address.setCity((String) (addressResult[4]));
        address.setCountryCode((String) (addressResult[5]));
        address.setLatitude((Double) (addressResult[6]));
        address.setLongitude((Double) (addressResult[7]));




        return address;


    }

    @RequestMapping(value = "/getConstructionPhase/{phaseName}", method = RequestMethod.GET)
    public @ResponseBody ConstructionPhase getConstructionPhase(@PathVariable("phaseName") String phaseName,Model model) {

        Object[] constructionPhaseResult = (Object[])constructionPhaseDao.fetchConstructionPhase(phaseName).get(0);

        ConstructionPhase constructionPhase = new ConstructionPhase();

        constructionPhase.setId((Integer) (constructionPhaseResult[0]));
        constructionPhase.setPhaseName((String) (constructionPhaseResult[1]));
        constructionPhase.setMasterReferenceId((Integer) (constructionPhaseResult[2]));
        constructionPhase.setConstructionPhaseNumber((Integer) (constructionPhaseResult[3]));
        constructionPhase.setStatus((String) (constructionPhaseResult[4]));
        constructionPhase.setStartDate((Date) (constructionPhaseResult[5]));
        constructionPhase.setEndDate((Date) (constructionPhaseResult[6]));
        constructionPhase.setConstructionCost((BigDecimal) (constructionPhaseResult[7]));
        constructionPhase.setRevisedConstructionCost((BigDecimal) (constructionPhaseResult[8]));

        return constructionPhase;
    }



//    @PostMapping(value = "/getGuideline")
//    public String getGuideline(Model model)



    @RequestMapping(value = "/saveIndividual", method = RequestMethod.POST, consumes ={MediaType.APPLICATION_JSON_VALUE} )
    public  @ResponseBody String saveIndividual(@RequestBody Individual individual)
    {
        individualDao.save(individual);
        return "Individual saved";
    }


    @RequestMapping(value = "/saveInspection", method = RequestMethod.POST, consumes ={MediaType.APPLICATION_JSON_VALUE} )
    public  @ResponseBody String saveInspection(@RequestBody Inspection inspection)
    {


        inspectionDao.save(inspection);

        return "Inspection saved";
    }

    @RequestMapping(value = "/saveLoan", method = RequestMethod.POST, consumes ={MediaType.APPLICATION_JSON_VALUE} )
    public  @ResponseBody String saveLoan(@RequestBody Loan loan)
    {


        loanDao.save(loan);

        return "Loan saved";
    }


}
