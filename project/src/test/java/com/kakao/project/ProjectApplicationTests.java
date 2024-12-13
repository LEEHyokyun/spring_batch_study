package com.kakao.project;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kakao.project.file.mapper.FileFindingMapper;

@MybatisTest
@SpringBootTest
class ProjectApplicationTests {
	
	@Autowired
	FileFindingMapper fileFindingMapper;
	
	@Test
	@DisplayName("file Mapper Test")
	void fileMappingTest() {
		//null이 아닌지 테스트 하는 unit test
		assertNotNull(fileFindingMapper.selectFileFindingList());
	}

}
