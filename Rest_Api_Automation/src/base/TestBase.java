package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	
	public TestBase(){
		try{
				prop = new Properties();
				FileInputStream fis= new FileInputStream("C:/Users/A622893/git/Rest_Api_Automation/Rest_Api_Automation/src/config/config.properties");
	//			C:\Users\A622893\git\Rest_Api_Automation\Rest_Api_Automation\src\config\config.properties
				prop.load(fis);
			}
		catch(FileNotFoundException e){
				e.printStackTrace();
			}
		catch(IOException e){
				e.printStackTrace();
			}
	}

}
