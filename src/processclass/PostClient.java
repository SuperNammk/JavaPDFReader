/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package processclass;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import responseclass.FPTResponse;

public class PostClient {

    public static FPTResponse PostData(String data) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.fpt.ai/hmi/tts/v5"))
                .header("api-key", "sAkT3CityBPe303NyKn3qwtop7HKC3kx")
                .header("speed", "")
                .header("voice", "thuminh")
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        FPTResponse res = new ObjectMapper().readValue(response.body(), FPTResponse.class);
        return res;
    }

    

}
