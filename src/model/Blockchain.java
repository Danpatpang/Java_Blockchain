package model;

import java.util.ArrayList;

/**
 * Block 객체로 이루어진 Blockchain을 ArrayList로 구현.
 */
public class Blockchain {
    public ArrayList<Block> blockchain;

    /**
     * Blockchain 초기화.
     */
    public Blockchain() {
        this.blockchain = new ArrayList<Block>();
    }

    /**
     * Blockchain에 추가 Block 생성.
     * 초기 Block이 없을 경우 previousHash 값을 0으로 초기화.
     *
     * @param data 저장할 데이터.
     */
    public void add(String data) {
        if (blockchain.isEmpty()) {
            blockchain.add(new Block(data, "0"));
            return;
        }

        blockchain.add(new Block(data, blockchain.get(blockchain.size() - 1).hash));
    }

    /**
     * 연결된 블록의 유효성 검증.
     * 1. 전자 서명이 동일한지 확인.
     * 2. 연결된 블록이 유효한 블록인지 확인.
     * 3. 해당 블록이 정상적으로 채굴됐는지 확인.
     *
     * @param difficulty 채굴 난이도.
     */
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

            if (!currentBlock.hash.substring(0, difficulty).equals(target)) {
                System.out.println("Block has not mined");
                return false;
            }
        }

        return true;
    }
}
