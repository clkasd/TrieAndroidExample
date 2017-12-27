# TrieAndroidExample
Example of Trie data structure on Android with a RecyclerView.
# What is Trie
Trie data structure is also called prefix tree.Because it has been developed for 
optimal prefix searches in a very large array of words.

# How does it work?
Trie keeps words according to their chars.
When you insert a word into this tree.It will create a node with every char from its start.
and when insertion is completed mark the last node as leaf.

The next figure shows a trie with the words “tree”, “trie”, “algo”, “assoc”, “all”, and “also.”
<img src="https://community.topcoder.com/i/education/alg_tries.png"/>

If you want to find "algo" you need to traverse tree to find first "a" char
and traverse its child nodes to find "l" and so on.
When you reached leaf node you get the word "algorithm".
Information about Trie data structure : https://en.wikipedia.org/wiki/Trie
# What does this library or example do? 

This library is example of complete search tree based on a generic class.Generally Trie algorithm enables you to find the word but does not give you oppurtunity to get related details of a searched node.With this library you can search a node from tree and after that it applies DepthFirstSearch to get other leaf nodes beneath your searched node and also it returns their connected information.

In this example I used City objects. City object has some properties like location ,country name and city name.If you search for "new" prefix you should get "newyork","newark","newcastle"..etc. as a list.As an advantage this list is created with City objects related with "newyork,"newark","newcastle".etc.

Meaning ,you can also get their location , country name and all of its properties. 


# How can I use it?

You can download project and open it in Android Studio.Trie package contains Trie implementation. If you need detailed information about how to use you can check out MainActivity.class.

# TODO
Add library to bintray or jcenter to enable using with gradle.

