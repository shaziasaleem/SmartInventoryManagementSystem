package userService.inventory.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import system.inventory.commonutilities.commonDto.LoggedInUserDTO;

@Service
public class UserKafkaProducer {

    private final KafkaTemplate<String, LoggedInUserDTO> kafkaTemplate;

    public UserKafkaProducer(KafkaTemplate<String, LoggedInUserDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLoggedInUserDetails(LoggedInUserDTO userDTO) {
        kafkaTemplate.send("user-topic", userDTO);
    }
}

