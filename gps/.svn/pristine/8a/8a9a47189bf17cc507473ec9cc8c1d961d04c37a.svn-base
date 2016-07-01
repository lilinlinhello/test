package com.yunda.gps.util.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

public class FTPHandle extends FTPClient {
	private static final Logger logger = Logger.getLogger(FTPHandle.class);

	private static final String fileNameStrReg = "^.*[/\\\\:?|*<>].*$";

	private static final String filePathStrReg = "^.*[\\\\:?|*<>].*$";

	private FTPConfig ftpConfig = FTPConfig.newInstance();

	private boolean fileEncoding = true;

	public FTPHandle() {
		this.getClientInstance();
	}
	
	public FTPHandle(FTPConfig ftpConfig) {
		if (ftpConfig == null) {
			logger.fatal("no ftp config");
		}
		else {
			this.ftpConfig = ftpConfig;
		}
		this.getClientInstance();
	}

	/**
	 * <p>
	 * Discription:[连接ftp，并做相关初始化设置]
	 * </p>
	 */
	private void getClientInstance() {
		try {
			String hostName = ftpConfig.getHostName();
			if (StringUtils.isEmpty(hostName)) {
				logger.fatal("login ftp failure!");
				throw new  RuntimeException("login ftp failure!");
			}
			String[] hostNames = hostName.split(",");
			boolean isUnConnent = false;
			for (int i = 0; i < hostNames.length; i++) {
				try {
					isUnConnent = false; // 初始化 连接失败标志
					super.connect(hostNames[i].trim(), ftpConfig.getPort());// 建立连接
					break;
				}
				catch (Exception e) {
					isUnConnent = true;
				}
			}
			if (isUnConnent) {
				logger.fatal("connect ftp failure!");
				throw new  RuntimeException("connect ftp failure!");
			}
			boolean isLogin = super.login(ftpConfig.getUserName(), ftpConfig.getPassword());// 登陆
			super.setFileType(FTP.BINARY_FILE_TYPE);// 设置默认文件传输类型
			super.setBufferSize(ftpConfig.getBufferSize()); // 设置 传输 速率
			if (!isLogin) {
				logger.fatal("login ftp failure!");
				throw new  RuntimeException("login ftp failure!");
			}
			int reply = super.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				super.disconnect();
				logger.fatal("ftp no reply!");
				throw new  RuntimeException("ftp no reply!");
			}
		}catch (IOException e) {
			if (logger.isInfoEnabled()) {
				logger.info("connect ftp failure!", e);
			}else {
				logger.error("connect ftp failure!");
			}
		}
	}
	public boolean isConn() {
		boolean connected = false;
		try {
			if (isConnected()) {
				setTimeout(60000);
				pwd();
				setTimeout(0);
				connected = true;
			}
		}
		catch (IOException e) {
			logger.info(e.getMessage());
		}
		return connected;
	}

	private void setTimeout(int tout) throws SocketException {
		setSoTimeout(tout);
		if (tout == 0)
			setDataTimeout(-1);
		else
			setDataTimeout(tout);
	}
	
	/**
	 * <p>
	 * Discription:[登出ftp文件服务器]
	 * </p>
	 */
	public void ftpLogout() {
		try {
			if (isConn()) {
				super.logout();
			}
			logger.info("ftp logout!");
		}catch (IOException e) {
			logger.fatal(e.getMessage());
		}
	}
	
	
	/**
	 * <p>
	 * Discription:[方法功能中文描述]
	 * </p>
	 * 
	 * @param is 要上传的本地文件流
	 * @param fileName 文件名称（可为 带路径的文件名称，eg. /temp/today/test.txt）
	 * @return
	 * @throws IOException
	 */
	public Boolean uploadFile(InputStream is, String fileName) throws IOException {
		return this.uploadFile__(is, ftpConfig.getSavePath(), fileName, null);

	}
	/**
	 * <p>
	 * Discription:[上传文件]
	 * </p>
	 * 
	 * @param file 要上传的本地文件对象
	 * @param fileName 文件名称
	 * @return
	 * @throws IOException
	 */
	public Boolean uploadFile(File file, String fileName) throws IOException {
		return this.uploadFile__(new FileInputStream(file), ftpConfig.getSavePath(), fileName, null);
	}
	private Boolean uploadFile__(InputStream is, String workingDir, String fileName, Integer fileType)	throws IOException {
		if (fileName.contains("/")) {
			workingDir += fileName.substring(0, fileName.lastIndexOf("/") + 1);
			fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
		}
		if (!this.checkFileName(fileName) || !this.checkFilePath(workingDir)) {// 判断 fileName 是否合法
			throw new RuntimeException("fileName or  filePath contains the invalid char");
		}

		if (fileType != null) {
			super.setFileType(fileType);
		}

		if (StringUtils.isEmpty(workingDir)) {
			workingDir = "/temp/";
		}

		if (!super.changeWorkingDirectory(workingDir)) {
			this.createDirectory(workingDir);
			super.changeWorkingDirectory(workingDir);
		}
		try {
			return super.storeFile(fileName, is);
		}finally {
			if(null!=is){
				is.close();
			}
		}
	}
	private boolean checkFileName(String fileName) {
		return !StringUtils.isEmpty(fileName) && !fileName.matches(fileNameStrReg);
	}
	private boolean checkFilePath(String filePath) {
		return !StringUtils.isEmpty(filePath) && !filePath.matches(filePathStrReg);
	}
	/**
	 * <p>
	 * Discription:[文件服务器中创建指定文件夹（可递归创建）]
	 * </p>
	 * 
	 * @param workingDir 文件路径格式 ：/my1/today/
	 */
	public void createDirectory(String workingDir) {
		if (!workingDir.startsWith("/")) {
			workingDir = "/" + workingDir;
		}
		String[] wds = workingDir.split("/");
		String tempPath = "";
		boolean isSu = true;
		try {
			for (int i = 1; i < wds.length; i++) {
				isSu = super.makeDirectory(tempPath += ("/" + wds[i]));
			}
		}
		catch (IOException e) {
			if (logger.isInfoEnabled()) {
				logger.info(e.getMessage(), e);
			} else {
				logger.error(e.getMessage());
			}
		}
		if (!isSu) {
			throw new RuntimeException("make remote Directory failure!");
		}
	}
	
	
	
}
