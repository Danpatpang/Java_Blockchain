package block;
import java.util.Date;
/**
 * Implement the Block.
 * Each Block contains hash, previousHash, data, timeStamp, nonce.
 * Hash is determined by sha-256 algorithm.
 * 
 * @author Danpatpang
 * @date   2018. 06. 15.
 */
public class Block {
	public String hash;
	public String previousHash;
	private String data;
	private long timeStamp;
	private int nonce;
	
	/**
	 * 데이터와 이전 해시 값을 입력받은 후 블록을 초기화.
	 * 현재 해시 값의 경우는 calculateHash() 메서드를 통해서 정의.
	 * 
	 * @param data
	 * @param previousHash
	 */
	public Block(String data, String previousHash) {
		this.previousHash = previousHash;
		this.data = data;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); 
	}

	/**
	 * sha-256 알고리즘을 적용하여 새로운 해시 값을 만들어내는 메서드.
	 * 블록의 모든 정보를 해시화(암호화).
	 * nonce를 이용하여 다른 값으로 계속 바꿀 수 있음.
	 * 
	 * @return calculatHash
	 */
	public String calculateHash() {
		String calculateHash = StringUtil.applySha256(previousHash 
				+ Long.toString(timeStamp) 
				+ Integer.toString(nonce) 
				+ data);
		return calculateHash;
	}
	
	/**
	 * 블록을 채굴하는 메서드.
	 * 채굴의 난이도를 입력받아 문제를 생성.
	 * 해당 문제가 해결될 때까지 hash 값 재계산.
	 * 
	 * @param difficulty
	 */
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0');
		while(!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		System.out.println(nonce);
		System.out.println("Block Mined! : " + hash);
	}
}
