package com.moneywizzard.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class MWUtils {

	private final static double PI = 3.14159;
	private static final String ALGORITHM = "AES";
	private static final byte[] keyValue = new byte[] { 'S', 'w', 'i', 'n',
			'g', '.', '5', ':', 'a', 'r', 'V', 'U', '<', '>', 'q', 't' };

	/**
	 * Returns GUID
	 * @return GUID
	 */
	public static String generateGUID() {
		UUID idOne = UUID.randomUUID();
		String uuid = idOne.toString();
		uuid = uuid.replaceFirst(".{2}", "MW");
		return uuid;
	}

	/**
	 * Returns current date
	 * @return Date
	 */
	public static Date getcurrentDateTime() {
		Date fromDate = Calendar.getInstance().getTime();
		return fromDate;
	}



	/**
	 * Returns date after adding offset
	 * @param offset
	 * @return
	 */
	public static String calculateExpiryData(Integer offset){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE,offset); // Adding 5 days
		return sdf.format(c.getTime());
	}

	public static String extendExpiryDate(Integer offset,String currentExpiry){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();

		try {
			Date date = sdf.parse(currentExpiry);
			c.setTime(date);
			c.add(Calendar.DATE, offset);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(c.getTime());
	}

	public static Long daysDifference(String fromDay){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		Long difference = null;
		try {
			date = sdf.parse(fromDay);
			String currDateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			Date currDate = sdf.parse(currDateString);	
			long diff = date.getTime() - currDate.getTime();
			difference = diff / (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return difference;
	}

	/**
	 * Return if string value is null or empty
	 * @param data
	 * @return
	 */
	public static boolean isNullOrBlank(String data) {
		boolean status = false;
		if (data == null || data.isEmpty()) {
			status = true;
		}
		return status;
	}

	/**
	 * Converts string to Byte , required while storing in BLOB
	 * @param contentString
	 * @return
	 */
	public static Byte[] convertStringToByte(String contentString) {
		byte[] contentbytes = contentString.getBytes(Charset.forName("UTF-8"));
		Byte[] contentObject = new Byte[contentbytes.length];
		int i = 0;
		for (byte b : contentbytes)
			contentObject[i++] = b; // Autoboxing.

		return contentObject;
	}

	/**
	 * Converts Byte to String
	 * @param byteObjects
	 * @return
	 */
	public static String convertByteToString(Byte[] byteObjects) {
		String string = null;
		if (byteObjects != null) {
			int j = 0;
			byte[] bytes = new byte[byteObjects.length];
			// Unboxing byte values. (Byte[] to byte[])
			for (Byte b : byteObjects)
				bytes[j++] = b.byteValue();

			string = new String(bytes);
		}

		return string;
	}

	/**
	 * Converts decimal degree to radian value
	 * 
	 * @param decimalDegree
	 * @return
	 */
	public static Double convertDegreeToRadian(String decimalDegree) {
		Double radian = null;

		if (!MWUtils.isNullOrBlank(decimalDegree)) {

			radian = (Double.valueOf(decimalDegree) * PI) / 180;
		}
		return radian;
	}

	/**
	 * Converts radian value to degree value
	 * 
	 * @param radianValue
	 * @return
	 */
	public static String convertRadianToDecimal(Double radianValue) {
		String decimalString = null;
		if (radianValue != null) {
			decimalString = String.valueOf((radianValue * 180) / PI);
		}
		return decimalString;

	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGORITHM);
		return key;
	}

	public static String encrypt(String valueToEnc) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encValue = c.doFinal(valueToEnc.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encValue);
		return encryptedValue;
	}


	public static Boolean validateEmailAddress(String emailAddresss){

		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		Boolean isValid = emailAddresss.matches(EMAIL_REGEX);
		return isValid;

	}



	public static void deleteImage(String path) throws IOException,IllegalArgumentException{

		String systemPath = System.getProperty("jboss.server.data.dir");
		String jBossServer = systemPath.substring(0,systemPath.length()-4);

		String filePath=path;
		int indexOfOFF = filePath.lastIndexOf("/");
		filePath=filePath.substring(indexOfOFF+1);

		String dir=filePath.substring(3,8);
		char[] dir1=dir.toCharArray();	
		dir="";	
		for(int j=0;j<dir1.length;j++)
		{
			dir+= dir1[j]+"//";		
		}		

		String serverPath = jBossServer +"//deployments//vv-assets.war//images//"+ dir + filePath;	

		File f = new File(serverPath);
		f.delete();

	}

	public static void deleteLogo(String path) throws IOException,IllegalArgumentException{

		String systemPath = System.getProperty("jboss.server.data.dir");
		String jBossServer = systemPath.substring(0,systemPath.length()-4);

		String filePath=path;
		int indexOfOFF = filePath.lastIndexOf("/");
		filePath=filePath.substring(indexOfOFF+1);

		String dir=filePath.substring(3,8);
		char[] dir1=dir.toCharArray();	
		dir="";	
		for(int j=0;j<dir1.length;j++)
		{
			dir+= dir1[j]+"//";		
		}		

		String serverPath = jBossServer +"//deployments//vv-assets.war//businessLogo//"+ dir + filePath;	

		File f = new File(serverPath);
		f.delete();

	}

	/**
	 * Method returns coupon value in integer, in case of national coupon.
	 * @param couponTitle
	 * @return
	 */
	public static String getCouponValue(String couponTitle){
		String value = "0";
		if(couponTitle!= null){

			//str = "test";
			//check if string contains $ or %
			if(couponTitle.contains("$") || couponTitle.contains("%")){
				//if contains, find index of $ 
				if(couponTitle.toUpperCase().contains("OFF")){
					int indexOfOFF = couponTitle.toUpperCase().indexOf("OFF");
					int startIndex = 0;
					if(indexOfOFF > 6)
						startIndex = indexOfOFF-6;

					String offValue = couponTitle.substring(startIndex,indexOfOFF);
					offValue = offValue.replaceAll("[^0-9]+", " "); 
					List<String> values =  Arrays.asList(offValue.trim().split(" "));

					if(values!= null && values.size() > 0){
						value = values.get(0);
					}		
					return value; 
				}else{

					couponTitle = couponTitle.replaceAll("[^0-9]+", " "); 
					List<String> values =  Arrays.asList(couponTitle.trim().split(" "));			
					if(values!= null && values.size() > 0){
						value = values.get(0);
					}
				}	
			}
		}
		return value;
	}


	/**
	 * Pranay Goyal : 15-04-2015
	 * search for couponId in the views file
	 * @param file
	 * @param couponId
	 * @return
	 */
	public static String searchFileViews(File file,String couponId){
		int i=0;
		Boolean found=false;

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;		
			while ((text = reader.readLine()) != null) {
				i++;
				if(text.isEmpty()){
					continue;
				}
				String as[] = text.split("=");
				if(as[0].equalsIgnoreCase(couponId)){
					found=true;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if(!found){
			return "false";
		}else{
			return i+"";
		}
	}

	/**
	 * Pranay Goyal : 15-04-2015
	 * delete couponId from the views file
	 * @param file
	 * @param startline
	 * @param numlines
	 */
	public static void deleteCouponView(File file, int startline, int numlines){
		try{
			BufferedReader br=new BufferedReader(new FileReader(file));

			//String buffer to store contents of the file
			StringBuffer sb=new StringBuffer("");

			//Keep track of the line number
			int linenumber=1;
			String line;

			while((line=br.readLine())!=null){
				//Store each valid line in the string buffer
				if(linenumber<startline||linenumber>=startline+numlines)
					sb.append(line+"\n");

				linenumber++;
			}
			if(startline+numlines>linenumber)
				System.out.println("End of file reached.");
			br.close();

			FileWriter fw=new FileWriter(file);
			//Write entire string buffer into the file
			fw.write(sb.toString());
			fw.close();
			//System.out.println("Coupon Deleted");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Pranay Goyal : 15-04-2015
	 * Update count of views in views file or create a new record in it
	 * @param file
	 * @param couponId
	 */
	public static void finalUpdateCouponView(File file,String couponId,String startDate){
		int i=0;
		Boolean flag=false;
		Double popularity=1.0;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;		

			if(!isNullOrBlank(startDate)){
				int daydiff=(int)(long)MWUtils.daysDifference(startDate);
				//System.out.println("***************daydiff"+daydiff);
				if(daydiff==0){daydiff=1;}
				float pop =  (1/(float)daydiff);
				//System.out.println("***************pop"+pop);
				popularity= Math.abs(Math.round((pop) * 100.0) / 100.0);
				//System.out.println("***************Popularity"+popularity);
			}
			while ((text = reader.readLine()) != null) {
				i++;
				if(text.isEmpty()){
					continue;
				}
				String as[] = text.split("=");
				if(as[0].equalsIgnoreCase(couponId)){
					updateCouponView(file,text,false,popularity);
					flag=true;
					break;
				}

			}
			if(!flag){		
				text=couponId+"="+(1+popularity);
				updateCouponView(file,text,true,popularity);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}


	/**
	 * @param file
	 * @param str
	 * @param isNew
	 * @throws IOException
	 */
	public static void updateCouponView(File file,String str,Boolean isNew,Double popularity) throws IOException{

		if(isNew){
			FileWriter fileWriter = new FileWriter(file,true);
			BufferedWriter bufferFileWriter  = new BufferedWriter(fileWriter);
			fileWriter.append("\n"+str);
			bufferFileWriter.close();
			//System.out.println("Coupon added");
		}else{
			Path path = Paths.get(file.getAbsolutePath());
			Charset charset = StandardCharsets.UTF_8;

			String as[] = str.split("=");
			String updateString=as[0]+"="+(Double.parseDouble(as[1])+popularity);

			String content = new String(Files.readAllBytes(path), charset);
			content = content.replaceAll(str, updateString);
			Files.write(path, content.getBytes(charset));
			//System.out.println("Coupon Updated");
		}

	}


	/**
	 * @param str
	 * @param list
	 * @return
	 */
	public static boolean containsCaseInsensitive(String str, List<String> list){
		for (String string : list){
			if (string.equalsIgnoreCase(str)){
				return true;
			}
		}
		return false;
	}


	public static String getNumberValue(String a) {

		if(a != null || a!=""){
			a = a.replaceAll(",", "");
			Pattern p = Pattern.compile("-?\\d+");
			Matcher m = p.matcher(a);
			if(m.find()) {
				return m.group();

			}  
		}
		return null;
	}

}
