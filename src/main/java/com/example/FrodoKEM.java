package com.example;

import org.bouncycastle.pqc.crypto.frodo.*;
import org.bouncycastle.pqc.crypto.*;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.pqc.crypto.frodo.FrodoPublicKeyParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoPrivateKeyParameters;
import org.bouncycastle.util.encoders.Hex;
import java.security.SecureRandom;
import java.util.Arrays;

public class FrodoKEM {

    public void runDemo() {
        try {

            FrodoParameters params = FrodoParameters.frodokem640aes;
            System.out.println("Using Frodo parameter set: " + params.getName());
            
            // Generate key pair
            FrodoKeyPairGenerator keyGen = new FrodoKeyPairGenerator();
            keyGen.init(new FrodoKeyGenerationParameters(new SecureRandom(), FrodoParameters.frodokem640aes));

            AsymmetricCipherKeyPair keyPair = keyGen.generateKeyPair();

            FrodoPublicKeyParameters publicKey = (FrodoPublicKeyParameters) keyPair.getPublic();
            FrodoPrivateKeyParameters privateKey = (FrodoPrivateKeyParameters) keyPair.getPrivate();

            FrodoKEMGenerator encapsulator = new FrodoKEMGenerator(new SecureRandom());
            SecretWithEncapsulation secretEnc = encapsulator.generateEncapsulated(publicKey);

            byte[] sharedEnc = secretEnc.getSecret();
            byte[] ciphertext = secretEnc.getEncapsulation();

            FrodoKEMExtractor extractor = new FrodoKEMExtractor(privateKey);
            byte[] sharedDec = extractor.extractSecret(ciphertext);

            System.out.println("Ciphertext: " + Hex.toHexString(ciphertext));
            System.out.println("Shared key (encapsulator): " + Hex.toHexString(sharedEnc));
            System.out.println("Shared key (decapsulator): " + Hex.toHexString(sharedDec));
            System.out.println(Arrays.equals(sharedEnc, sharedDec)
                    ? "Keys match!" : "Keys differ!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
