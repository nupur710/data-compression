package org.example;

public class HuffmanNode {
    public char ch;
    public int freq;
    HuffmanNode leftChild= null;
    HuffmanNode rightChid= null;
    HuffmanNode(char ch, int freq) {
        this.ch= ch;
        this.freq= freq;
    }
}
