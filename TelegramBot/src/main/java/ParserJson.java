import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;

public class ParserJson<stringBuilder> {

    private ObjectMapper mapper;
    private URLConnection connection;
    private Scanner scanner;
    private URL nameURL;
    private List<Currency> currencies;
    private StringBuilder stringBuilder = new StringBuilder();

     {
        mapper = new ObjectMapper();
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);        
        try {
            nameURL = new URL("https://bank.gov.ua/NBU_Exchange/exchange?json");
            connection = nameURL.openConnection();
            scanner = new Scanner(connection.getInputStream());
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
    
      
     public List<Currency> read () {
         while (scanner.hasNext()) {
             stringBuilder.append(scanner.nextLine());
         }
        try {
            currencies = mapper.readValue(stringBuilder.toString(), new TypeReference<List<Currency>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Read exception in 'mapper.readValue()'");
        }
        return currencies;
    }

    public String getAmount (String currencyCodeL) {
         List<Currency> currencyList = read();
         double amount = 0.0;
         for (Currency a : currencyList) {
             if (a.getCurrencyCodeL().equals(currencyCodeL)) {
                 amount = a.getAmount();
             }
         }
         return String.valueOf(amount);
    }
    
}
