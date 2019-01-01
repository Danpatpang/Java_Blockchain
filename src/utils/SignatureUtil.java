package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 전자 서명 생성 기능을 지원.
 */
public class SignatureUtil {
    /**
     * MessageDigest API를 이용하여 입력 값을 SHA-256 알고리즘으로 해시화.
     * 생성된 해시 값은 64byte로 변경되어 반환.
     *
     * @param input (previousHash + data + timeStamp + nonce)
     */
    public static String applySHA256(String input) {
        StringBuffer hexString = new StringBuffer();
        byte[] hash;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(input.getBytes("UTF-8"));

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toString((0xff & hash[i]) + 0x100, 16).substring(1);
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
