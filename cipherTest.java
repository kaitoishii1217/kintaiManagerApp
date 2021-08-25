package cipherTest;

import java.math.BigInteger;
import java.security.MessageDigest;

public class cipherTest {

	public static void main(String[] args) {
		
		String text = "This is test";
		
		String cipheredText = getCipher(text);
		
		System.out.println(cipheredText);
		
	}
	
	
	public static String getCipher(String text) {
		
		String cipheredText = null;
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(text.getBytes("utf-8"));
			cipheredText = String.format("%064x", new BigInteger(1,digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cipheredText;
	}

}
