package block;

import java.util.ArrayList;
import com.google.gson.*;

/**
 * Create the Block chain and try to mine each Block.
 * After verifying, output data to gson.
 * 
 * @author Danpatpang
 * @date   2018.06.15.
 */
public class BlockTest {
	public static ArrayList<Block> chain = new ArrayList<Block>();
	public static int difficulty = 5;
	
	/**
	 * 블록의 유효성을 체크하는 메서드.
	 * 1. 현재 블록의 해시 값이 유효한가?
	 * 2. 이전 블록의 해시 값이 유효한가? 
	 * 3. 채굴의 결과 값이 유효한가?
	 * @return
	 */
	public static boolean isChainValid() {
		Block previousBlock;
		Block currentBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		for (int i = 1; i < chain.size(); i++) {
			previousBlock = chain.get(i-1);
			currentBlock = chain.get(i);
			
			if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current hash is not equal");
				return false;
			}
			if(!previousBlock.hash.equals(currentBlock.previousHash)){
				System.out.println("Previous hash is not equal");
				return false;
			}
			if(!currentBlock.hash.substring(0,difficulty).equals(hashTarget)) {
				System.out.println("This block has not been mined");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * main
	 */
	public static void main(String[] args) {
		chain.add(new Block("generate", "0"));
		System.out.println("Trying to Mine Block 1...");
		chain.get(0).mineBlock(difficulty);
		
		chain.add(new Block("second", chain.get(chain.size()-1).hash));
		System.out.println("Trying to Mine Block 2...");
		chain.get(1).mineBlock(difficulty);
		
		chain.add(new Block("third", chain.get(chain.size()-1).hash));
		System.out.println("Trying to Mine Block 3...");
		chain.get(2).mineBlock(difficulty);
		
		System.out.println("chain is valid :" + isChainValid());
		String chainJson = new GsonBuilder().setPrettyPrinting().create().toJson(chain);
		System.out.println(chainJson);
	}
}
