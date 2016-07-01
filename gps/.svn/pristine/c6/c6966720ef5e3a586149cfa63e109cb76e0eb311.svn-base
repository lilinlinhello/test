package com.yunda.gps.util.ftp;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.yunda.gps.util.PropertiesManager;

public class FTPConfig {
	private static final Logger logger = Logger.getLogger(FTPConfig.class);
	
	private String hostName;

	private Integer port = FTPClient.DEFAULT_PORT;

	private String userName;

	private String password;

	private String savePath;

	private String syst;

	private int byteSize;

	public int getBufferSize() {
		return byteSize;
	}

	public void setByteSize(String byteSize) {
		this.byteSize = Integer.parseInt(byteSize);
	}

	private static FTPConfig ftpConfig = new FTPConfig();

	public String getSyst() {
		return syst;
	}

	public void setSyst(String syst) {
		this.syst = syst;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	private static final String FTP_PREFIX = "ftp.";

	public static FTPConfig newInstance() {
		return ftpConfig;
	}

	private FTPConfig() {
		Map<String, String> pMap = PropertiesManager.getPropertyByPrefix(FTP_PREFIX);
		for (Entry<String, String> entry : pMap.entrySet()) {
			if (!StringUtils.isEmpty(entry.getValue())) {
				try {
					BeanUtils.copyProperty(this, entry.getKey().substring(FTP_PREFIX.length()), entry.getValue());
				}
				catch (Exception e) {
					logger.info(e.getMessage());
				}
			}
		}
	}

	public FTPConfig(String hostName, Integer port, String userName, String password, Integer byteSize) {
		this.hostName = hostName;
		this.port = port;
		this.userName = userName;
		this.password = password;
		this.byteSize = byteSize == null ? 1024 * 1024 : byteSize;
	}
	
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setPort(Integer port) {
		if (port != null) {
			this.port = port;
		}
	}

	public Integer getPort() {
		return port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
