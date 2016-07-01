package com.yunda.gps.util.ftp;

import java.io.IOException;

public interface FtpCallback<T> {
	public T dealFtpFile(FTPHandle ftp) throws IOException ;
}
