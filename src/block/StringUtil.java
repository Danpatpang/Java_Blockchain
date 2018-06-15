package block;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Generate digital signature.
 * Using sha-256 algorithm.
 * Input string converts 64bit hexadecimal String.
 * 
 * @author Danpatpang
 * @date   2018.06.14.
 */
public class StringUtil {
	/**
	 * 입력 값에 sha-256 알고리즘을 적용하는 메서드.
	 * 알고리즘이 적용된 데이터를 각각 16진수화.
	 * 16진수로 포맷팅 후 64bit를 hexString에 저장.
	 * hexString을 String 타입으로 반환.
	 * 
	 * @param input
	 * @return hexString.toString()
	 */
	public static String applySha256(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				/* 포맷팅 (8 bit & n bit => 8 bit) */
				String hex = Integer.toHexString(0xff & hash[i]);
				/* 값이 0x10 미만일 경우 2자리로 통일하기 위해 '0'을 추가 */
				if(hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
