package com.freight.fox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Items {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("owner")
    private Owner owner;

    @JsonProperty("language")
    private String language;

    @JsonProperty("stargazers_count")
    private Long stargazersCount;

    @JsonProperty("forks_count")
    private Long forksCount;

    @JsonProperty("updated_at")
    private String updatedAt;
}
