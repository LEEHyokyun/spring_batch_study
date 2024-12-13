package com.kakao.project.common.service;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kakao.project.common.constants.CommonFileConstants;
import com.kakao.project.common.constants.CommonFileConstants.FileType;
import com.kakao.project.common.dto.CommonFIleParsingDTO;

/*
 * NAS에 있는 파일을 파싱하여 파일 확장자에 따라 거래자 영문명, 생년월일, 국적정보를 반환하는 메소드입니다.
 * */
/*
 * TOBE : 모든 파일을 txt 파일로 일괄 변환하여 enum 관리 및 분기처리 필요없도록 구성할 수는 없을까?
 * */
public class CommonFIleService implements CommonFIleServiceInterface{
	public ArrayList<CommonFIleParsingDTO> getFileContents() throws Exception{
		//필요한 변수들을 초기화합니다.
		ArrayList<CommonFIleParsingDTO> fileParsingList = new ArrayList<CommonFIleParsingDTO>();
		
		//파일 디렉토리(NAS)에 있는 파일을 순차적으로 탐색합니다.
		File files = new File(CommonFileConstants.FILE_DIRECTORY);
		
		//탐색하여 확장자에 따라 파일 읽기 로직을 수행하고 파싱결과를 list 형태로 반환합니다.
		for (File file : files.listFiles()) {
			
			String fileName = file.getName().split("[.]")[0];
			FileType fileType = CommonFileConstants.FileType.getType(file.getName().split("[.]")[1]);
			
			switch(fileType) {
				case txt : 	
					//txt 파일 읽기
					this.getTxtFileParsingDTO(fileParsingList, new Scanner(file));
					
				break;
				
				case xlsx : 
					//xlsx 파일 읽기
					this.getExcelFileParsingDTO(fileParsingList, file);
					
				break;
				
				//추가 구성이 필요할 경우 fileType enum에 확장자를 추가하여 구성할 수 있습니다.
				default : System.out.println(CommonFileConstants.FILE_NEW_TYPE_MESSAGE);
				break;
			} 
			
			
		}
		
		return fileParsingList;
	}
	
	/*
	 * 서비스 내에서 공통적으로 사용하는 로직은 함수처리하여 로직을 구조화하고 가독성을 높입니다.
	 * */
	
	//텍스트 파일의 내용을 읽고 DTO형태로 반환하여 list에 추가할 수 있도록 해주는 함수입니다.
	public void getTxtFileParsingDTO(ArrayList<CommonFIleParsingDTO> fileParsingList, Scanner contentList) {
		while(contentList.hasNext()) {
			CommonFIleParsingDTO fileParsingDTO = new CommonFIleParsingDTO();
			
			String[] content = contentList.nextLine().split(CommonFileConstants.FILE_TXT_SEPERATOR);
			
			fileParsingDTO.setCustomerEngName(content[0]);
			fileParsingDTO.setCustomerBirthDay(content[1]);
			fileParsingDTO.setCustomerNation(content[2]);
			
			fileParsingList.add(fileParsingDTO);
		}
	}
	
	//엑셀 파일의 내용을 읽고 DTO형태로 반환하여 list에 추가할 수 있도록 해주는 함수입니다.
	public void getExcelFileParsingDTO(ArrayList<CommonFIleParsingDTO> fileParsingList, File file) throws InvalidFormatException, IOException {

		//엑셀파일 읽기(파일 용량이 커질 경우 스트림을 이용할 경우 메모리 과부하 문제 여지, File 객체를 활용
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		//시트 갯수
		int sheetNum = workbook.getNumberOfSheets();
		
		for(int i=0 ; i<sheetNum ; i++) {
			XSSFSheet sheet = workbook.getSheetAt(i);
			
			//행 갯수
			int sheetRows = sheet.getPhysicalNumberOfRows();
			
			//행 별 문자열 파싱
			for(int j=0;j<sheetRows;j++) {
				CommonFIleParsingDTO fileParsingDTO = new CommonFIleParsingDTO();
				
				XSSFRow row = sheet.getRow(j);
				
				fileParsingDTO.setCustomerEngName(this.getCellValue(row.getCell(0)));
				fileParsingDTO.setCustomerBirthDay(this.getCellValue(row.getCell(1)));
				fileParsingDTO.setCustomerNation(this.getCellValue(row.getCell(2)));
				
				fileParsingList.add(fileParsingDTO);
			}
		}
	}
	
	//엑셀 파일 내용 파싱을 위한 함수입니다. 반복되는 로직의 구조화 및 가독성을 위해 별도 함수처리를 하였습니다.
	public static String getCellValue(XSSFCell cell) {
		NumberFormat nFormat = NumberFormat.getInstance();
		nFormat.setGroupingUsed(false); //숫자가 지수형식으로 나오지 않도록 설정
		
		//return 값은 항상 문자열 형식입니다.
		String value = "";
		
		switch(cell.getCellType()) {
			case STRING : 
				value = cell.getStringCellValue(); 
			break;
			
			case NUMERIC : 
				value = nFormat.format(cell.getNumericCellValue());
			break;
			
			default : 
				value = cell.getStringCellValue();
			break;
		}
		
		return value;
	}
}
