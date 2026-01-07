package inventory.productService.utils;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import system.inventory.commonutilities.commonDto.LoggedInUserDTO;

@Component
public class UserKafkaConsumer {

    private LoggedInUserDTO currentLoggedInUser;

    @KafkaListener(topics = "user-topic", groupId = "product-group")
    public void consumeUserData(LoggedInUserDTO userDTO) {
        System.out.println("Received user data: " + userDTO.getUserName());
        this.currentLoggedInUser = userDTO;
    }

    public LoggedInUserDTO getCurrentLoggedInUser() {
        return currentLoggedInUser;
    }
}
