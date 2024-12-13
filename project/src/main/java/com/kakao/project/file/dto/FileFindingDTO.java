package com.kakao.project.file.dto;

import lombok.Data;

/*
 * DTO명은 명사 + 명사에 행해지는 행위(동사) + DTO명으로 구성하여
 * DTO의 목적을 쉽게 알 수 있도록 설정합니다.
 * */

@Data
public class FileFindingDTO extends AbstractFileDTO{
	private String fileName;   //파일 명
	
	public String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(String fileName) {
		//파일명을 구성합니다.
		this.fileName = fileName;
	}

}
