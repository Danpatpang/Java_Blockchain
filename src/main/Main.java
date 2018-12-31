package main;

import com.google.gson.GsonBuilder;
import model.Blockchain;

public class Main {
    public static void main(String[] args) {
        Blockchain danpatChain = new Blockchain();
        int difficulty = 5;

        danpatChain.add("first block");
        System.out.println("try...");
        danpatChain.blockchain.get(0).mineBlock(difficulty);

        danpatChain.add("second block");
        System.out.println("try...");
        danpatChain.blockchain.get(1).mineBlock(difficulty);

        danpatChain.add("third block");
        System.out.println("try...");
        danpatChain.blockchain.get(2).mineBlock(difficulty);

        String jsonData = new GsonBuilder().setPrettyPrinting().create().toJson(danpatChain.blockchain);
        System.out.println(jsonData);
        System.out.println(danpatChain.isValidChain(difficulty));
    }
}
