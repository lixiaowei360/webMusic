package com.webMusic.core.util;

import com.webMusic.core.config.UrlConfig;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;



/**
 * ftp上传下载工具类
 * @author	李晓伟
 * @date	2018年3月27日下午8:11:51
 * @version 1.0
 */
public class FtpUtil {
	
	static String host = UrlConfig.ftpHost;
	static Integer port = UrlConfig.ftpFileUploadPort;
	static String username = UrlConfig.ftpUser;
	static String password = UrlConfig.ftpUserPassword;
	
	public static FTPClient getftpClient() throws SocketException, IOException{
		FTPClient ftp = new FTPClient();
		int reply;
		System.out.println("*****************************");
		System.out.println(host+port);
		System.out.println("*****************************");
		ftp.connect(host, port);// 连接FTP服务器
		// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
		ftp.login(username, password);// 登录
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
		}
		return ftp;
	}
	/** 
	 * Description: 向FTP服务器上传文件 
	 * @param host FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param basePath FTP服务器基础目录
	 * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
	 * @param filename 上传到FTP服务器上的文件名 
	 * @param input 输入流 
	 * @return 成功返回true，否则返回false 
	 * @throws IOException 
	 * @throws SocketException 
	 */  
	public static boolean uploadFile( String basePath,
			String filePath, String filename, InputStream input) throws SocketException, IOException {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		
		try {
			ftp = getftpClient();
			
			//切换到上传目录
			if (!ftp.changeWorkingDirectory(basePath+filePath)) {
				//如果目录不存在创建目录
				String[] dirs = filePath.split("/");
				String tempPath = basePath;
				for (String dir : dirs) {
					if (null == dir || "".equals(dir)) continue;
					tempPath += "/" + dir;
					if (!ftp.changeWorkingDirectory(tempPath)) {
						if (!ftp.makeDirectory(tempPath)) {
							return result;
						} else {
							ftp.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			//设置上传文件的类型为二进制类型
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			//上传文件
			if (!ftp.storeFile(filename, input)) {
				return result;
			}
			input.close();
			ftp.logout();
			result = true;
		}  finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}
	
	/** 
	 * Description: 从FTP服务器下载文件 
	 * @param host FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param remotePath FTP服务器上的相对路径 
	 * @param fileName 要下载的文件名 
	 * @param localPath 下载后保存到本地的路径 
	 * @return 
	 */  
	public static boolean downloadFile( String remotePath,
			String fileName, String localPath) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());

					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}

			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}
	
	/**
     * 删除文件-FTP方式
     *
     * @param ftp         FTPClient对象
     * @param ftpPath     FTP服务器上传地址
     * @param ftpFileName FTP服务器上要删除的文件名
     * @return
     */
    public static boolean deleteFile( String ftpPath, String ftpFileName) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
        	ftp = getftpClient();
            ftp.changeWorkingDirectory(ftpPath);//转移到指定FTP服务器目录
            ftpFileName = new String(ftpFileName.getBytes("UTF-8"), "UTF-8");
            ftpPath = new String(ftpPath.getBytes("UTF-8"), "UTF-8");
            ftp.deleteFile(ftpFileName);
            ftp.logout();
            success = true;
        } catch (SocketException e){
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
        return success;
    }
    
	public static void main(String[] args) {
		/*try {  
	        FileInputStream in=new FileInputStream(new File("D:\\temp\\image\\gaigeming.jpg"));  
	        boolean flag = uploadFile("192.168.25.133", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/images","/2015/01/21", "gaigeming.jpg", in);  
	        System.out.println(flag);  
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    }  */
	}
}
