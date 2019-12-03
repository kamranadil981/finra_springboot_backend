package com.finra.finratest.controller;

import com.finra.finratest.helper.GenerateCombinations;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "finratest")
public class MobileNumberController {

    @GetMapping(value = "/{mobileNumber}", produces = "application/json")
    public ResponseEntity<Object> getMobileNumberCombination(@PathVariable String mobileNumber)throws Exception{
        if (mobileNumber.length() == 10 || mobileNumber.length() ==7)
          return new GenerateCombinations().getCombinations(mobileNumber);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Mobile Number!");
    }
}