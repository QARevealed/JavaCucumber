package group.revealed.core.xray.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Request body class for linking uploaded test results execution with test plan.
 */
@Setter
@AllArgsConstructor
public class XrayTestPlanBody
{

    @JsonProperty("add")
    private List<String> add;
}
