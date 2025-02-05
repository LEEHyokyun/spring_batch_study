package com.kakao.project.file.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.project.file.dto.FileFindingDTO;
import com.kakao.project.file.mapper.FileFindingMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileFindingService extends AbstractFileService{
	
	@Autowired
	private FileFindingMapper fileFindingMapper;
	
	@Transactional(readOnly = true)
	public List<FileFindingDTO> selectFileFindingList(){
		
		//resultList : 동적인 배열크기와 정적 배열 결과에 대한 조회 용이성을 위해 ArrayList로 결과를 반환 받습니다.
		List<FileFindingDTO> resultList = new ArrayList<FileFindingDTO>();
		
		//resultList에 조회결과를 반환
		List<Map<String, Object>> result = fileFindingMapper.selectFileFindingList();
		for(int i=0 ; i<result.size(); i++) {
			//조회결과를 map을 통해 반환받고, 조회대상 파일명을 저장하는 리스트를 구성합니다.
			resultList.set(i, this.setResult(result.get(i)));
		}
		
		return resultList;
	}
	
	//로직의 구조화와 가독성을 위해 반복되는 부분은 함수로 조치합니다.
	private FileFindingDTO setResult(Map<String, Object> result) {
		//return
		FileFindingDTO fileFindingDTO = new FileFindingDTO();
		
		//DTO 세팅
		fileFindingDTO.setFileName(String.valueOf(result.get("파일_명")));
		
		return fileFindingDTO;
	}
}
