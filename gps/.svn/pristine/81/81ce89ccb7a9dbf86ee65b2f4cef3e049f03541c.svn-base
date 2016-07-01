package autoCreateMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;



public class AutoCreateBean {
	public static void main(String[] args) {
		 String path = AutoCreateBean.class.getResource("").toString();  
	    if (path != null)  
	        {  
	            path = path.substring(6,path.length());//如果是windwos将5变成6  
	            if(path.indexOf("%e9%83%ad%e7%ab%af%e7%ab%af") !=-1){
	            	path = path.replace("%e9%83%ad%e7%ab%af%e7%ab%af", "郭端端");
	            }
				if(path.indexOf("%20")!=-1){
					path = path.replaceAll("%20", " ") ; //本地包含空格的注意替换
			}
	            System.out.println("current path :" + path);  
	        }
		try{
		  List<String> warnings = new ArrayList<String>();  
		  boolean overwrite = true;  
		  File configFile = new File(path+"generator.xml");//获取项目路径，在generator.xml中修改  
		  ConfigurationParser cp = new ConfigurationParser(warnings);  
		  Configuration config = cp.parseConfiguration(configFile);  
		  DefaultShellCallback callback = new DefaultShellCallback(overwrite);  
		  MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);  
		  myBatisGenerator.generate(null); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
