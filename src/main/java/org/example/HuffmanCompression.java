package org.example;

import java.util.PriorityQueue;
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
        return frequencies;
    }

    public PriorityQueue<HuffmanNode> createPriorityQueue(int[] frequencies) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>(new FrequencyComparator());
        //add in priority queue
        //nodes added in ascending order of freq
        for(int i= 0; i<frequencies.length; i++) {
            if(frequencies[i] > 0) {
                //whatever is added first, remains on top in priorityqueue
                queue.add(new HuffmanNode((char) i, frequencies[i]));
            }
        }
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
        String compressedData= encode(text, node1);
        return compressedData;
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

    public void generateBits(String[] array, HuffmanNode root, StringBuilder s) {
        if(root.ch == '-') {
            s.append(0);
            generateBits(array, root.leftChild, s);
            s.append(1);
            generateBits(array, root.rightChild, s);
        } else {
            System.out.println(root.ch + " - " + s.toString());
            array[root.ch]= s.toString();
            s.deleteCharAt(s.length()-1);
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
