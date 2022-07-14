package group.revealed.core.jira.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class ChangeIssueStatus
{

    @JsonProperty("transition")
    private Transition transition;
}