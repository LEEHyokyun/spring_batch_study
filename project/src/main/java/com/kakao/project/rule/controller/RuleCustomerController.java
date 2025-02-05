package com.kakao.project.rule.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.project.customer.dto.CustomerFilteringDTO;
import com.kakao.project.customer.service.CustomerFilteringService;
import com.kakao.project.rule.dto.RuleCustomerDTO;
import com.kakao.project.rule.service.RuleCustomerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/rule")
public class RuleCustomerController extends AbstractRuleController{
	
	@Autowired
	private RuleCustomerService ruleCustomerService;
	
	@PostMapping("/insert")
	//전달받는 자원은 리스트 형태입니다. 
	public void insertRuleCustomer(@RequestBody RuleCustomerDTO req) {
		int result = ruleCustomerService.insertRuleCustomer(req);
		
		/*
		 * TOBE : 전산처리에 대한 분기처리
		 * */
	}
}
