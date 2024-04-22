package org.example;

import java.util.Scanner;

public class HuffmanCompression {
    //count frequency of each character in text--> insert in frequency table
    //build priority queue with least used freq characters at top
    //build huffman tree
    public static final int CHARACTER_LIMIT= 256; //allowed characters
    public static int[] frequencyTable(char[] text) {
        int[] frequencies= new int[CHARACTER_LIMIT];
        for(int i= 0; i<text.length; i++) {
            frequencies[text[i]]++;
        }
        for(int i= 0; i<frequencies.length; i++) {
            System.out.print(frequencies[i]);
        }
        return frequencies;
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String ip= sc.nextLine();
        char[] bf= ip.toCharArray();
        frequencyTable(bf);
    }
}
