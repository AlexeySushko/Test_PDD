package pdd_project.model;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by Alexey Sushko
 * Класс который предназначен для шифрования паролей, используется со сторонней библиотекой commons-codec-1.10.jar,
 * имеет всего два метода, один для шифрования, второй для дешифрования соответственно
 */
public class Utilities {


    private static final String KEY = "testApp";

    private static byte[] sharedvector = {0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11};

    public static String encryptText(String text){

        String encText = "";
        byte[] keyArray = new byte[24];
        byte[] temporaryKey;
        String key = KEY;
        byte[] toEncryptArray = null;

        try{
            toEncryptArray = text.getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            temporaryKey = messageDigest.digest(key.getBytes("UTF-8"));

            if(temporaryKey.length < 24){
                int index = 0;
                for(int i = temporaryKey.length; i < 24; i++){
                    keyArray[i] = temporaryKey[index];
                }
            }

            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
            byte[] encrypted = c.doFinal(toEncryptArray);
            encText = Base64.encodeBase64String(encrypted);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException noEx) {
            noEx.printStackTrace();
        }

        return encText;
    }

    public static String decryptText(String encText){

        String rawText = "";
        byte[] keyArray = new byte[24];
        byte[] temporaryKey;
        String key = KEY;

        try{
            MessageDigest m = MessageDigest.getInstance("MD5");
            temporaryKey = m.digest(key.getBytes("UTF-8"));

            if(temporaryKey.length < 24){
                int index = 0;
                for(int i=temporaryKey.length; i < 24; i++){
                    keyArray[i] =  temporaryKey[index];
                }
            }

            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
            byte[] decrypted = c.doFinal(Base64.decodeBase64(encText));

            rawText = new String(decrypted, "UTF-8");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException noEx){
            noEx.printStackTrace();
        }

        return rawText;
    }
}
