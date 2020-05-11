package com.project.emp.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@MapperScan
@Repository
public interface FileProcessingDao {

    public Integer insertProc(@Param("originName") String originName, 
            @Param("storeName") String storeName,
            @Param("directory") String directory, 
            @Param("fileSize") String fileSize, 
            @Param("method") String method, 
            @Param("message")String message);
    
}
