package idwall.desafio.string;

import java.util.Arrays;
import java.util.List;

/**
 * Store line information
 */
public class LineFormat {
    private List<String> words;
    private int characterQtt;
    private int[] spacesQtt;

    public LineFormat(List<String> words, int characterQtt) {
        this.words = words;
        this.characterQtt = characterQtt;
        spacesQtt = new int[words.size() - 1];
        Arrays.fill(spacesQtt, 1);
    }


    //Getter and Setters
    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public int getCharacterQtt() {
        return characterQtt;
    }

    public void setCharacterQtt(int characterQtt) {
        this.characterQtt = characterQtt;
    }

    public int[] getSpacesQtt() {
        return spacesQtt;
    }

    public void setSpacesQtt(int[] spacesQtt) {
        this.spacesQtt = spacesQtt;
    }
}
