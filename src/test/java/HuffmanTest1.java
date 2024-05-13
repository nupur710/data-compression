import org.example.HuffmanCompression;
import org.example.HuffmanNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class HuffmanTest1 {
    HuffmanCompression huffmanCompression;
    BufferedReader br;
    StringBuilder text;
    @Before
    public void init() {
        huffmanCompression= new HuffmanCompression();
        try {
            FileReader fileReader = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\Gutenberg.txt");
            br = new BufferedReader(fileReader);
            text= new StringBuilder();
            int c;
            while((c= br.read()) != -1) {
                text.append((char) c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    @Test
    public void frequencyTableTest() {
        int[] frequencyTable= huffmanCompression.frequencyTable(text.toString().toCharArray());
        Assert.assertEquals(333, frequencyTable['X']);
        Assert.assertEquals(223000, frequencyTable['t']);
    }
    @Test
    public void createPriorityQueueTest() {
        int[] freqencyTable= huffmanCompression.frequencyTable(text.toString().toCharArray());
        PriorityQueue<HuffmanNode> queue= huffmanCompression.createPriorityQueue(freqencyTable);
        Assert.assertEquals(119, queue.size());
        Assert.assertEquals('#', queue.peek().ch);
        Assert.assertEquals(1, queue.poll().freq);
        Assert.assertEquals('%', queue.peek().ch);
        Assert.assertEquals(1, queue.poll().freq);
        Assert.assertEquals('&', queue.peek().ch);
        Assert.assertEquals(1, queue.poll().freq);
        Assert.assertEquals('{', queue.peek().ch);
        Assert.assertEquals(1, queue.poll().freq);
    }

    @Test
    public void createHuffmanTreeTest() {
        int[] freqencyTable= huffmanCompression.frequencyTable(text.toString().toCharArray());
        PriorityQueue<HuffmanNode> queue= huffmanCompression.createPriorityQueue(freqencyTable);
        HuffmanNode node1= huffmanCompression.createHuffmanTree(queue);
        Assert.assertEquals('-', node1.ch);
        Assert.assertEquals(3324211, node1.freq);
        Assert.assertEquals('-', node1.leftChild.ch);
        Assert.assertEquals(1362029, node1.leftChild.freq);
        Assert.assertEquals('-', node1.leftChild.leftChild.ch);
        Assert.assertEquals(652051, node1.leftChild.leftChild.freq);
        Assert.assertEquals('-', node1.leftChild.rightChild.ch);
        Assert.assertEquals(709978, node1.leftChild.rightChild.freq);
        Assert.assertEquals('-', node1.rightChild.ch);
        Assert.assertEquals(1962182, node1.rightChild.freq);
        Assert.assertEquals('-', node1.rightChild.leftChild.ch);
        Assert.assertEquals(863800, node1.rightChild.leftChild.freq);
        Assert.assertEquals('-', node1.rightChild.rightChild.ch);
        Assert.assertEquals(1098382, node1.rightChild.rightChild.freq);
        Assert.assertEquals('e', node1.leftChild.leftChild.leftChild.ch);
        Assert.assertEquals(325664, node1.leftChild.leftChild.leftChild.freq);
        Assert.assertNull(node1.leftChild.leftChild.leftChild.leftChild);
        Assert.assertNull(node1.leftChild.leftChild.leftChild.rightChild);
       }

       @Test
       public void generateBitsTest() {
           String[] str= new String[huffmanCompression.CHARACTER_LIMIT];
           int[] frequencyTable= huffmanCompression.frequencyTable(text.toString().toCharArray());
           PriorityQueue<HuffmanNode> queue= huffmanCompression.createPriorityQueue(frequencyTable);
           HuffmanNode node1= huffmanCompression.createHuffmanTree(queue);
           huffmanCompression.generateBits(str, node1, new StringBuilder());
          Assert.assertEquals("000", str['e']);
          Assert.assertEquals("0010000000", str['_']);
          Assert.assertEquals("0010000001", str['V']);
          Assert.assertEquals("001000001", str['S']);
          Assert.assertEquals("11101", str['r']);
          Assert.assertEquals("11111", str['s']);
       }

    @Test
    public void decompressTest() throws IOException {
        BufferedReader br= new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\GutenbergEncoded.txt"));
        int c;
        StringBuilder abc= new StringBuilder();
        while((c= br.read()) != -1) {
            abc.append((char) c);
        }
        char[] arr_ch= abc.toString().toCharArray();
        char[] decx= huffmanCompression.decompress(arr_ch);
        System.out.println(decx);
    }
}
