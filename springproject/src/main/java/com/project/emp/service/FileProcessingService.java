package com.project.emp.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.project.emp.dao.FileProcessingDao;
import com.project.emp.other.ClassfiedFile;
import com.project.emp.other.CodeMap;
import com.project.emp.other.SFTPFileUploader;

@Service
public class FileProcessingService extends AbstractService {
    
    private Logger log = LoggerFactory.getLogger(FileProcessingService.class);
    
    String reqPath = null;
    
    @Autowired
    FileProcessingDao fileProcessingDao;
    
    
    /**
     * 웹 서버용 로컬 파일 다운로드 서비스
     * @param directory Required
     * @param storeFileNames 저장된 파일들(s)
     * */
    public void fileLocalDownloads(String directory) {
        List<String> nullName = null;
        fileLocalDownloads(directory,nullName);
    }
    
    public void fileLocalDownloads(String directory, String storeFileName) {
        List<String>  storeFileList = new ArrayList<String>();
        storeFileList.add(storeFileName);
        fileLocalDownloads(directory,storeFileList);
    }
    
    public void fileLocalDownloads(String directory, List<String> storeFileNames) {
        if(directory==null) {
            return;
        }
        if(reqPath==null) {
            reqPath = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession().getServletContext().getRealPath("/");
        }
        StackTraceElement[] ste = new Throwable().getStackTrace();
        
        String callMethodName = ste[1].getClassName()+"."+ste[1].getMethodName()+"["+ste[1].getLineNumber()+"]";
        String successCode = "File Downloading Success";

        SFTPFileUploader fileServer = new SFTPFileUploader(reqPath + CodeMap.AWS_PWD, CodeMap.AWS_SERVER_HOST);
        fileServer.connection();
        if(storeFileNames==null) {
            Integer success = fileServer.download(CodeMap.AWS_FILE_DEFAULT_CD, reqPath, directory);
            if(success==0) {
                successCode = "File Downloading Failed File Directory : "+directory;
            }
            fileProcessingDao.insertProc("",
                    "directory files",
                    directory, String.valueOf(""), 
                    callMethodName, successCode);
        }
        for(String storeFileName : storeFileNames) {
            Integer success = fileServer.download(CodeMap.AWS_FILE_DEFAULT_CD, reqPath, directory, storeFileName);
            if(success==0) {
                successCode = "File Downloading Failed File Name : "+directory+storeFileName;
            }
            fileProcessingDao.insertProc("",
                    storeFileName,
                    directory, String.valueOf(""), 
                    callMethodName, successCode);
        }
        fileServer.close();
        log.info("서버파일 로컬 다운로드 완료");
        
       
    }
    
    /**
     * 자동 파일 분류 및 파일 업로드 서비스
     * @param file Required
     * @param directory Required 파일 저장할 디렉토리
     * @param storeFileName 존재하지 않으면 자동으로 이름 부여
     * */
    public ClassfiedFile fileClassfication(MultipartFile file, String directory) {
        return fileClassfication(file, directory, null);
    }
    
    public ClassfiedFile fileClassfication(MultipartFile file, String directory, String storeFileName) {
        //자동 파일 분류 처리 서비스
        //웹에서 받아온 파일을 자동으로 이름, 저장할 이름, 파일 사이즈를 계산해서 넣어줌

        //단일 파일 분류시스템 1개의 파일 이름과 파일 저장할 이름등을 분류해서 만들어줌
        //ClassifiedFile 변수명 = FileProcessingService.fileClassfication(멀티파트파일, 저장할 디렉토리);
        //위 방식으로 사용가능
        if(directory==null) {
            return null;
        }
        if(file==null) {
            return null;
        }
        if(reqPath==null) {
            reqPath = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession().getServletContext().getRealPath("/");
        }
        StackTraceElement[] ste = new Throwable().getStackTrace();
        
        String callMethodName = ste[1].getClassName()+"."+ste[1].getMethodName()+"["+ste[1].getLineNumber()+"]";
        String successCode = "File Uploading Success";
        
        ClassfiedFile fileNaming = new ClassfiedFile();
        fileNaming.setFileOriginName(file.getOriginalFilename());
        //확장자 처리
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //저장 파일명이 존재하지 않으면 임의의 문자 부여
        if(CodeMap.isEmpty(storeFileName)) {
            fileNaming.setFileStoreName(UUID.randomUUID().toString().replaceAll("-", "") + extension);
        } else {
            fileNaming.setFileStoreName(storeFileName+extension);
        }
        fileNaming.setFileSize(file.getSize());
        File saveFile = new File(fileNaming.getFileStoreName());
        try {
            SFTPFileUploader fileServer = new SFTPFileUploader(reqPath + CodeMap.AWS_PWD, CodeMap.AWS_SERVER_HOST);
            file.transferTo(saveFile);
            fileServer.connection();
            fileServer.upload(CodeMap.AWS_FILE_DEFAULT_CD + directory, saveFile);
            fileServer.close();
            log.info("파일 업로드 완료");
        } catch (IllegalStateException | IOException e) {
            log.info("파일 업로드 실패 이유 : " + e.getMessage());
            successCode = e.getMessage();
        } finally {
            fileProcessingDao.insertProc(fileNaming.getFileOriginName(),
                    fileNaming.getFileStoreName(),
                    directory, String.valueOf(fileNaming.getFileSize()), 
                    callMethodName, successCode);
        }
        return fileNaming;
    }
    
    //지금은 사용하지 않는 코드(추후에 사용할 수도 있어서 일단 지우지는 않음)
    @Deprecated
    public static File CSVFiling(MultipartFile file, String directory) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        SimpleDateFormat formating = new SimpleDateFormat("yyyy-MM-dd[hhmm]");
        String storeFileNaming = formating.format(new Date()) + "backUpFile" + extension;
        File filing = new File(directory + storeFileNaming);
        filing.mkdirs();
        System.out.println(filing.getAbsolutePath());
        try {
            file.transferTo(filing);
            System.out.println("file업로드 성공");
        } catch (IllegalStateException | IOException e) {
            // TODO Auto-generated catch block
            System.out.println("파일 업로드 실패");
            return null;
        }
        return filing;
    }
    
}
