package ua.edu.ucu.tries;

public class RWayTrie implements Trie {
    private static final int R = 26;
    private static final int charAscii = 97;
    private int size = 0;

    public static class TrieNode {
        private TrieNode[] children;
        private Object content;

        public TrieNode(Object value) {
            content = value;
        }

        public TrieNode() {
        }
    }

    private TrieNode root;

    private TrieNode insert(TrieNode node, Tuple tuple, int d) {
        if (node == null) {
            node = new TrieNode();
        }
        if (d == tuple.term.length()) {
            node.content = tuple.weight;
            return node;
        }
        char c = tuple.term.charAt(d);
        node.children[c - charAscii] = insert(node.children[c - charAscii], tuple, d + 1);
        return node;
    }

    @Override
    public void add(Tuple t) {
        root = insert(root, t, 0);
    }

    private TrieNode search(TrieNode node, String s, int d) {
        if (node == null) {
            return null;
        }
        if (d == s.length()) {
            return node;
        }
        char c = s.charAt(d);
        return search(node.children[c - charAscii], s, d + 1);
    }

    @Override
    public boolean contains(String word) {
        return search(root, word, 0) != null;
    }

    @Override
    public boolean delete(String word) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<String> words() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
