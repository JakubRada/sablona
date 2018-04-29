import java.util.Scanner;
import java.util.Map;

public class Template2 {
    private String templateText;
    private String resultText = "";

    public Template2(String templateText) {
        this.templateText = templateText;
    }

    public String render(Map<String, String> variables) {
        Scanner scLine = new Scanner(templateText);
        String word;
        String wordToReplace;
        String line;
        int countLines = 0;
        int countWords;
        while (scLine.hasNextLine()) { //projde vsechny radky sablony
            if (countLines != 0) {
                resultText += "\n";
            }
            countWords = 0;
            line = scLine.nextLine();
            Scanner scWords = new Scanner(line);
            while (scWords.hasNext()) { //nahradi vsechna slova v ramci jedne radky
                if (countWords != 0) {
                    resultText += " ";
                }
                word = scWords.next();
                if ("{{".equals(word)) {
                    wordToReplace = scWords.next();
                    if (variables.containsKey(wordToReplace)) {
                        resultText += variables.get(wordToReplace);
                    } else {
                        resultText += "???";
                    }
                    scWords.next();
                } else {
                    resultText += word;
                }
                countWords += 1;
            }
            countLines += 1;
        }
        return resultText;
    }
}