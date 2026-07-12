package com.codingrodent.motd.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "The number of messages available")
public record MessageCount(
    @JsonProperty("count")
    @Schema(description = "Total number of available messages", example = "42")
    int count
) {}
