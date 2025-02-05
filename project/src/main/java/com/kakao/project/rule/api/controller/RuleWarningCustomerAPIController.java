package com.kakao.project.rule.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.project.customer.dto.CustomerFilteringDTO;
import com.kakao.project.customer.service.CustomerFilteringService;
import com.kakao.project.rule.api.dto.RuleWarningCustomerAPIDTO;
import com.kakao.project.rule.api.service.RuleWarningCustomerAPIService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/rule/api")
public class RuleWarningCustomerAPIController extends AbstractRuleAPIController{
	
	@Autowired
	private RuleWarningCustomerAPIService ruleWarningCustomerAPIService;
	
	@PostMapping("/send")
	//전달받는 자원은 리스트 형태입니다. 
	public void selectCustomerFilteringList(@RequestBody List<RuleWarningCustomerAPIDTO> req) {
		int result = ruleWarningCustomerAPIService.sendRuleWarningCustomerAPI(req);
		
		/*
		 * TOBE: result 상황에 맞는 분기처리
		 * */
	}
}
