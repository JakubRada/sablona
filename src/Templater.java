import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Templater {
    public static Map<String, String> fillParameters(String[] args) {
        Map<String, String> variables = new HashMap<String, String>();
        String[] parameterParts;
        for (String parameter : args) {
            parameterParts = parameter.split("=");
            if ((parameterParts.length == 3) && ("--var".equals(parameterParts[0]))) {
                variables.put(parameterParts[1], parameterParts[2]);
            }
        }
        return variables;
    }

    public static String readTemplate() {
        Scanner sc = new Scanner(System.in);
        String templateText = "";
        while (sc.hasNextLine()) {
            templateText += sc.nextLine();
            templateText += "\n";
        }
        sc.close();
        return templateText;
    }

    public static void main(String[] args) {
        Map<String, String> variables = fillParameters(args);
        String templateText = readTemplate();
        Template temp = new Template(templateText);
        String renderedText = temp.render(variables);
        System.out.println(renderedText);
    }
}