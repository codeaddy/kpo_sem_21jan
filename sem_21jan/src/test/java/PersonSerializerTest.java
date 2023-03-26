import org.example.JsonSerializer;
import org.example.Person;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonSerializerTest {

    @Test
    void personIsSerialized() throws IllegalAccessException {
        Person p = new Person("Ivan", "Ivanov", LocalDate.of(1997, 11, 2));
        JSONObject json = new JsonSerializer<>(Person.class).serialize(p);
        assertEquals("Ivan", json.get("firstName"));
        assertEquals("Ivanov", json.get("lastName"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = p.getBirthDate();
        String formattedString = birthDate.format(formatter);

        assertEquals("1997-11-02", formattedString);
    }
}