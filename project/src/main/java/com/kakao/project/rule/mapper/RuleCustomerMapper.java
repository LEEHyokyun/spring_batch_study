package com.kakao.project.rule.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kakao.project.rule.dto.RuleCustomerDTO;

@Mapper
public interface RuleCustomerMapper{
	
	//요주의 인물 내역을 조회합니다(고객 실명번호를 반환합니다).
	int insertRuleCustomer(RuleCustomerDTO req);
}
