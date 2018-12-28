package model;

import utils.SignatureUtil;

import java.util.Date;

public class Block {
    public String hash; // signature
    public String previousHash;
    private String data;
    private long timeStamp;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculatedHash();
    }

    public String calculatedHash() {
        String input = previousHash + data + Long.toString(timeStamp);

        return SignatureUtil.applySHA256(input);
    }
}