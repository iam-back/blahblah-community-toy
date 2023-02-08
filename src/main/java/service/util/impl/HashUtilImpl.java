package service.util.impl;

import org.springframework.stereotype.Service;
import service.util.HashUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class HashUtilImpl implements HashUtil {

    private static final int SALT_SIZE = 16;

    @Override
    public String getSalt(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] temp = new byte[SALT_SIZE];

        secureRandom.nextBytes(temp);

        return convert(temp);
    }

    @Override
    public String doHash(String str1, String str2){

        String str = str1 + str2;
        byte[] bytes = str.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            for (int i = 1; i < 10000; i++) {
                messageDigest.update(bytes);
                bytes = messageDigest.digest();
            }
        } catch (NoSuchAlgorithmException e){
            e.getStackTrace();
        }
        return convert(bytes);
    }

    @Override
    public String convert(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();

        for(byte b : bytes){
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
}
