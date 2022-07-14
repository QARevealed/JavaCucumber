package group.revealed.core.jira.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class Transition
{

    @JsonProperty("id")
    private String id;
}