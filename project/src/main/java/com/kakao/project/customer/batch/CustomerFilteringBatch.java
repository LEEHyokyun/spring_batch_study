package com.kakao.project.customer.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

import com.kakao.project.common.dto.CommonFIleParsingDTO;
import com.kakao.project.common.service.CommonFIleService;
import com.kakao.project.customer.dto.CustomerFilteringDTO;
import com.kakao.project.customer.service.CustomerFilteringService;
import com.kakao.project.file.service.FileFindingService;
import com.kakao.project.rule.constants.RuleConstants;
import com.kakao.project.rule.dto.RuleCustomerDTO;

/*
 * 배치 클래스는 도메인 + 명사 + 명사에 행해지는 행위(동사) + DTO명으로 구성하여
 * 배치 클래스의 목적을 쉽게 알 수 있도록 설정합니다.
 * */
public class CustomerFilteringBatch extends AbstractCustomerFilteringBatch{
  /*
   * TOBE : 다른 도메인의 서비스 처리를 어떻게 개선할 수 있을것인가
   * */
  //파일 파싱을 진행하기 위한 서비스
  private FileFindingService fileFindingService;
  //파일을 파싱한 결과를 저장하기 위한 서비스
  private CommonFIleService commonFileService;
  //파싱한 파일 결과를 전달받아 요주의 인물 정보를 저장하기 위한 서비스
  private CustomerFilteringService customerFilteringService;
  //규제 상수값을 가져오기 위한 상수 변수
  private RuleConstants ruleConstants;
  
  /*
   * 배치의 작업방식 중 순차적, 단일 트랜잭션 수행 방식인 Tasklet은 적합하지 않음으로 판단하여,
   * 대용량 데이터 처리 및 오류가 발생하여도 청크마다 트랜잭션을 완료하는 chunk 방식으로 진행하고자 합니다.
   * */
  @Bean(name = "customerWatchListFilteringJob")
  public Job customerWatchListFilteringJob(JobRepository jobRepository, Step customerWatchListFilteringStep, Step noFilteringStep) {
	  	
	  	//application 설정으로 인해 프로그램 실행 시 자동으로 본 배치가 실행됩니다.
	  	
       	Job job = new Job();
       	
	    /*
	     * 서비스를 import하면 트랜잭션이 나뉘어져 단일화가 불가능하지만, 이 경우 조회이므로 Mapper가 아닌 service를 바로 import하여 사용하고자 합니다.
	    */
	    //통신 오류 등으로 인해 정상적인 파일 통신 내역이 존재하지 않는다면 배치를 실행하지 않습니다.
	    if(fileFindingService.selectFileFindingList().isEmpty()) {
	       job = (Job) new JobBuilder("noFilteringStep", jobRepository)		
	                 .start(noFilteringStep)
	                 .build();
	    }else {
	    	//배치에서 수행할 JOB을 정의합니다.
		   job = (Job) new JobBuilder("customerWatchListFilteringJob", jobRepository)
	                 .start(customerWatchListFilteringStep)
	                 .build();
	    }
	    	
		
       return job;
  }
	
  @Bean
  public Step customerWatchListFilteringStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
       /*
        * TOBE : 단일 트랜잭션으로 구성하는 방향은 유지하되 더 구조적, 안전한 처리를 구성할 수 있을것인가
        * */
		//JOB에서 수행할 step을 정의합니다.
		Step step = new StepBuilder("customerWatchListFilteringStep", jobRepository)
                    .tasklet(
                    		(contribution, chunkContext) -> {
                    	    /*
                    	     * step 수행
                    	     * */
                    		System.out.println("배치가 실행되었습니다!");
                    		
                    		/*
                    		 * 파일 파싱 내역을 받아서 요주의인물 선정 실명번호를 저장
                    		 * */
                    		ArrayList<CommonFIleParsingDTO> fileParsingList = commonFileService.getFileContents();
                    		List<CustomerFilteringDTO> customerFilteringList = new ArrayList<CustomerFilteringDTO>();
                    		for(CommonFIleParsingDTO fileParsingItem : fileParsingList) {
                    			
                    			
                    			CustomerFilteringDTO customerFilteringDTO = new CustomerFilteringDTO();
                    			customerFilteringDTO.setCustomerEngName(fileParsingItem.getCustomerEngName());
                    			customerFilteringDTO.setCustomerBirthDay(fileParsingItem.getCustomerBirthDay());
                    			customerFilteringDTO.setCustomerNation(fileParsingItem.getCustomerNation());
                    			
                    			customerFilteringList.add(customerFilteringDTO);
                    			
                    		}
                    		
                    		/*
                    		 * 조회 내역 존재하면 거래자 요주의 정보에 저장해야 합니다.
                    		 * */
                			List<CustomerFilteringDTO> result = customerFilteringService.selectCustomerFilteringList(customerFilteringList);
                			if(result.size() > 0) {
                				for(CustomerFilteringDTO customerFilteringItem : result) {
                					RuleCustomerDTO ruleCustomerDTO = new RuleCustomerDTO();
                					
                					ruleCustomerDTO.setCustomerIdentityNo(customerFilteringItem.getCustomerIdentityNo());
                					ruleCustomerDTO.setRuleCode(ruleConstants.ROLE_CUSOMTER);
                				}
                			}
                			
                    		/*
                    		 * step 수행
                    		 * */	
                            return RepeatStatus.FINISHED;
                    		},
                    		platformTransactionManager
                    )
              .build();
       
       return step;
  }
  
  @Bean
  public Step noFilteringStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
       
		//JOB에서 수행할 step을 정의합니다.
		Step step = new StepBuilder("NoFilteringStep", jobRepository)
                    .tasklet(
                    		(contribution, chunkContext) -> {
                    	    /*
                    	     * step 수행
                    	     * */
                    		System.out.println("요주의 인물 확인 배치를 실행할 파일이 존재하지 않습니다.");
                    			
                    		/*
                    		 * step 수행
                    		 * */	
                            return RepeatStatus.FINISHED;
                    		},
                    		platformTransactionManager
                    )
              .build();
       
       return step;
  }
}


