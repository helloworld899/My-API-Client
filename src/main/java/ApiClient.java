// Dessa paket använder vi för att läsa information från och skriva information till HTTP-anslutningar
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

// Dessa paket hjälper oss konvertera JSON till Java-objekt och tvärt om
// De används när vi skickar information till servern och när vi hämtar information från servern
import com.fasterxml.jackson.databind.*;

// Detta används för att skicka data med UTF-8 - en teckenuppsättning som låter oss använda ÅÄÖ och massa andra tecken
import java.nio.charset.StandardCharsets;

// Paket vi använder för att göra HTTP-anslutningar
import java.net.URL;
import java.net.HttpURLConnection;


public class ApiClient {
    private String apiAdress;
    HttpURLConnection connection;

    //Nedan skapar vi en konstruktor
    public ApiClient(String apiAdress) {
        this.apiAdress = apiAdress;
    }


   /* public ArrayList<String> getStringArray(String target) {
        JsonObject countryObj = new JsonObject();

        ArrayList<String> myArrayOfStrings = new ArrayList<>();

        return myArrayOfStrings;
    }

    //Ingen användning av myArrayOfStrings, därav utkommenderat.
    */

    //Metod för att hämta blogginlägg
    public Blog[] listBlogs() {
        Blog[] blogs = {};

        String target = "/blogs/list/";

        System.out.println("Getting blog content from " + apiAdress + target);

        // Se kommentarer i metoden addBlog()
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();

        // Se kommentarer i metoden addBlog()
        try {
            URL url = new URL(apiAdress + target);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");

            int status = connection.getResponseCode();
            System.out.println("HTTP status code: " + status);


            if (status >= 300) {
                if (connection.getErrorStream() != null) {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }
                    reader.close();
                }
            } else {
                if (connection.getInputStream() != null) {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }
                    reader.close();
                }
            }

            //System.out.println(responseContent.toString());
            String jsonStr = responseContent.toString();

            // Se kommentarer i metoden addBlog()
            ObjectMapper mapper = new ObjectMapper();
            blogs = mapper.readValue(jsonStr, Blog[].class);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return blogs;
    }

    public boolean clearAllBlogs() {
        String target = "/blogs/clear/"; // http://127.0.0.1:8080/api/v1/blogs/clear

        System.out.println("Clearing blogs from " + apiAdress + target);

        boolean success = false;

        try {
            URL url = new URL(apiAdress + target);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int status = connection.getResponseCode();
            System.out.println("HTTP status code: " + status);

            if (status < 300) {
                success = true;
            }

            //System.out.println(responseContent.toString());
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return success;
    }

    public boolean addBlog(Blog newBlog) {
        String target = "/blogs/create/";

        System.out.println("Adding blog to " + apiAdress + target);

        boolean success = false;

        try {
            // Skapa ett URL-objekt och säg vilken adress vi vill skicka information till
            URL url = new URL(apiAdress + target);

            // Öppna nätverksanslutningen
            connection = (HttpURLConnection) url.openConnection();

            // Ange metod
            connection.setRequestMethod("POST");

            // Lägg till header (säg att vi vill skicka JSON-data)
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);

            // Konvertera vårt Java-objekt (Blog) till JSON med hjälp av .toJSON-metoden i klassen Blog,
            // och skriv den JSON-datan till vår nätverksanslutning med hjälp av en OutputStream
            try (OutputStream os = connection.getOutputStream()) {
                // Skapa en byte-array som innehåller JSON-datan
                byte[] input = newBlog.toJson().getBytes(StandardCharsets.UTF_8);

                // Skriv byte-arrayen till nätverksanslutningen (vi måste också ange hur lång strängen är)
                os.write(input, 0, input.length);
            }

            // Vad fick vi för svar? Vad var HTTP-statuskoden vi fick tillbaka?
            int status = connection.getResponseCode();

            // Generellt om HTTP-koden är över 300 har något gått fel
            // Om den är 299 eller lägre har det gått bra
            // (Exempelvis är "200 OK" bra och "404 Not Found" inte bra)
            if (status == 201) {
                success = true;
            } else {
                System.out.println("We got a respond we did not expect. HTTP status code: " + status);
            }

            //System.out.println(responseContent.toString());
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return success;
    }

    public boolean deleteSpecificBlogByID(Blog deleteBlog) {
        String target = "/blogs/delete/" + deleteBlog.id;

        System.out.println("Deleting a blog from " + apiAdress + target);

        boolean success = false;

        try {
            URL url = new URL(apiAdress + target);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int status = connection.getResponseCode();

            if (status < 300) {
                success = true;
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return success;
    }

    public boolean updateSpecificBlogByID(int id, Blog updateBlog) {
        String target = "/blogs/update/" + id;
        boolean success = false;

        System.out.println("Updating blog content from " + apiAdress + target);

        try {

            URL url = new URL(apiAdress + target);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {

                byte[] input = updateBlog.toJson().getBytes(StandardCharsets.UTF_8);

                os.write(input, 0, input.length);
            }


            int status = connection.getResponseCode();
            System.out.println("HTTP status code: " + status);

            if (status < 300) {
                success = true;
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return success;
    }


    public Blog getBlogByID(int id) {
        Blog blog = null; //Vi sätter Blogg till null

        String target = "/blogs/view/" + id;

        System.out.println("Getting blog content from " + apiAdress + target);

        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();


        try {
            URL url = new URL(apiAdress + target);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");

            int status = connection.getResponseCode();

            System.out.println("HTTP status code: " + status);

            if (status >= 300) {
                if (connection.getErrorStream() != null) {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }
                    reader.close();
                }
            } else {
                if (connection.getInputStream() != null) {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }
                    reader.close();
                }
                String jsonStr = responseContent.toString();
                ObjectMapper mapper = new ObjectMapper();
                blog = mapper.readValue(jsonStr, Blog.class);
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return blog; // Den returnerar bloggen ifall det gick bra att hämta...
        //men om det ej gick att hämta så returnerar den istället en null.
    }
}
