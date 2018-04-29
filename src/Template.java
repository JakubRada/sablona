import java.util.HashMap;
import java.util.Map;

public class Template {
    String templateText;
    public Template(String templateText) {
        this.templateText = templateText;
    }

    public String render(Map<String, String> variables) {
        String toBeReplaced;
        String replaceWith;
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            toBeReplaced = String.format("\\{\\{ %s \\}\\}", entry.getKey());
            replaceWith = entry.getValue();
            templateText = templateText.replaceAll(toBeReplaced, replaceWith);
        }
        return templateText;
    }
}