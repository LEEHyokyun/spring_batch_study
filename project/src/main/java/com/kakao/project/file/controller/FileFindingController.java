package com.kakao.project.file.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.project.file.dto.FileFindingDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/file")
public class FileFindingController extends AbstractFileController{
	
	@Autowired
	private FileFindingController fileFindingService;
	
	@GetMapping("/find")
	//전달받는 자원이 없으므로 get mapping 합니다. 
	public List<FileFindingDTO> selectFileFindingList() {
		return fileFindingService.selectFileFindingList();
	}
}
