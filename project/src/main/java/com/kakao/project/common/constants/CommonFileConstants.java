package com.kakao.project.common.constants;

import java.util.Arrays;

/*
 * 파일 확장자에 대한 상수 enum입니다.
 * 확장자, 파일 처리 로직의 확장 용이를 위해 확장자명을 enum으로 관리합니다.
 * */
public class CommonFileConstants {
	
	//파일 디렉토리(NAS)는 변동성이 매우 적으므로 일반 상수 처리
	public static String FILE_DIRECTORY = "c:/rcv/";
	
	//txt 파일의 구분자는 전문규격으로 설정하여 관리하여 변동성이 매우 적으므로 일반 상수 처리
	public static String FILE_TXT_SEPERATOR = "\\|\\|\\|";
	
	//확장자가 신규 유형일 경우 메시지 상수 처리
	public static String FILE_NEW_TYPE_MESSAGE = "새로운 유형의 파일 확장자입니다. 별도 로직 구성이 필요합니다.";
	
	//확장자명 enum 관리
	public enum FileType {
		//파일 확장자를 상수처리
		txt("txt"), 
		csv("csv"), 
		xlsx("xlsx"); 
		
		private final String type; 
		
		//다른 곳에서 생성자를 동적으로 변화시킬 수 없게끔 조치
		private FileType(String type){ 
			this.type = type; 
		} 
		
		public static FileType getType(String type) { 
			return Arrays.stream(values())
			.filter(fileType -> fileType.type.equals(type))
			.findAny()
			.orElse(txt);
		} 
	}
}
