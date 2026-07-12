package com.codingrodent.motd.controller;

import com.codingrodent.motd.model.Message;
import com.codingrodent.motd.model.MessageCount;
import com.codingrodent.motd.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Message of the Day", description = "Provides access to messages of the day")
@RestController
@RequestMapping("/api")
public class MotdController {

    private static final Logger log = LoggerFactory.getLogger(MotdController.class);

    private final MessageService messageService;

    public MotdController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Operation(
        summary = "Get a random message of the day",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "A randomly selected message",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))
            )
        }
    )
    @GetMapping(value = "/motd", produces = MediaType.APPLICATION_JSON_VALUE)
    public Message getMessageOfTheDay() {
        log.debug("getMessageOfTheDay called");
        return messageService.getRandomMessage();
    }

    @Operation(
        summary = "Get the number of available messages",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "The total count of available messages",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageCount.class))
            )
        }
    )
    @GetMapping(value = "/motd/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageCount getMessageCount() {
        log.debug("getMessageCount called");
        return new MessageCount(messageService.getCount());
    }
}
