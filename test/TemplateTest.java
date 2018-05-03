import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Map;
import java.util.HashMap;


public class TemplateTest {
    @Test
    public void anotherWrongOnPurpose() {
        assertEquals(0,2);
    }
    
    @Test
    public void noChange() {
        Template template = new Template("Od: Dodavatel elektřtiny");
        Map<String, String> variables = new HashMap<String, String>();
        variables.put("zakaznik", "Ferda Mravenec");
        variables.put("cas", "13:45");
        assertEquals(template.render(variables), "Od: Dodavatel elektřtiny");
    }
    
    @Test
    public void oneChange() {
        Template template = new Template("Od: {{ zakaznik }}\n");
        Map<String, String> variables = new HashMap<String, String>();
        variables.put("zakaznik", "Ferda Mravenec");
        variables.put("cas", "13:45");
        assertEquals(template.render(variables), "Od: Ferda Mravenec\n");
    }

    @Test
    public void twoChanges() {
        Template template = new Template("Od: {{ zakaznik }}\nPro: {{ vedouci }}\n");
        Map<String, String> variables = new HashMap<String, String>();
        variables.put("zakaznik", "Ferda Mravenec");
        variables.put("vedouci", "Vila Amalka");
        variables.put("cas", "13:45");
        assertEquals(template.render(variables), "Od: Ferda Mravenec\nPro: Vila Amalka\n");
    }

    @Test
    public void twoSameChanges() {
        Template template = new Template("Od: {{ zakaznik }}\n...\nDekuji, {{ zakaznik }}\n");
        Map<String, String> variables = new HashMap<String, String>();
        variables.put("zakaznik", "Ferda Mravenec");
        variables.put("vedouci", "Vila Amalka");
        assertEquals(template.render(variables), "Od: Ferda Mravenec\n...\nDekuji, Ferda Mravenec\n");
    }

    @Test
    public void wrongOnPurpose() {
        assertEquals(0, 1);
    }
}