package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        HuffmanCompression huffman= new HuffmanCompression();
        var input= new StringBuilder();
        try {
            FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\Gutenberg.txt");
            BufferedReader br = new BufferedReader(file);
            int c;
            while((c= br.read()) != -1) {
                input.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(input.toString());
        huffman.frequencyTable(input.toString().toCharArray());
    }
}
