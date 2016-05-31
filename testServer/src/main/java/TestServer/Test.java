package src.main.java.TestServer;

import src.main.java.TestServer.datamodel.*;
import java.util.ArrayList;
import java.util.Iterator;
import com.google.gson.Gson;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
import java.io.FileOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Test {
    public void testLogin() {
        DataOperationPost dataOp = new DataOperationPost();
        dataOp.setUrl(dataOp.getBaseUrl() + "/user/login");
        String json1 = "{\"username\": \"1234\", \"cocopassword\": \"12345\"}";
        String json2 = "{\"username\": \"1234\", \"password\": \"12345\"}";
        String json3 = "{\"username\": \"tiankk\", \"password\": \"tiankk\"}";
        try {
        	dataOp.setJson(json1);
        	System.out.println(dataOp.Do());

        	dataOp.setJson(json2);
        	// System.out.println(dataOp.Do());

        	dataOp.setJson(json3);
        	System.out.println(dataOp.Do());
    	} catch (Exception e) {
    		System.out.println("Exception");
    	}
    }
    
    public void testRegister() {
        DataOperationPost dataOp = new DataOperationPost();
        dataOp.setUrl(dataOp.getBaseUrl() + "/user/register");
        String json3 = "{\"username\": \"tiankk\", \"password\": \"tiankk\"," +  
                " \"gender\": 1, \"phone\": \"18819473274\", \"tags\": \"action\"}";
        try {
        	dataOp.setJson(json3);
        	System.out.println(dataOp.Do());
    	} catch (Exception e) {
    		System.out.println("Exception");
    	}
    }
    
    public void testMovie() {
        DataOperationGet dataOp = new DataOperationGet();
	    dataOp.setUrl(dataOp.getBaseUrl() + "/movie/onView");
    	String response = "";
	    try {
            response = dataOp.Do();
	    } catch (Exception e) {
	        System.out.println("Exception");
	    }
	    // System.out.println(response);
	    MovieList list = new MovieList();
	    try {
	        list = new Gson().fromJson(response, MovieList.class);
	    } catch (Exception e) {
	        System.out.println("exception deserialize");
	    }
	    for (int i = 0; i < list.size(); i++) {
	        System.out.println(list.get(i).getName() + " , " + list.get(i).getIntro());
            deserialize(list.get(i).getAvatar(), "pic2.png");
	    }
    }
	
	public void testTheater() {
        DataOperationGet dataOp = new DataOperationGet();
	    dataOp.setUrl(dataOp.getBaseUrl() + "/theater/nearby");
    	String response = "";
	    try {
            response = dataOp.Do();
	    } catch (Exception e) {
	        System.out.println("Exception");
	    }
	    // System.out.println(response);
	    TheaterList list = new TheaterList();
	    try {
	        list = new Gson().fromJson(response, TheaterList.class);
	    } catch (Exception e) {
	        System.out.println("exception deserialize");
	    }
	    for (int i = 0; i < list.size(); i++) {
	        System.out.println(list.get(i).getName() + " , " + list.get(i).getLocation()+ "," +list.get(i).getPrice());
	        /*if (deserialize(list.get(i).getPrice()))*/
	            System.out.println("succeed");
	    }
    }

    public boolean deserialize(String imgStr, String filePath) {
        //对字节数组字符串进行Base64解码并生成图片
        // System.out.println(imgStr);
        if (imgStr == null) //图像数据为空  
            return false;  
        BASE64Decoder decoder = new BASE64Decoder();
        try {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i) {  
                if(b[i]<0) {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成图片   
            OutputStream out = new FileOutputStream(filePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        } catch (Exception e) {  
            return false;  
        }
    }
}
