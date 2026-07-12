package com.codingrodent.motd.service;

import com.codingrodent.motd.model.Message;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MessageService {

    private List<Message> messages;

    @PostConstruct
    void loadMessages() throws IOException {
        var resource = new ClassPathResource("messages.json");
        messages = new ObjectMapper().readValue(resource.getInputStream(), new TypeReference<>() {});
    }

    public Message getRandomMessage() {
        return messages.get(ThreadLocalRandom.current().nextInt(messages.size()));
    }

    public int getCount() {
        return messages.size();
    }
}
