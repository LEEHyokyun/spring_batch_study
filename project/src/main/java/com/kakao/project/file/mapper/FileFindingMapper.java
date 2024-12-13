package com.kakao.project.file.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface FileFindingMapper{
	
	//파일 조회 대상 여부 및 파싱 진행 여부 내역을 조회합니다.
	List<Map<String, Object>> selectFileFindingList();
}
