package com.yunda.gps.common.fileUD.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yunda.app.entity.vo.LoginUser;
import com.yunda.app.util.StaticVar;
import com.yunda.gps.common.fileUD.bean.Documents;
import com.yunda.gps.common.fileUD.dao.FileUDDao;
import com.yunda.gps.common.fileUD.service.FileUDService;
import com.yunda.gps.util.DateUtil;
import com.yunda.gps.util.FileType;
import com.yunda.gps.util.FileTypeJudge;
import com.yunda.gps.util.KeyUtil;
import com.yunda.gps.util.PropertiesManager;
import com.yunda.gps.util.ftp.FTPConfig;

/**
 * 文件上传操作 业务实现类
 *
 */
@Service(value="fileUDService")
@Transactional
public class FileUDServiceImpl implements FileUDService{
	@Resource(name="fileUDDao")
	private FileUDDao fileUDDao;
	Log log = LogFactory.getLog(FileUDServiceImpl.class);	

	
	/**
	 * 上传文件至FTP,记录文档信息(不限制上传文件类型)
	 * @param fileName : 用户上传的文件名称
	 */
	public List<String> upload(List<MultipartFile> files, HttpSession session) {
		List<String> ids = new ArrayList<String>();
		if (files != null && files.size()>0) {
			LoginUser user = (LoginUser) session.getAttribute(StaticVar.LOGIN_USER_KEY);
		//	FTPConfig ftpConfig = FTPConfig.newInstance();
			String savePath = PropertiesManager.getProperty("ftp.savePath")+"";
            for (int i = 0; i < files.size(); i++) {
            	MultipartFile file = files.get(i);
            	if(null!=file && file.getSize()>0){
            		String id = KeyUtil.getIdByTime(3);
            		String fileName = file.getOriginalFilename() ;
            		InputStream fis =null;
            		try {
            			String fix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
						String saveName = id+fix; //保存文件名
//            			 boolean t = FTPUtil.uploadFile(file.getInputStream(),fileName);
            			@SuppressWarnings("resource")
						FileOutputStream fos=new FileOutputStream(savePath+"\\"+fileName);
            			byte[] buffer=new byte[1024];
//            			FileInputStream fis = new FileInputStream(file.getInputStream());
            		    fis = file.getInputStream();
            			int len=0;
            			while((len=fis.read(buffer))>0){
            			  fos.write(buffer,0,len);
            				}
            			recordDocInfos(id,ids, null, fileName, saveName, user.getUserId());
            		} catch (IOException e) {
            			e.printStackTrace();
            		}finally{
            			if(fis !=null){
            				try {
								fis.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
            			}
            		}
            	}
            }
        } 
		return ids ;
	}
	/**
	 * 上传文件至FTP,记录文档信息(限制上传文件类型为常用基本图片:JPEG,PNG,GIF,TIFF,BMP)
	 * @param fileName : 用户上传的文件名称
	 * @throws IOException 
	 */
	public Map<String,Object> uploadOfPictures(List<MultipartFile> files, HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("errMsg","");
		List<String> ids = new ArrayList<String>();
		if (files != null && files.size()>0) {
			LoginUser user = (LoginUser) session.getAttribute(StaticVar.LOGIN_USER_KEY);
//			FTPConfig ftpConfig = FTPConfig.newInstance();
			String savePath = PropertiesManager.getProperty("ftp.savePath")+"";
			InputStream fis =null;
            for (int i = 0; i < files.size(); i++) {
            	MultipartFile file = files.get(i);
            	if(null!=file && file.getSize()>0){
            		// 判断文件类型，(生成缩略图，后期优化......)
            		try {
            			FileType type = FileTypeJudge.getType(file.getInputStream());
            			String name = type.name() ;
						if(name.equals("JPEG") || name.equals("PNG") || name.equals("GIF")||
								name.equals("TIFF") || name.equals("BMP")){
							String id = KeyUtil.getIdByTime(3);
							String fileName = file.getOriginalFilename() ;
							//BASE64Encoder encoder = new BASE64Encoder();
							//String tem = fileName.substring(0,fileName.lastIndexOf("."));
							String fix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
							//服务器上存放名称进行加密(为了避免中文乱码问题)
							String saveName = id+fix;
//							boolean t = FTPUtil.uploadFile(file.getInputStream(),saveName) ;
//	            			if(t){
//	            				recordDocInfos(id,ids,ftpConfig,fileName,saveName,user.getUserId()) ;
//	            			} 
						//	FileOutputStream fos=new FileOutputStream(savePath+"\\"+fileName);
							String filePath = savePath+"/"+saveName ;
							file.transferTo(new File(filePath));  
	            			recordDocInfos(id,ids, null, fileName, saveName, user.getUserId());
						}else{
							map.put("errMsg","目前系统仅支持如下几种常用基本图片类型:JPEG,PNG,GIF,TIFF,BMP");
							break ;
						}
					} catch (Exception e) {
						map.put("errMsg","上传附件失败!");
						e.printStackTrace();
						log.fatal(e.getMessage());
					}finally{
						if(fis !=null){
            				try {
								fis.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
            			}
					}
            	}
            }
            map.put("ids",ids);
        }else{
        	map.put("errMsg","");
        } 
		return map ;
	}
	/**
	 * 上传成功后记录上传文件信息
	 * @param id
	 * @param ids
	 * @param ftpConfig
	 * @param fileName
	 * @param saveName
	 * @param userId
	 */
	private void recordDocInfos(String id,List<String> ids,FTPConfig ftpConfig,String fileName,String saveName,String userId){
		//String id = KeyUtil.getIdByTime(3);
		ids.add(id);
		Documents doc = new Documents();
		doc.setId(id);
		doc.setDocumentName(fileName);
		doc.setDocumentAddress(PropertiesManager.getProperty("ftp.photos.address")+"/"+saveName); //保存路径直接获取
		doc.setCreateTime(DateUtil.getDateYMDHMs(new Date()));
		doc.setCreateBy(userId); // 从session 中获取登陆用户
		doc.setDeleteFlag(0);
		fileUDDao.saveUpInfos(doc);
	}
	
	/**
	 * 通过id加载文档名称
	 * @param id
	 * @return Map<String,String>
	 */
	public List<String> getFileNameByIds(String docIds){
		List<String> list = new ArrayList<String>();
		if(null!=docIds && !"".equals(docIds)){
			list = fileUDDao.getFileNameByIds(docIds.split(","));
		}
		return list ;
	}
	/**
	 * 通过ID加载文档FTP存放地址
	 * @param docIds
	 * @return
	 */
	public List<String> getFilePathByIds(String docIds){
		List<String> list = new ArrayList<String>();
		if(null!=docIds && !"".equals(docIds)){
			list = fileUDDao.getFilePathByIds(docIds.split(","));
		}
		return list ;
	}
}
