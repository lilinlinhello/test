package com.yunda.gps.util.ftp;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

/***
 * FTP操作工具类
 * @author luogengxian
 * @date:2015-08-02
 */
public class FTPUtil {
	private static final Logger logger = Logger.getLogger(FTPUtil.class);
	public static <T> T openFtpInterface(FtpCallback<T> ftpCallback) {
		return openFtpInterface(ftpCallback, null);
	}
	public static <T> T openFtpInterface(FtpCallback<T> ftpCallback, FTPConfig ftpConfig) {
		logger.info("start ftp connect");
		FTPHandle ftp = newInstance(ftpConfig);
		try {
			return ftpCallback.dealFtpFile(ftp);
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally {
			if (ftp != null) {
				ftp.ftpLogout();
			}
		}
	}
	private static FTPHandle newInstance(FTPConfig ftpConfig) {
		if (ftpConfig == null) {
			return new FTPHandle() ;
		}
		return new FTPHandle(ftpConfig) ;

	}
	
	public static Boolean uploadFile(final InputStream is, final String fileName) {
		return openFtpInterface(new FtpCallback<Boolean>() {
			public Boolean dealFtpFile(FTPHandle ftp) throws IOException {
				return ftp.uploadFile(is, fileName);
			}
		});
	}
	
}
