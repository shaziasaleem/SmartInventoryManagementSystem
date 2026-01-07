package authService.inventory.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Value;
import system.inventory.commonutilities.commonDto.UserDTO;

@Service
public class ClientService {

	private final RestTemplate restTemplate;
	
    @Value("${user-service.base-url}")
    private String userServiceBaseUrl;

    public ClientService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public UserDTO getUserByUsername(String username) {
        String url = userServiceBaseUrl + "/user/getuserbyname/" + username;
        System.out.println("User Service Base URL: " + url);
        if (!userServiceBaseUrl.startsWith("http")) {
            throw new IllegalArgumentException("Invalid user-service.base-url: " + userServiceBaseUrl);
        }
        return restTemplate.getForObject(url, UserDTO.class);
    }
}
