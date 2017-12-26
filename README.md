# TrieAndroidExample
Example of Trie data structure on Android with a RecyclerView.
# What is Trie
Trie data structure is also called prefix tree.Because it has been developed for 
optimal prefix searches in a very large array of words.

# How does it work?
Trie keeps words according to their chars.

# What does this library or example do? 

# How can I use it?

When you inserting a word into this tree create a node with every char from its start.
and when insertion is completed mark the last node as leaf.
for instance :
         a
      /  |  \
     i   p   k
    /|
   m r
f you want to find "aim" you need to traverse tree to find first "a" char
and traverse its child nodes to find "i" and so on.
when you reached leaf node you found your search.
Information about Trie data structure : https://en.wikipedia.org/wiki/Trie

