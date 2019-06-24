import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class Internet {
    public StringBuffer sendGet() throws IOException{
        URL obj = new URL("https://api.exchangeratesapi.io/latest?symbols=USD");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET RESPONSE CODE : : " + responseCode);
        StringBuffer response = new StringBuffer();

        if (responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();
        } else {
            System.out.println("Something is wrong");
        }
        return response;
    }
}


