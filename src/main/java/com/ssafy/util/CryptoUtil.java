package com.ssafy.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {
	
	private String ips;
	private Key keySpec;
	
	public CryptoUtil(String key) {
		try {
            byte[] keyBytes = new byte[16];
            byte[] b = key.getBytes("UTF-8");
            System.arraycopy(b, 0, keyBytes, 0, keyBytes.length);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            this.ips = key.substring(0, 16);
            this.keySpec = keySpec;
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	public String encrypt(String str) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec,
                    new IvParameterSpec(ips.getBytes()));
 
            byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
            String Str = new String(Base64.getEncoder().encodeToString(encrypted));
 
            return Str;
        } catch (Exception e) {
        	return null;
        }
    }
 
    public String decrypt(String str) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec,
                    new IvParameterSpec(ips.getBytes("UTF-8")));
 
            byte[] byteStr = Base64.getDecoder().decode(str.getBytes());
            String Str = new String(cipher.doFinal(byteStr), "UTF-8");
 
            return Str;
        } catch (Exception e) {
            return null;
        }
    }
	 

	public static void main(String[] args) throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException,
			IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException {
		String key = "HappyHouse_Algorithm!";
		
		CryptoUtil aes = new CryptoUtil(key);
		
		
		String txt = "Hello World";
		String encrypt = aes.encrypt(txt);
		String decrypt = aes.decrypt(encrypt); 
		
		
		System.out.println("평문 : " + txt);
		System.out.println("암호화 : " + encrypt);
		System.out.println("복호화 : " + decrypt);
	}

}
