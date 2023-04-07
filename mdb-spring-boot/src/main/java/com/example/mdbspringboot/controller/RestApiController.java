package com.example.mdbspringboot.controller;

import com.example.mdbspringboot.apiDto.ApiResponse;
import com.example.mdbspringboot.constants.AppConstants;
import com.example.mdbspringboot.dbEntity.UsedCarEntity;
import com.example.mdbspringboot.service.UsedCarContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RestApiController {

    @Autowired
    UsedCarContentService usedCarContentService;

    @RequestMapping(path = AppConstants.TEST_URI, method = RequestMethod.GET,
            headers = "Accept=application/json", produces = "application/json")
    public ApiResponse doTest() {
        return new ApiResponse(HttpStatus.OK.value(), AppConstants.SUCCESS_MESSAGE, HttpStatus.OK);
    }

    @RequestMapping(path = AppConstants.FIND_ALL_USED_CAR_URI, method = RequestMethod.GET,
            headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody List<UsedCarEntity> getCarAllDetails() {
        return usedCarContentService.getCarAllDetails();
    }

    @RequestMapping(path = AppConstants.FIND_USED_CAR_URI, method = RequestMethod.GET,
            headers = "Accept=application/json", produces = "application/json")
    public @ResponseBody List<UsedCarEntity> getCarDetails(@RequestParam Map<String, String> reqParam ) {
        return usedCarContentService.getCarDetails(reqParam);
    }
}
