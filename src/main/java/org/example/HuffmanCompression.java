package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCompression {
    //count frequency of each character in text--> insert in frequency table
    //build priority queue with least used freq characters at top
    //build huffman tree
    public static final int CHARACTER_LIMIT= 65536; //allowed characters
    public StringBuilder header= new StringBuilder();
    public static int[] frequencyTable(char[] text) {
        int[] frequencies= new int[CHARACTER_LIMIT];
        for(int i= 0; i<text.length; i++) {
            frequencies[text[i]]++;
        }
        return frequencies;
    }

    public PriorityQueue<HuffmanNode> createPriorityQueue(int[] frequencies) {
        header= new StringBuilder(); //reset header in case this method is called repeadtedly
        header.append((char) 1); //SOH
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>(new FrequencyComparator());
        //add in priority queue
        //nodes added in ascending order of freq
        for(int i= 0; i<frequencies.length; i++) {
            if(frequencies[i] > 0) {
                //whatever is added first, remains on top in priorityqueue
                queue.add(new HuffmanNode((char) i, frequencies[i]));
                header.append(":").append((char) i).append(frequencies[i]);
            }
        }
        header.append((char) 2); //STX
        return queue;
    }

    public HuffmanNode createHuffmanTree(PriorityQueue<HuffmanNode> queue) {
        HuffmanNode root= null;
        while(queue.size() > 0) {
            root= getLeastFrequencyNode(queue);
//            System.out.println(root);
//            System.out.println("root with least freq is " + root.ch + " " + root.freq);
            //add root back to queue only if queue > 0. Once queue size becomes 0, the compelete tree has been built
            if(queue.size() > 0) queue.add(root);
        }
        return root;
    }

    public HuffmanNode getLeastFrequencyNode(PriorityQueue<HuffmanNode> queue) {
        HuffmanNode node1 = queue.poll();
//        System.out.println("extracting " + node1.ch);
        HuffmanNode node2 = queue.poll();
//        System.out.println("extracting " + node2.ch);
        HuffmanNode root= new HuffmanNode('-', node1.freq+node2.freq);
        //left child freq is less than right child
        root.leftChild= node1;
        root.rightChild= node2;
        return root;
    }
    public String compress(char[] text) {
        int[] frequencies= frequencyTable(text);
        PriorityQueue<HuffmanNode> queue= createPriorityQueue(frequencies);
        HuffmanNode node1= createHuffmanTree(queue);
        String compressedData= header.toString() + encode(text, node1);
        return compressedData;
    }

    public char[] decompress(char[] text) {
        if(text[0] != (char) 1) return null;
        int[] frequencies= parseHeaderAsFreq(text);
        PriorityQueue<HuffmanNode> queue= createPriorityQueue(frequencies);
        HuffmanNode node1= createHuffmanTree(queue);
        String decompresedText= decode(text, node1);
        return decompresedText.toCharArray();
    }

    public void writeHeaderToFile(String header) {
        try {
            BufferedWriter writer= new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\src\\main\\resources\\final_1.txt"));
            writer.write(header);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String encode(char[] text, HuffmanNode node1) {
        var str= new StringBuilder();
        String[] arr= new String[CHARACTER_LIMIT];
        generateBits(arr, node1, new StringBuilder());
        for(int i= 0; i<text.length; i++) {
            str.append(arr[text[i]]);
        }
        return str.toString();
    }


    public int[] parseHeaderAsFreq(char[] encodedText) {
        int[] frequencies= new int[CHARACTER_LIMIT];
        int i= 0;
        for(; i<encodedText.length && encodedText[i] != (char) 2; i++) {
            header.append(encodedText[i]);
            if(encodedText[i] == ':') {
                i++;
                header.append(encodedText[i]);
                int f= 0;
                int m= 1;
                int j= i+1;
                for(; j<encodedText.length && encodedText[j] != (char) 2 && encodedText[j] != ':'; j++) {
                    f= (f * m) + (encodedText[j] - '0');
                    if(f != 0) m= 10;
                    header.append(encodedText[i] - '0');
                }
                frequencies[encodedText[i]]= f;
                i= j-1;
            }
        } return frequencies;
    }

    public String  decode(char[] text, HuffmanNode node1) {
        int i = header.length();
        StringBuilder originalText = new StringBuilder();
        HuffmanNode node2 = node1;
        while (i < text.length) {
            if (text[i] == '0') {
                node2 = node2.leftChild;
            } else if (text[i] == '1') {
                node2 = node2.rightChild;
            }
            if (node2.leftChild == null && node2.rightChild == null) {
                originalText.append(node2.ch);
                node2 = node1; //reset
            }
            i++;
        }
        return originalText.toString();
    }

    public void generateBits(String[] array, HuffmanNode root, StringBuilder s) {
        if (root == null) {
            return;
        }
        if(root.ch == '-') {
            s.append('0');
            generateBits(array, root.leftChild, s);
            s.deleteCharAt(s.length() - 1);
            s.append('1');
            generateBits(array, root.rightChild, s);
            s.deleteCharAt(s.length() - 1);
        } else {
            System.out.println(root.ch + " - " + s.toString());
            array[root.ch] = s.toString();
        }
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String ip= sc.nextLine();
        char[] bf= ip.toCharArray();
       // frequencyTable(bf);
        HuffmanCompression hf= new HuffmanCompression();
        hf.compress(bf);

    }
}
