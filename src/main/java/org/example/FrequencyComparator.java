package org.example;

import java.util.Comparator;

public class FrequencyComparator implements Comparator<HuffmanNode> {
    @Override
    public int compare(HuffmanNode h1, HuffmanNode h2) {
        return h1.freq-h2.freq;
    }
}
