package com.kakao.project.rule.dto;

import lombok.Data;

/*
 * DTO명은 명사 + 명사에 행해지는 행위(동사) + DTO명으로 구성하여
 * DTO의 목적을 쉽게 알 수 있도록 설정합니다.
 * */

@Data
public class RuleCustomerDTO extends AbstractRuleDTO{
	private String customerIdentityNo;   //거래자 실명번호
	private String ruleCode;             //규제 코드
	
	public String getCustomerIdentityNo() {
		return this.customerIdentityNo;
	}
	
	public void setCustomerIdentityNo(String customerIdentityNo) {
		//실명번호를 구성합니다.
		this.customerIdentityNo = customerIdentityNo;
	}
	
	public String getRuleCode() {
		return this.customerIdentityNo;
	}
	
	public void setRuleCode(String ruleCode) {
		//규제코드를 구성합니다.
		this.ruleCode = ruleCode;
	}
}
