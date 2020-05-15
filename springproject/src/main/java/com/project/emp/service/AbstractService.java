package com.project.emp.service;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractService {
    
    @Autowired
    private DataSource dataSource = null;
    
    public void proc() {
        
    }
    
    /**
     * DB 트랜잭션 롤백
    */
    public void rollback() {
        try {
            DataSourceUtils.getConnection(dataSource).rollback();
        } catch (CannotGetJdbcConnectionException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    
    public void commit() {
        try {
            DataSourceUtils.getConnection(dataSource).commit();
        } catch (CannotGetJdbcConnectionException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
