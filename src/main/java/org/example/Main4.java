package org.example;

import java.io.*;
import java.util.BitSet;

public class Main4 {

    public static void main(String[] args) throws IOException  {
        HuffmanCompression huffmanCompression= new HuffmanCompression();
        StringBuilder text= new StringBuilder();
        FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\Gutenberg.txt");
        BufferedReader br = new BufferedReader(file);
        int c= 0;
        while((c= br.read()) != -1) {
            text.append((char) c);
        }
        String input= huffmanCompression.compress(text.toString().toCharArray());
        char[] ch= input.toCharArray();
        //char[] ch = {'1', '0', '1', '0', '0', '0', '1', '1', '1'};
        BitSet bitSet= new BitSet(ch.length);
        System.out.println(bitSet.length());
        for(int i= 0; i<ch.length; i++) {
            if(ch[i] == '1') {
                bitSet.set(i);
            }
        }
        writeBitSetToFile(bitSet, "write_bitset_to_file.txt");

        char[] x= new char[bitSet.length()];
        for(int i= 0; i < bitSet.length(); i++) {
            if(bitSet.get(i)) {
                x[i]= '1';
            } else {
                x[i]= '0';
            }
        }

    }

    public static void writeBitSetToFile(BitSet bitSet, String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(bitSet);
        oos.close();
        fos.close();
        System.out.println("BitSet written to file: " + filename);
    }
}
