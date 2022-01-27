import java.io.StringWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.Jsonable;


public class Blog implements Jsonable {
    public int id;
    public LocalDate date;
    public String text;

    enum keys implements JsonKey {
        ID("id"),
        DATE(),
        TEXT(),
        private final Object value;


    }

    public Blog() {
    }

    public Blog(String text) {
        this.text = text;
    }

    public Blog(int id, LocalDate date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }


}
