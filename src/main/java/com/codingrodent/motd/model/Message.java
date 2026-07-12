package com.codingrodent.motd.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "A message of the day")
public record Message(
    @JsonProperty("text")
    @Schema(description = "The message text", example = "Have a great day!")
    String text
) {}
