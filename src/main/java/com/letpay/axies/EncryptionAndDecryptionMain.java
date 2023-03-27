package com.letpay.axies;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionAndDecryptionMain {
    public static String encrypt(String input, String key) {

        byte[] crypted = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes());
            crypted = Base64.getEncoder().encode(encryptedBytes);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new String(crypted);

    }
    public static String decrypt(String input, String key){
        byte[] output = null;
        try{
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(Base64.getDecoder().decode(input));
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return new String(output);
    }

    public static String checkSum(String input) throws NoSuchAlgorithmException
    {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(input.getBytes());

        byte byteData[] = md.digest();

        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<byteData.length;i++) {
            String hex=Integer.toHexString(0xff & byteData[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
//        System.out.println("Hex format : " + hexString.toString());
        return  hexString.toString();
    }

}
