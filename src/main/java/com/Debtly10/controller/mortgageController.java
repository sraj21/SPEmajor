package com.Debtly10.controller;

import com.Debtly10.DTOS.*;
import com.Debtly10.Services.MortgageService;
import com.Debtly10.models.Customer;
import com.Debtly10.models.Mortgage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/mortgage")
public class mortgageController {
    private MortgageService mortgageService;

    public mortgageController(MortgageService mortgageService) {
        this.mortgageService = mortgageService;
    }

    @PostMapping("/register_mortgage/{cid}")
    public String addMortgage(@RequestBody MortgageRegistrationDTO mortgageRegistrationDTO,@PathVariable Long cid){
        return mortgageService.addMortgage(mortgageRegistrationDTO, cid);
    }

    @GetMapping("/mortgage_by_cid/{cid}")
    public List<Mortgage>  getMortgageByCustomer(@PathVariable Long cid)
    {
        return mortgageService.getMortgageByCustomer(cid);
    }

    @GetMapping("/get_mortgages")
    public List<MortgageFetchDTO> getMortgages(){
        return mortgageService.getAllMortgage();
    }

    @DeleteMapping("/delete_mortgage/{mid}")
    public String deleteMortgage(@PathVariable Long mid)
    {
        return mortgageService.deleteMortgage(mid);

    }

    @PatchMapping("/update_mortgage/{mid}")
    public String updateMortgage(@RequestBody MortgageUpdateDto mortgageUpdateDto,
                                 @PathVariable Long mid) {
        return mortgageService.updateMortgage(mortgageUpdateDto, mid);

    }





}
