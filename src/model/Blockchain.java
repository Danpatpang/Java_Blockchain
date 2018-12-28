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

    public Boolean isValidChain() {
        Block currentBlock, previousBlock;

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
        }

        return true;
    }
}
