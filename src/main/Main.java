package main;

import com.google.gson.GsonBuilder;
import model.Blockchain;
import model.Wallet;
import services.Transaction;
import utils.SignatureUtil;

public class Main {
    public static void main(String[] args) {
        Wallet walletA = new Wallet();
        Wallet walletB = new Wallet();

        System.out.println("privateKey & publicKey");
        System.out.println(SignatureUtil.getStringFromKey(walletA.getPrivateKey()));
        System.out.println(SignatureUtil.getStringFromKey(walletA.publicKey));

        Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5);
        transaction.generateSignature(walletA.getPrivateKey());
        System.out.println(transaction.verifySignature());
    }
}
