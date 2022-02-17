package park.practiceproject.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
	public static String makeMD5(String str){
		String result;

		try{
			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(str.getBytes());

			byte byteData[] = md.digest();

			StringBuffer sb = new StringBuffer();

			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}

			result = sb.toString();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
			result = null;
		}

		return result;
	}
}
