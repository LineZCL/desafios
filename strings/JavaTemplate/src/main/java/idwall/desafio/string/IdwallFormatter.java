package idwall.desafio.string;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallFormatter extends StringFormatter {

    public IdwallFormatter(Integer limit) {
        super(limit);
    }

    /**
     * Should format as described in the challenge
     *
     * @param text
     * @return
     */
    @Override
    public String format(String text, boolean isJustify) {
        List<LineFormat> lines = arrangeLines(text, isJustify);
        return getText(lines);
    }

    /**
     * Arrange the text in lines with limit characters
     *
     * @param text
     * @return
     */
    public List<LineFormat> arrangeLines(String text, boolean justify){
        List<LineFormat> lines = new ArrayList<>();
        String[] words = arrangeWords(text);

        int sizeTextLine = 0;
        List<String> wordsInLine = new ArrayList<>();
        for (int i = 0; i < words.length; i++){
            if(!words[i].isEmpty()){
                if(words[i] == "\n"  || sizeTextLine + words[i].length()  > limit || sizeTextLine == 0){
                    if(!wordsInLine.isEmpty()) {
                        LineFormat line = new LineFormat(wordsInLine, sizeTextLine);
                        if (justify)
                            justify(line);
                        lines.add(line);
                    }
                    wordsInLine = new ArrayList<>();
                    sizeTextLine = 0;
                }
                wordsInLine.add(words[i]);
                sizeTextLine += words[i].length() + 1;
            }
        }
        lines.add(new LineFormat(wordsInLine, sizeTextLine));
        return lines;
    }

    /**
     * Work with the words to be processed
     *
     * @param text
     * @return
     */
    public String[] arrangeWords(String text){
        text = text.replace("\n", " \n ");
        return text.split(" ");
    }

    /**
     *
     * Return the text ready to print
     * @param lines
     * @return
     */
    public String getText(List<LineFormat> lines){
        String text = "";
        for (LineFormat line : lines ) {
            int spaceIndex = 0;
            for(String word : line.getWords()){
                text += word;
                if(spaceIndex < line.getSpacesQtt().length){
                    for (int i = 0; i < line.getSpacesQtt()[spaceIndex]; i++){
                        text += " ";
                    }
                }
                spaceIndex ++;
            }
            text += "\n";
        }
        return text;
    }

    @Override
    public void justify(LineFormat line) {
        int missingSpaces = limit - line.getCharacterQtt();
        int numberSpaces = missingSpaces / line.getSpacesQtt().length;
        int numberSpacesRest = missingSpaces % line.getSpacesQtt().length;

        if(numberSpaces + numberSpacesRest <= 5){
            for(int i = 0; i < line.getSpacesQtt().length; i++){
                line.getSpacesQtt()[i] += numberSpaces;
                if(numberSpacesRest >= i)
                    line.getSpacesQtt()[i]++;
            }
        }

    }
}
