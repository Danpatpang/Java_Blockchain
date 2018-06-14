package block;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Generate digital signature
 * 
 * @author Danpatpang
 */
public class StringUtil {
	public static String applySha256(String input) {
		try {
			// 32바이트
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			// 암호화
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			// 16진수화
			for (int i = 0; i < hash.length; i++) {
				// 2바이트 & 1바이트 => 2바이트 32*2 = 64
				String hex = Integer.toHexString(0xff & hash[i]);
				// 01일 경우 1로 인식하므로 (항상 2자리를 만들어 주겠다.)
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
