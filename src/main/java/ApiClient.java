// Dessa paket använder vi för att läsa information från och skriva information till HTTP-anslutningar

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

// Dessa paket hjälper oss konvertera JSON till Java-objekt och tvärt om
// De används när vi skickar information till servern och när vi hämtar information från servern
import com.github.cliftonlabs.json_simple.JsonObject;
import com.fasterxml.jackson.databind.*;

// Detta används för att skicka datan med UTF-8 - en teckenuppsättning som låter oss använda ÅÄÖ och massa andra tecken

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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
}

    public ArrayList<String> getStringArray(String target) {
        JsonObject countryObjt = new JsonObject();

        ArrayList<String> myArrayOfStrings = new ArrayList<>();

        return myArrayOfStrings;
    }

    //Metod för att hämta blogginlägg
    public Blog[] getBlogs() {
        Blog[] blogs = {};

        String target = "/blogs/list"
    }

