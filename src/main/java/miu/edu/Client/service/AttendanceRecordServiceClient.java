package miu.edu.Client.service;

import com.google.gson.Gson;
import miu.edu.Client.model.AttendanceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AttendanceRecordServiceClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${base-service-url}")
    private String baseUrl;

    public String getLocationCode() {
        return locationCode;
    }

    @Value("${spring.application.location}")
    private String locationCode;

    public String accessToken;

    public AttendanceRecord attendanceRecord;

    public void createRecord(String barCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        Map<String, String> body = new HashMap<>();

        Gson gson = new Gson();
        HttpEntity<String> request =
                new HttpEntity<>(gson.toJson(body), headers);

        String url = baseUrl + "/attendances/" + locationCode + "/" + barCode;
        restTemplate.postForObject(url, request, AttendanceRecord.class);
    }

    //  fetch('/authenticate/login', {
    public String login(String email, String password) {
        String url = baseUrl + "/authenticate/login";

        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        Gson gson = new Gson();
        HttpEntity<String> request =
                new HttpEntity<>(gson.toJson(body), headers);
        accessToken = (String) restTemplate.postForObject(url, request, Map.class).get("accessToken");
        return accessToken;
    }
}
