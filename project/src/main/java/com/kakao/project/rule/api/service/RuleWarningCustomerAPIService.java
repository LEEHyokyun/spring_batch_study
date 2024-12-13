package com.kakao.project.rule.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.kakao.project.customer.dto.CustomerFilteringDTO;
import com.kakao.project.customer.mapper.CustomerFilteringMapper;
import com.kakao.project.rule.dto.RuleCustomerDTO;
import com.kakao.project.rule.mapper.RuleCustomerMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RuleWarningCustomerAPIService extends AbstractRuleAPIService{
	
	@Autowired
	private RuleCustomerMapper ruleCustomerMapper;
	
	@Transactional
	public int insertRuleCustomer(RuleCustomerDTO item){
		
		//요주의 정보를 외부기관으로 전달합니다.
		ruleCustomerMapper.insertRuleCustomer(item);
		
		/*
		 * TOBE : 전산처리 예외상황에 대한 분기
		 * */
		
		//정상적으로 처리되었을때 1을 반환합니다.
		return 1;
	}

}
