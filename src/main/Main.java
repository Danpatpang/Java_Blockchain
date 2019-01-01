package main;

import com.google.gson.GsonBuilder;
import model.Blockchain;

public class Main {
    public static void main(String[] args) {
        Blockchain danpatChain = new Blockchain();
        int difficulty = 5;

        // 블록 채굴
        danpatChain.add("first block");
        System.out.println("try...");
        danpatChain.blockchain.get(0).mineBlock(difficulty);

        danpatChain.add("second block");
        System.out.println("try...");
        danpatChain.blockchain.get(1).mineBlock(difficulty);

        danpatChain.add("third block");
        System.out.println("try...");
        danpatChain.blockchain.get(2).mineBlock(difficulty);

        // 체인 조회
        String jsonData = new GsonBuilder().setPrettyPrinting().create().toJson(danpatChain.blockchain);
        System.out.println(jsonData);

        // 유효성 검증
        System.out.println(danpatChain.isValidChain(difficulty));
    }
}
