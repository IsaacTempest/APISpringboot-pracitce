package com.example.demo;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class GreetingController {
    @RequestMapping("/")
    public JSONObject anime(@RequestBody String name) throws IOException, InterruptedException, org.json.simple.parser.ParseException {
        //Scanner scanner = new Scanner(System.in);
        //String nameSurname = scanner.nextLine();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://animechan.vercel.app/api/random/character?name=" + name))
                .GET() // GET is default
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        JSONParser parser = new JSONParser();    
        JSONObject json = (JSONObject)parser.parse(response.body()); 
       
        System.out.println(response.body());
        return json;
    }
}
