package com.kakao.project.common.dto;
/*
 * DTO명은 도메인 + 명사 + 명사에 행해지는 행위(동사) + DTO명으로 구성하여
 * DTO의 목적을 쉽게 알 수 있도록 설정합니다.
 * */
public class CommonFIleParsingDTO extends AbstractCommonDTO{
	private String customerBirthDay;     //거래자 생년월일
	private String customerEngName;      //거래자 영문명
	private String customerNation;       //거래자 국적
	
	public String getCustomerBirthDay() {
		return this.customerBirthDay;
	}
	
	public void setCustomerBirthDay(String customerBirthDay) {
		//생년월일 숫자만 구성할 수 있도록 합니다.
		customerBirthDay = customerBirthDay.replaceAll("[^0-9]","");
		this.customerBirthDay = customerBirthDay.length() < 8 ? "19"+customerBirthDay : customerBirthDay;
	}
	
	public String getCustomerEngName() {
		return this.customerEngName;
	}
	
	public void setCustomerEngName(String customerEngName) {
		//영문명 특수문자 제거, 소문자로만 구성할 수 있도록 합니다.
		this.customerEngName = customerEngName.replace(" ", "").toLowerCase();
	}
	
	public String getCustomerNation() {
		return this.customerNation;
	}
	
	public void setCustomerNation(String customerNation) {
		//국적 문자만 구성할 수 있도록 합니다.
		customerNation = customerNation.replaceAll("[^A-Z]","");
		this.customerNation = customerNation;
	}
}
