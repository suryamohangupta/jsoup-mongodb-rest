package com.example.mdbspringboot.controller;

import com.example.mdbspringboot.apiDto.ApiResponse;
import com.example.mdbspringboot.constants.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

    @RequestMapping(path = AppConstants.TEST_URI, method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public ApiResponse doTest() {
        return new ApiResponse(HttpStatus.OK.value(), AppConstants.SUCCESS_MESSAGE, HttpStatus.OK);
    }
}
