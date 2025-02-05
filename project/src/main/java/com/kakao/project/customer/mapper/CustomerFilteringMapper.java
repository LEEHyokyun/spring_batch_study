package com.kakao.project.customer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kakao.project.customer.dto.CustomerFilteringDTO;

@Mapper
public interface CustomerFilteringMapper{
	
	//요주의 인물 내역을 조회합니다(고객 실명번호를 반환합니다).
	Map<String, Object> selectCustomerFilteringList(CustomerFilteringDTO req);
}
