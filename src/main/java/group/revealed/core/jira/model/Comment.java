package group.revealed.core.jira.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {

    @JsonProperty("total")
    private int total;
    @JsonProperty("comments")
    private List<Object> comments;
    @JsonProperty("maxResults")
    private int maxResults;
    @JsonProperty("startAt")
    private int startAt;
}