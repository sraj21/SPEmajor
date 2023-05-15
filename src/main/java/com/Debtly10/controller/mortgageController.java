package com.Debtly10.controller;

import com.Debtly10.DTOS.*;
import com.Debtly10.Services.MortgageService;
import com.Debtly10.models.Customer;
import com.Debtly10.models.Mortgage;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
@CrossOrigin
@RestController
@RequestMapping("/mortgage")
@Slf4j

public class mortgageController {
    private MortgageService mortgageService;

    public mortgageController(MortgageService mortgageService) {
        this.mortgageService = mortgageService;
    }
    private static final Logger logger = LogManager.getLogger(mortgageController.class);
    @PostMapping("/register_mortgage/{cid}")
    public String addMortgage(@RequestBody MortgageRegistrationDTO mortgageRegistrationDTO,@PathVariable Long cid){
        logger.info("[Registering new mortgage for customer] CID: "+cid);
        return mortgageService.addMortgage(mortgageRegistrationDTO, cid);
    }

    @GetMapping("/mortgage_by_cid/{cid}")
    public List<Mortgage>  getMortgageByCustomer(@PathVariable Long cid)
    {
        logger.info("[Retrieving all mortgages for customer] CID: "+cid);
        return mortgageService.getMortgageByCustomer(cid);
    }

    @GetMapping("/get_mortgages")
    public List<MortgageFetchDTO> getMortgages(){

        logger.info("[Retrieving all mortgages]");
        return mortgageService.getAllMortgage();
    }

    @DeleteMapping("/delete_mortgage/{mid}")
    public String deleteMortgage(@PathVariable Long mid)
    {
        logger.info("[Deleting mortgage] MID: "+mid);
        return mortgageService.deleteMortgage(mid);

    }

    @PatchMapping("/update_mortgage/{mid}")
    public String updateMortgage(@RequestBody MortgageUpdateDto mortgageUpdateDto,
                                 @PathVariable Long mid) {
        logger.info("[Updating mortgage] MID: "+mid);
        return mortgageService.updateMortgage(mortgageUpdateDto, mid);

    }





}
