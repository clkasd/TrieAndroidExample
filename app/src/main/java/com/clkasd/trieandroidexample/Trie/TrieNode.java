package com.clkasd.trieandroidexample.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by aykutcelik on 21.12.2017.
 */

public class TrieNode<T> {
    char c;

    boolean isLeaf=false;

    List<T> connectedPayload = new ArrayList<>();

    HashMap<Character, TrieNode<T>> children = new HashMap<>();

    public TrieNode(){
    }

    public TrieNode(char c)
    {
        this.c=c;
    }

}
