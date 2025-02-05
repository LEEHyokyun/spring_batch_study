package com.kakao.project.rule.api.dto;

import lombok.Data;

/*
 * DTO명은 명사 + 명사에 행해지는 행위(동사) + API+DTO명으로 구성하여
 * DTO의 목적을 쉽게 알 수 있도록 설정합니다.
 * */

@Data
public class RuleWarningCustomerAPIDTO extends AbstractRuleAPIDTO{
	private String customerIdentityNo;   //거래자 실명번호

	public String getCustomerIdentityNo() {
		return this.customerIdentityNo;
	}
	
	public void setCustomerIdentityNo(String customerIdentityNo) {
		//실명번호를 구성합니다.
		this.customerIdentityNo = customerIdentityNo;
	}

}
