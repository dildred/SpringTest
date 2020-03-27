package com.project.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.emp.dto.MaterialDto;
import com.project.emp.other.AutoPaging;

/**
 * 매퍼의 인터페이스화<br>
 * 1. 매퍼.xml에 네임스페이스<br>
 * <mapper namespace="com.project.emp.dao.MaterialDao">를 현재 패키지로 설정함<br>
 * <br>
 * 2. 매퍼의 id와 인터페이스의 메소드를 일치시킴<br>
 * <select id="getMaterialStatus" -> public List<String> getMaterialStatus<br>
 * <br>
 * 3. 필요에 따라 파라미터의 이름을 설정해줌<br>
 * (@Param("delFlg") String delFlg)<br>
 * <br>
 * 4. mapper-bean-context.xml에 등록할 매퍼를 작성해줌.<br>
 * 
 * 5. 실행이 안되면 Dao에 @MapperScan 달아줘봄 
 * */
@MapperScan
@Repository
public interface MaterialDao {
	
	/**
	 * 삭제되지 않은 재료 데이터의 재료 분류를 중복 없이 가져옴<br>
	 * @param delFlg 삭제 플래그
	 * */
	public List<String> getMaterialStatus(@Param("delFlg") String delFlg);
	/**
	 * 재료를 등록한다.<br>
	 * @param material 등록 DTO
	 * */
	public Integer registMaterialData(MaterialDto material);
	/**
	 * 중복된 재료명이 있는지 확인한다.<br>
	 * @param matName 확인할 재료명
	 * */
	public Integer isMatName(@Param("matName") String matName);
	/**
	 * 총 재료의 개수를 취득한다.
	 * */
	public Integer getListCount();
	/**
	 * 총 재료의 리스트를 취득한다.<br>
	 * @param paging 페이지 해 놓은 것
	 * */
	public List<MaterialDto> getMaterialList(AutoPaging paging);
	/**
	 * 재료를 삭제한다(삭제 플래그를 1로 해놓는다)<br>
	 * @param material 기본키 2개 matNo, matName존재
	 * */
	public Integer deleteMaterial(MaterialDto material);
	/**
	 * 기본 키를 이용하여 데이터 하나를 호출한다.<br>
	 * @param materialNo 재료 번호, 
	 * @param mateialName 재료 이름
	 * */
	public MaterialDto getMaterialDataWithPrimaryKey(@Param("matNo")String materialNo, @Param("matName")String mateialName);
	/**
	 * 기본 키를 이용하여 데이터를 수정한다.<br>
	 * @param material 기본키 2개 matNo, matName존재
	 * */
	public Integer updateMaterial(MaterialDto material);
}
