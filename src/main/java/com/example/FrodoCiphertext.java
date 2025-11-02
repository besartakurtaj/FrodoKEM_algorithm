package com.example;

public class FrodoCiphertext {
  private final byte[] ciphertext;

    public FrodoCiphertext(byte[] ciphertext) {
        this.ciphertext = ciphertext;
    }

    public byte[] getCiphertext() {
        return ciphertext;
    }

}
