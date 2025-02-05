package com.kakao.project.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.kakao.project.customer.dto.CustomerFilteringDTO;
import com.kakao.project.customer.mapper.CustomerFilteringMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerFilteringService extends AbstractCustomerFilteringService{
	
	@Autowired
	private CustomerFilteringMapper customerFilteringMapper;
	
	@Transactional(readOnly = true)
	public List<CustomerFilteringDTO> selectCustomerFilteringList(List<CustomerFilteringDTO> list){
		
		//resultList : 동적인 배열크기와 정적 배열 결과에 대한 조회 용이성을 위해 ArrayList로 결과를 반환 받습니다.
		List<CustomerFilteringDTO> resultList = new ArrayList<CustomerFilteringDTO>();
		
		//resultList에 조회결과를 반환
		for(int i=0 ; i<list.size(); i++) {
			//조회결과를 map을 통해 반환받고, 요주의인물로 선정된 실명번호를 저장합니다.
			resultList.set(i, this.setResult(customerFilteringMapper.selectCustomerFilteringList(list.get(i))));
		}
		
		return resultList;
	}
	
	//로직의 구조화와 가독성을 위해 반복되는 부분은 함수로 조치합니다.
	private CustomerFilteringDTO setResult(Map<String, Object> result) {
		//return
		CustomerFilteringDTO customerFilteringDTO = new CustomerFilteringDTO();
		
		//DTO 세팅
		customerFilteringDTO.setCustomerIdentityNo(String.valueOf(result.get("거래자_실명번호")));
		
		return customerFilteringDTO;
	}
}
