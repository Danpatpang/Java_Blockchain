package chain;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
/**
 * privateKey는 변조하지 않으려는 데이터에 서명할 때 사용
 * publicKey는 서명을 검증하는데 사용 
 * @author Danpatpang
 */
public class Wallet {
	public PrivateKey privateKey;
	public PublicKey publicKey;
	
	public Wallet() {
		generateKeyPair();
	}
	
	public void generateKeyPair() {
		try {
			// keypair 생성
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
			// 난수 생성
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			// ECDSA를 사용하기 위해 사용?
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			
			keyGen.initialize(ecSpec, random);
			KeyPair keyPair = keyGen.generateKeyPair();
			privateKey = keyPair.getPrivate();
			publicKey = keyPair.getPublic();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
