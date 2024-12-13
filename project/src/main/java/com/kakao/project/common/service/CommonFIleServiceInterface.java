package com.kakao.project.common.service;

import java.util.ArrayList;

import com.kakao.project.common.dto.CommonFIleParsingDTO;

/*
 * 거래자에 대한 와치리스트 필터링 및 API 동작이 이루어지러면 파일파싱이 먼저 이루어져야 합니다.
 * 이 서비스는 공통영역에서 함수화하여 다른 도메인 영역에서도 자유롭게 활용할 수 있도록 처리할 것입니다.
 * 이예 따라 해당 파싱기능을 공통인터페이스 선언하여 활용하고자 합니다.
 * */

public interface CommonFIleServiceInterface {
	//NAS에 있는 리스트 파일 내용에서 고객영문명, 생년월일, 국적 정보를 반환합니다.
	//리스트 파일크기에 상관없이 동적인 할당, 조회 용이를 위해 arrayList 형태를 반환합니다.
	public abstract ArrayList<CommonFIleParsingDTO> getFileContents() throws Exception;
}
