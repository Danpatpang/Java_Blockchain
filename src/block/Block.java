package block;
import java.util.Date;
/**
 * Making the block
 * 
 * @author Danpatpang
 */
public class Block {
	public String hash;
	public String previousHash;
	private String data;
	private long timeStamp;
	
	public Block(String data, String previousHash) {
		this.previousHash = previousHash;
		this.data = data;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); 
	}
	
	public String calculateHash() {
		String calculateHash = StringUtil.applySha256(previousHash+Long.toString(timeStamp)+data);
		return calculateHash;
	}
	
}
