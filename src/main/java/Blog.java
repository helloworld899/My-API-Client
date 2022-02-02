import java.io.StringWriter;
import java.io.Writer;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.Jsonable;


public class Blog implements Jsonable {
    public int id;
    public String title;
    public String text;
    public String date;



    enum keys implements JsonKey {
        ID("id"),
        TITLE("title"),
        TEXT("text"),
        DATE("date");

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
            return this.value;
        }
    }

    //Constructors
    public Blog() {
    }

    public Blog(String title, String text, String date) {
        this.title = title;
        this.text = text;
        this.date = date;

    }

    public Blog(int id, String title, String text, String date) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;

    }


    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    @Override
    public String toJson() {
        final StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        } catch (final Exception e) {

        }
        return writable.toString();
    }

    @Override
    public void toJson(final Writer writable) {
        try {
            final JsonObject json = new JsonObject();
            json.put(keys.ID.getKey(), this.getId());
            json.put(keys.TITLE.getKey(), this.getTitle());
            json.put(keys.TEXT.getKey(), this.getText());
            json.put(keys.DATE.getKey(), this.getDate());
            json.toJson(writable);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
