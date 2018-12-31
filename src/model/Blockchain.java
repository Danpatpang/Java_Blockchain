package model;

import java.util.ArrayList;

public class Blockchain {
    public ArrayList<Block> blockchain;

    public Blockchain() {
        this.blockchain = new ArrayList<Block>();
    }

    public void add(String data) {
        if (blockchain.isEmpty()) {
            blockchain.add(new Block(data, "0"));
            return;
        }

        blockchain.add(new Block(data, blockchain.get(blockchain.size() - 1).hash));
    }

    public Boolean isValidChain(int difficulty) {
        Block currentBlock, previousBlock;
        String target = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            if (!currentBlock.hash.equals(currentBlock.calculatedHash())) {
                System.out.println("Hash is not equal");
                return false;
            }

            if (!currentBlock.previousHash.equals(previousBlock.hash)) {
                System.out.println("PreviousHash is not equal");
                return false;
            }

            if(!currentBlock.hash.substring(0, difficulty).equals(target)){
                System.out.println("Block has not mined");
                return false;
            }
        }

        return true;
    }
}
