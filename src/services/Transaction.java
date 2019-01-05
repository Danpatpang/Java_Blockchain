package services;

import utils.SignatureUtil;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

public class Transaction {
    private static int sequence = 0;

    public String transactionId;
    public PublicKey sender;
    public PublicKey recipient;
    public float value;
    public byte[] signature;

    public Transaction(PublicKey from, PublicKey to, float value) {
        this.sender = from;
        this.recipient = to;
        this.value = value;
    }

    public void generateSignature(PrivateKey privateKey) {
        String data = SignatureUtil.getStringFromKey(sender)
                + SignatureUtil.getStringFromKey(recipient)
                + Float.toString(value);

        signature = SignatureUtil.applyECDSA(privateKey, data);
    }

    public boolean verifySignature() {
        String data = SignatureUtil.getStringFromKey(sender)
                + SignatureUtil.getStringFromKey(recipient)
                + Float.toString(value);

        return SignatureUtil.verifyECDSA(sender, data, signature);
    }

    private String calculatedHash() {
        sequence++;
        return SignatureUtil.applySHA256(
                SignatureUtil.getStringFromKey(sender)
                        + SignatureUtil.getStringFromKey(recipient)
                        + Float.toString(value)
                        + sequence);
    }
}
