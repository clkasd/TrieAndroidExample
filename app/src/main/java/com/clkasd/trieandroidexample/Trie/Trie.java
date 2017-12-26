package com.clkasd.trieandroidexample.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aykutcelik on 21.12.2017.
 */

public class Trie<T> {
    private TrieNode<T> root;

    public Trie()
    {
        root = new TrieNode<T>();
    }
    public void insert(String word, T payload)
    {
        HashMap<Character, TrieNode<T>> children = root.children;

        for(int i=0;i<word.length();i++)
        {
            char c = Character.toLowerCase(word.charAt(i));
            TrieNode t;
            if(children.containsKey(c))
            {
                t=children.get(c);
            }
            else
            {
                t = new TrieNode(c);
                children.put(c,t);
            }
            children = t.children;

            if(i==word.length()-1) {
                t.isLeaf = true;
                t.connectedPayload.add(payload);
            }
        }
    }
    private TrieNode<T> searchNode(String str){
        Map<Character, TrieNode<T>> children = root.children;
        TrieNode<T> t = null;
        for(int i=0; i<str.length(); i++){
            char c = Character.toLowerCase(str.charAt(i));
            if(children.containsKey(Character.toLowerCase(c))){
                t = children.get(c);
                children = t.children;
            }else{
                return null;
            }
        }
        return t;
    }


    public List<T> getPayloadWithPrefix(String prefix) {
        List<T> payload = new ArrayList<>();
        TrieNode<T> node = searchNode(prefix);
        if (node != null && node.children.size() > 0)
        {
            if (node.isLeaf)
                payload.addAll(node.connectedPayload);
            applyDFS(node,payload);
        }
        else if(node!=null && node.isLeaf && node.children.size()==0)
            payload.addAll(node.connectedPayload);
        return payload;
    }

    private void applyDFS(TrieNode<T> node,List<T> payload) {
        for (TrieNode<T> trieNode :node.children.values()) {
            if(trieNode.isLeaf)
            {
                payload.addAll(trieNode.connectedPayload);
                if(trieNode.children.values().size()>0)
                    applyDFS(trieNode,payload);
            }
            else
                applyDFS(trieNode,payload);
        }
    }
}
