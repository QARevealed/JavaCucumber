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
public class AvatarUrls {

    @JsonProperty("48x48")
    private String jsonMember48x48;
    @JsonProperty("24x24")
    private String jsonMember24x24;
    @JsonProperty("16x16")
    private String jsonMember16x16;
    @JsonProperty("32x32")
    private String jsonMember32x32;
}