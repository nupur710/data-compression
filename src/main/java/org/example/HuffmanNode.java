package org.example;

public class HuffmanNode {
    public char ch;
    public int freq;
    public HuffmanNode leftChild= null;
    public HuffmanNode rightChild= null;
    HuffmanNode(char ch, int freq) {
        this.ch= ch;
        this.freq= freq;
    }
}
