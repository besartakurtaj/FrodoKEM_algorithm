package com.example;

import org.bouncycastle.pqc.crypto.frodo.FrodoPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoPublicKeyParameters;

public class FrodoKeyPair {
    private final FrodoPublicKeyParameters publicKey;
    private final FrodoPrivateKeyParameters privateKey;

    public FrodoKeyPair(FrodoPublicKeyParameters publicKey, FrodoPrivateKeyParameters privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public FrodoPublicKeyParameters getPublicKey() { return publicKey; }
    public FrodoPrivateKeyParameters getPrivateKey() { return privateKey; }
}
