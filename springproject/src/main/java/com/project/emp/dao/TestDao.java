package com.project.emp.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.emp.dto.TestDto;

/**
 * Annotation Repository<br>
 * 아래와 같이 적으면 이 자바 파일이 리포지토리(다른말로 DAO랑 동일하다 생각하면 됨)라는 것을 명시(컨트롤러와 동일!)<br>
 * bean컨테이너에 등록이 되어 있어야 Annotation Autowired(의존 객체 주입)를 사용할 수 있음.<br>
 * */
@Repository
public interface TestDao {
	
    /**
     * testDto라고 적은 것이 매개변수
     * 자세한 내용은 test_mapper.xml참조
     * 
     * */
    public Integer insertTestDtoTable(@Param("testDto") TestDto testDto);
    
}
