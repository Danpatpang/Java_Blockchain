package main;

import com.google.gson.GsonBuilder;
import model.Blockchain;

public class Main {
    public static void main(String[] args) {
        Blockchain danpatChain = new Blockchain();

        danpatChain.add("first block");
        danpatChain.add("second block");
        danpatChain.add("third block");

        String jsonData = new GsonBuilder().setPrettyPrinting().create().toJson(danpatChain.blockchain);
        System.out.println(jsonData);
        System.out.println(danpatChain.isValidChain());
    }
}
