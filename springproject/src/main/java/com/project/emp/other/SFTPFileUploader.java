package com.project.emp.other;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPFileUploader {

    private Logger log = LoggerFactory.getLogger(SFTPFileUploader.class);
    
    public SFTPFileUploader(String pemFile, String publicDNS) {
        super();
        this.pemFile = pemFile;
        this.publicDNS = publicDNS;
    }

    // 파일 주소를 작성한다.
    private String pemFile;
    // 여기에 EC2 instance 도메인 주소를 적는다.
    private String publicDNS;
    private Session session;
    private Channel channel;
    private ChannelSftp channelSftp = null;

    public void connection() {

        try {
            JSch jsch = new JSch();

            String user = "ec2-user";
            String host = publicDNS;
            int port = 22;
            String privateKey = pemFile;

            jsch.addIdentity(privateKey);
            log.info("File Server Upload Initialization 1/3 Password Key Added");

            session = jsch.getSession(user, host, port);
            log.info("File Server Upload Initialization 2/3 Session Created");

            // disabling StrictHostKeyChecking may help to make connection but makes it
            // insecure
            // see
            // http://stackoverflow.com/questions/30178936/jsch-sftp-security-with-session-setconfigstricthostkeychecking-no
            //
            session.setConfig("StrictHostKeyChecking", "no");
            session.setConfig("GSSAPIAuthentication", "no");
            session.setServerAliveInterval(120 * 1000);
            session.setServerAliveCountMax(1000);
            session.setConfig("TCPKeepAlive", "yes");
            
            log.info("File Server Upload Initialization 3/3 Setting Ended");
             
            session.connect();
            
            log.info("File Server Connecting Ended");

            channel = session.openChannel("sftp");
            
            log.info("File Server Channel Opening");

            channel.connect();
            
            log.info("File Server Channel Connecting Ended");
            
            channelSftp = (ChannelSftp) channel;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void upload(String path, File file) {
        log.info("File Server Channel Uploading... File Name : " + path + file.getName());
        FileInputStream in = null;
        try {
            // 파일을 가져와서 inputStream에 넣고 저장경로를 찾아 put
            in = new FileInputStream(file);
            channelSftp.mkdir(path);
            channelSftp.cd(path);
            channelSftp.put(in, file.getName());
            log.info("File Server Channel Uploading Ended File Name : " + path + file.getName());
        } catch (SftpException se) {
            log.error("File Server Channel Uploading Failed File Name : " + path + file.getName() + "\n" + se.getMessage());
        } catch (FileNotFoundException fe) {
            log.error("File Server Channel Uploading Failed error code - nothing File" + "\n" + fe.getMessage());
        } finally {
            try {
                in.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
    
    public Integer download(String defaultPath, String localPath, String dir) {
        return download(defaultPath, localPath, dir, null);
    }
    
    public Integer download(String defaultPath, String localPath, String dir, String fileName) {
        
        try { // 경로탐색후 inputStream에 데이터를 넣음
            channelSftp.cd(defaultPath+dir);
            List<String> fileNames = null;
            if(fileName==null) {
                fileNames = new ArrayList<String>();
                Vector<?> lsFiles = channelSftp.ls(defaultPath);
                Iterator<?> vectorForIter = lsFiles.iterator();
                while(vectorForIter.hasNext()) {
                    LsEntry lsEntry = (LsEntry) vectorForIter.next();
                    fileNames.add(lsEntry.getFilename());
                }
            }
            if(fileName==null) {
                if(!fileNames.isEmpty()) {
                    int result = 0;
                    for(String file : fileNames) {
                        fileDownloadProc(defaultPath, localPath, dir, file);
                        result++;
                    }
                    return result;
                }
            } else {
                return fileDownloadProc(defaultPath, localPath, dir, fileName);
            }
           
        } catch (SftpException se) {
            se.printStackTrace();
            return 0;
        } 
        return 1;
    }
    
    private Integer fileDownloadProc(String defaultPath, String localPath, String dir, String fileName) {
        InputStream in = null;
        try {
            in = channelSftp.get(fileName);
            
            BufferedInputStream bis = new BufferedInputStream(in);
            File downloadFile = new File(localPath+dir+fileName);
            File localDirectory = new File(localPath+dir);
            if(!localDirectory.exists()) {
                localDirectory.mkdirs();
            }
            if(!downloadFile.exists()) {
                downloadFile.createNewFile();
            } else {
                bis.close();
                in.close();
                return 1;
            }
            FileOutputStream fos = new FileOutputStream(downloadFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            
            byte[] buffer = new byte[1024*1024];
            
            int readCount = -1;
            int totalReadCount = 0;
            
            while((readCount = bis.read(buffer))!=-1) {
                totalReadCount += readCount;
                bos.write(buffer);
                log.debug("File Server Download File "+downloadFile.getName()+" >> " +totalReadCount);
            }
            bos.flush();
            bos.close();
            fos.close();
            bis.close();
            in.close();
            
        } catch (SftpException se) {
            se.printStackTrace();
            return 0;
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }


    public void close() {
        log.info("File Server Channel Destroying");
        channelSftp.quit();
        session.disconnect();
        log.info("File Server Channel Destroying Ended");
    };

}
