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
        DATE("date"),
        TEXT("text");

        private final Object value;
        /**
         * Instantiates a JsonKey with the provided value.
         *
         * @param value represents a valid default for the key.
         */
        keys(final Object value) {
            this.value = value;
        }

        @Override
        public String getKey() {
            return this.name().toLowerCase();
        }

        @Override
        public Object getValue() {
            /* Can represent a valid default, error value, or null adhoc for the JsonKey. See the javadocs for more
             * information about its intended use. */
            return this.value;
        }
    }

    //Konstruktor
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


    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toJson() {
        final StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        } catch (final Exception e) {
            /* See java.io.StringWriter. */
        }
        return writable.toString();
    }

    @Override
    public void toJson(final Writer writable) {
        try {
            final JsonObject json = new JsonObject();
            json.put(keys.TEXT.getKey(), this.getText());
            json.put(keys.DATE.getKey(), this.getDate());
            json.put(keys.ID.getKey(), this.getId());
            json.toJson(writable);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    @Override
    public String toString() {
        return "JsonSimpleExample [id=\" + this.id + \", title=" + this.text + ", rating=" + this.date + "]";
    }


}
