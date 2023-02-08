package service.util;

public interface HashUtil {

    String getSalt();
    String doHash(String str1, String str2);
    String convert(byte[] bytes);
}
