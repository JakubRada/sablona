import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileReader;
import java.io.PrintWriter;

public class CsvTemplater {
    static String csvFile = "";
    static String templateFile = "";
    static String outFiles = "templater-out-%05d.txt";
    
    public static void processArguements(String[] args) {
        String[] parameterParts;
        for (String parameter : args) {
            parameterParts = parameter.split("=");
            if (parameterParts.length == 2) {
                if ("--csv".equals(parameterParts[0])) {
                    csvFile = parameterParts[1];
                } else if ("--template".equals(parameterParts[0])) {
                    templateFile = parameterParts[1];
                } else if ("--out".equals(parameterParts[0])) {
                    outFiles = parameterParts[1];
                }
            }
        }
    }

    public static String readTemplate() throws IOException {
        FileReader reader = new FileReader(templateFile);
        Scanner sc = new Scanner(reader);
        String templateText = "";
        while (sc.hasNextLine()) {
            templateText += sc.nextLine();
            templateText += "\n";
        }
        sc.close();
        return templateText;
    }

    public static void writeFile(String textToWrite, int fileNumber) throws IOException {
        String filename = String.format(outFiles, fileNumber);
        PrintWriter writer = new PrintWriter(filename);
        writer.write(textToWrite);
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        processArguements(args);
        String templateText = readTemplate();
        String renderedText;
        String[] headlines = new String[0];
        String[] names;
        int count = 0;
        Map<String, String> variables = new HashMap<String, String>();
        FileReader reader = new FileReader(csvFile);
        Scanner sc = new Scanner(reader);
        if (sc.hasNextLine()) {
            headlines = sc.nextLine().split(",");
        }
        while (sc.hasNextLine()) {
            count += 1;
            variables.clear();
            names = sc.nextLine().split(",");
            for (int i = 0; i < headlines.length; i += 1) {
                variables.put(headlines[i], names[i]);
            }
            Template template = new Template(templateText);
            renderedText = template.render(variables);
            writeFile(renderedText, count);
        }
        sc.close();
    }
}