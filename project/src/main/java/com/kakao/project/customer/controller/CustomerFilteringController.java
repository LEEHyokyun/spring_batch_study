package com.kakao.project.customer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.project.customer.dto.CustomerFilteringDTO;
import com.kakao.project.customer.service.CustomerFilteringService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/cus")
public class CustomerFilteringController extends AbstractCustomerFilteringController{
	
	@Autowired
	private CustomerFilteringService customerFilteringService;
	
	@PostMapping("/find")
	//전달받는 자원은 리스트 형태입니다. 
	public List<CustomerFilteringDTO> selectCustomerFilteringList(@RequestBody List<CustomerFilteringDTO> req) {
		return customerFilteringService.selectCustomerFilteringList(req);
	}
}
