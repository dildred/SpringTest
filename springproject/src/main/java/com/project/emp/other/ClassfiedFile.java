package com.project.emp.other;

public class ClassfiedFile {
    
    //원래 파일명
    private String fileOriginName;
    //저장해놓을 파일명
    private String fileStoreName;
    private Long fileSize;
    private String fileSizes;
    
    public String getFileSizes() {
        return fileSizes;
    }
    public void setFileSizes(String fileSizes) {
        this.fileSizes = fileSizes;
    }
    public String getFileOriginName() {
        return fileOriginName;
    }
    public ClassfiedFile setFileOriginName(String fileOriginName) {
        this.fileOriginName = fileOriginName;
        return this;
    }
    public String getFileStoreName() {
        return fileStoreName;
    }
    public ClassfiedFile setFileStoreName(String fileStoreName) {
        this.fileStoreName = fileStoreName;
        return this;
    }
    public Long getFileSize() {
        return fileSize;
    }
    public ClassfiedFile setFileSize(Long fileSize) {
        this.fileSize = fileSize;
        return this;
    }
    
    
}