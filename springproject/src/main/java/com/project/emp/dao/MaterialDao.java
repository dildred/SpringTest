package com.project.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MaterialDao {
	
	@Autowired
    private SqlSessionTemplate template;
	
	private String namespace = "material";
	
	/**
	 * 삭제되지 않은 재료 데이터의 재료 분류를 중복 없이 가져옴<br>
	 * @param delFlg 삭제 플래그
	 * */
	public List<String> getMaterialStatus(@Param("delFlg") String delFlg){
        return template.selectList(namespace + ".getMaterialStatus", delFlg);
  }
}
