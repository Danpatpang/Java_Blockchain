package model;

import utils.SignatureUtil;

import java.util.Date;

/**
 * Blockchain을 구성.
 * Block은 hash, previousHash, data, timeStamp, nonce 정보를 가지고 있다.
 */
public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private long nonce;

    /**
     * 블록 초기화
     */
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculatedHash();
        this.nonce = 0;
    }

    /**
     * 전자서명 생성.
     */
    public String calculatedHash() {
        String input = previousHash + data + Long.toString(timeStamp) + Long.toString(nonce);

        return SignatureUtil.applySHA256(input);
    }

    /**
     * 난이도에 따른 블록 채굴.
     * 임의의 수를 대입하여 주어진 문제 해결 시 채굴 성공.
     *
     * @param difficulty 채굴 난이도
     */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');

        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculatedHash();
        }

        System.out.println("Success mining.");
    }
}