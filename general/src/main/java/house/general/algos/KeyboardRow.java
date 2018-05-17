package house.general.algos;

import java.util.ArrayList;
import java.util.HashMap;

class KeyboardRow {
    public String[] findWords(String[] words) {

        char[] top = new char[]{'q', 'w', 'e', 'r', 't'};
        char[] middle = new char[]{'a', 's', 'd', 'f', 'g', 'h'};
        char[] bottom = new char[]{'z', 'x', 'c', 'v', 'b', 'n', 'm'};

        ArrayList<String> matchingWords = new ArrayList<>();

        HashMap<Character, Integer> keyboard = new HashMap<>();
        for (int i = 0; i < top.length; i++) {
            keyboard.put(top[i], 1);
        }

        for (int i = 0; i < middle.length; i++) {
            keyboard.put(middle[i], 0);
        }

        for (int i = 0; i < middle.length; i++) {
            keyboard.put(bottom[i], -1);
        }

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toLowerCase();
        }

        for (int i = 0; i < words.length; i++){
            int expected = words[i].charAt(0);
            int j;
            for (j = 1; j < words[i].length(); j++) {
                if (keyboard.get(words[i].charAt(j)) != expected) {
                    break;
                }
            }
            if (j == words[i].length()) {
                matchingWords.add(words[i]);
            }
        }
        return (String[]) matchingWords.toArray();
    }
}