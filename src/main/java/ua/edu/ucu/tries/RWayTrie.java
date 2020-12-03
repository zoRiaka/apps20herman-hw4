package ua.edu.ucu.tries;
import ua.edu.ucu.immutable.Queue;

public class RWayTrie implements Trie {
    private static final int R = 26;
    private static final int charAscii = 97;
    private int size = 0;

    public static class TrieNode {
        private TrieNode[] children;
        private Object content;

        public TrieNode(Object value) {
            content = value;
            children = new TrieNode[R];
        }

        public TrieNode() {
            children = new TrieNode[R];
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
        size++;
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
        if (contains(word)) {
            TrieNode del = search(root, word, 0);
            size--;
            return true;
        }
        return false;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    private void collect(TrieNode node, String prf, Queue q) {
        if (prf == null) {
            return;
        }
        if (node.content != null)  {
            q.enqueue(prf);
        }
        for (char ch = 0; ch < R; ch++) {
            collect(node.children[ch], prf + (char) (ch + charAscii), q);
        }
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue q = new Queue();
        collect(search(root, s, 0), s, q);
        return (Iterable<String>) q;
    }

    @Override
    public int size() {
        return this.size;
    }

}
