package model;

import utils.SignatureUtil;

import java.util.Date;

public class Block {
    public String hash; // signature
    public String previousHash;
    private String data;
    private long timeStamp;
    private long nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculatedHash();
        this.nonce = 0;
    }

    public String calculatedHash() {
        String input = previousHash + data + Long.toString(timeStamp) + Long.toString(nonce);

        return SignatureUtil.applySHA256(input);
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');

        while(!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculatedHash();
        }

        System.out.println("Success mining.");
    }
}