
package com.yunda.gps.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.*;

public class StreamUtil
{

	public StreamUtil()
	{
	}

	public static byte[] gZip(byte data[])
	{
		byte b[] = null;
		try
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bos);
			gzip.write(data);
			gzip.finish();
			gzip.close();
			b = bos.toByteArray();
			bos.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return b;
	}

	public static byte[] unGZip(byte data[])
	{
		byte b[] = null;
		try
		{
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			GZIPInputStream gzip = new GZIPInputStream(bis);
			byte buf[] = new byte[1024];
			int num = -1;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((num = gzip.read(buf, 0, buf.length)) != -1) 
				baos.write(buf, 0, num);
			b = baos.toByteArray();
			baos.flush();
			baos.close();
			gzip.close();
			bis.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return b;
	}

	public static byte[] zip(byte data[])
	{
		byte b[] = null;
		try
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ZipOutputStream zip = new ZipOutputStream(bos);
			ZipEntry entry = new ZipEntry("zip");
			entry.setSize(data.length);
			zip.putNextEntry(entry);
			zip.write(data);
			zip.closeEntry();
			zip.close();
			b = bos.toByteArray();
			bos.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return b;
	}

	public static byte[] unZip(byte data[])
	{
		byte b[] = null;
		try
		{
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			ZipInputStream zip;
			ByteArrayOutputStream baos;
			for (zip = new ZipInputStream(bis); zip.getNextEntry() != null; baos.close())
			{
				byte buf[] = new byte[1024];
				int num = -1;
				baos = new ByteArrayOutputStream();
				while ((num = zip.read(buf, 0, buf.length)) != -1) 
					baos.write(buf, 0, num);
				b = baos.toByteArray();
				baos.flush();
			}

			zip.close();
			bis.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return b;
	}

	

	public static String bytesToHexString(byte bArray[])
	{
		StringBuffer sb = new StringBuffer(bArray.length);
		for (int i = 0; i < bArray.length; i++)
		{
			String sTemp = Integer.toHexString(0xff & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}

		return sb.toString();
	}
}
