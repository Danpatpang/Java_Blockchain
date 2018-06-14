package block;

import java.util.ArrayList;
import com.google.gson.*;

public class testing {
	public static ArrayList<Block> chain = new ArrayList<Block>();
	
	public static void main(String[] args) {
		chain.add(new Block("generate", "0"));
		chain.add(new Block("second", chain.get(chain.size()-1).hash));
		chain.add(new Block("third", chain.get(chain.size()-1).hash));
		
		String chainJson = new GsonBuilder().setPrettyPrinting().create().toJson(chain);
		System.out.println(chainJson);
	}
	
	public static boolean isValidChain() {
		return true;
	}
}
