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
        PriorityQueue<HuffmanNode> queue= new PriorityQueue<HuffmanNode>(1,new FrequencyComparator());
        //add in priority queue
        //nodes added in ascending order of freq
        for(int i= 0; i<frequencies.length; i++) {
            if(frequencies[i] > 0) {
                queue.add(new HuffmanNode((char) i, frequencies[i]));
            }
        }
        return queue;
    }

    public HuffmanNode createHuffmanTree(PriorityQueue<HuffmanNode> queue) {
        HuffmanNode root= null;
        while(queue.size() > 0) {
            root= getLeastFrequencyNode(queue);
            //add root back to queue only if queue > 0. Once queue size becomes 0, the compelete tree has been built
            if(queue.size() > 0) queue.add(root);
        }
        return root;
    }

    public HuffmanNode getLeastFrequencyNode(PriorityQueue<HuffmanNode> queue) {
        HuffmanNode node1 = queue.poll();
        HuffmanNode node2 = queue.poll();
        HuffmanNode root= new HuffmanNode('-', node1.freq+node2.freq);
        //left child freq is less than right child
        root.leftChild= node1;
        root.rightChild= node2;
        return root;
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String ip= sc.nextLine();
        char[] bf= ip.toCharArray();
        frequencyTable(bf);
    }
}
