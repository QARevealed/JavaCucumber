package group.revealed.core.jira.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusesItem {

    @JsonProperty("color")
    private String color;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("statusCount")
    private int statusCount;
    @JsonProperty("id")
    private int id;
    @JsonProperty("isFinal")
    private boolean isFinal;
    @JsonProperty("statusPercent")
    private double statusPercent;
    @JsonProperty("isNative")
    private boolean isNative;
}