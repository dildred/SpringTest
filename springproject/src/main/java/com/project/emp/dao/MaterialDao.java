package com.project.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.emp.dto.MaterialDto;

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
	/**
	 * 재료를 등록한다.<br>
	 * @param material 등록 DTO
	 * */
	public Integer registMaterialData(MaterialDto material) {
		// TODO Auto-generated method stub
		return template.insert(namespace + ".registMaterialData", material);
	}
	
	/**
	 * 중복된 재료명이 있는지 확인한다.<br>
	 * @param matName 확인할 재료명
	 * */
	public Integer isMatName(@Param("matName") String matName) {
		// TODO Auto-generated method stub
		return template.selectOne(namespace + ".isMatName", matName);
	}
}
