package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie myTrie) {
        trie = myTrie;
    }

    public int load(String... strings) {
        for (String s: strings) {
            String[] words = s.split("\\W+");
            for (int i = 0; i < words.length; i++) {
                if (!this.contains(words[i]) &&  words[i].length() >= 2) {
                    trie.add(new Tuple(words[i], words[i].length()));
                }
            }
        }
        return 0;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        ArrayList<String> words = new ArrayList<>();
        Iterable<String> prefWords = this.wordsWithPrefix(pref);
        if (pref.length() >= 2) {
            for (String pw: prefWords) {
                if (pw.length() <= pref.length() + k - 1) {
                    words.add(pw);
                }
            }
        }
        return words;
    }

    public int size() {
        return trie.size();
    }
}
