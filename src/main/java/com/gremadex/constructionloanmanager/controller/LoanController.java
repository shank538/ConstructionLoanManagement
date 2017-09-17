package com.gremadex.constructionloanmanager.controller;

import com.gremadex.constructionloanmanager.model.LoanDetails;
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

    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody
    LoanDetails getLoanDetails() {
        LoanDetails loanDetails = new LoanDetails();
        loanDetails.setBankName("DBS");
        loanDetails.setAmount(1000.0);

        return loanDetails;
    }
}
