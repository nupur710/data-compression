package org.example;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HuffmanCompression huffman= new HuffmanCompression();
        var input= new StringBuilder();
        try {
            FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\Gutenberg.txt");
            FileWriter writer= new FileWriter(System.getProperty("user.dir")+"\\src\\main\\resources\\GutenbergEncoded.txt");
            BufferedReader br = new BufferedReader(file);
            BufferedWriter bw= new BufferedWriter(writer);
            int c;
            long start= System.currentTimeMillis();
            while((c= br.read()) != -1) {
                input.append((char) c);
//                System.out.print((char) c);
            }
            String text= huffman.compress(input.toString().toCharArray());
            bw.write(text);
            bw= new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\main\\resources\\GutenbergDecoded.txt"));
            char[] decompressedText= huffman.decompress(text.toString().toCharArray());
            bw.write(decompressedText);
            long end= System.currentTimeMillis();
            System.out.println("original: " + (end - start));
            //huffman.frequencyTable(input.toString().toCharArray());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException i) {
            System.out.println("Error reading file: ");
            i.printStackTrace();
        }
    }
}
