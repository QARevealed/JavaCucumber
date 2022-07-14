package group.revealed.core.jira;


import group.revealed.core.jira.model.*;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Service that provide services for JIRA's API. Exposed services are using gateway for access.
 */
public class JiraServiceImpl implements JiraService
{

    private RequestSpecification requestSpecification;


    public JiraServiceImpl() {

    }

    /**
     * Change JIRA task status.
     *
     * @param issueId ID of Jira issue
     * @param status  ID of new status to be set for the issue
     */
    public void changeIssueStatus(String issueId, Integer status, String jiraURL, String jiraUsername, String jiraToken) {
        PreemptiveBasicAuthScheme preemptiveBasicAuthScheme = new PreemptiveBasicAuthScheme();
        preemptiveBasicAuthScheme.setUserName(jiraUsername);
        preemptiveBasicAuthScheme.setPassword(jiraToken);

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(jiraURL);
        builder.setAuth(preemptiveBasicAuthScheme);
        builder.setContentType(ContentType.JSON);
        this.requestSpecification = builder.build();
        final Transition transition = new Transition(String.valueOf(status));
        final ChangeIssueStatus changeIssueStatus = new ChangeIssueStatus();
        changeIssueStatus.setTransition(transition);
        //@formatter:off
        given(requestSpecification)
                .pathParam("issue_id", issueId)
                .body(changeIssueStatus)
        .when()
                .post("/issue/{issue_id}/transitions")
        .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        //@formatter:on
    }

    /**
     * Update field: Test Environments
     *
     * @param issueId     ID of Jira issue
     * @param environment Test Environments value to be added to the issue
     */
    public void updateTestEnvironmentsField(String issueId, String environment) {

        UpdateFieldItem updateFieldItem = new UpdateFieldItem();
        updateFieldItem.setAddField(environment);
        List<UpdateFieldItem> updateFieldItems = new ArrayList<>();
        updateFieldItems.add(updateFieldItem);
        Update update = new Update();
        update.setCustomfield_13825(updateFieldItems);
        UpdateRequest updateRequest = new UpdateRequest(update);

        //@formatter:off
        given(requestSpecification)
                .pathParam("issue_id", issueId)
                .body(updateRequest)
        .when()
                .put("/issue/{issue_id}")
        .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        //@formatter:on
    }

    /**
     * Update field: Summary
     *
     * @param issueId ID of Jira issue
     * @param summary New summary to be set for the issue
     */
    public void updateSummary(String issueId, String summary) {

        UpdateFieldItem updateFieldItem = new UpdateFieldItem();
        updateFieldItem.setSetField(summary);
        List<UpdateFieldItem> updateFieldItems = new ArrayList<>();
        updateFieldItems.add(updateFieldItem);
        Update update = new Update();
        update.setSummary(updateFieldItems);
        UpdateRequest updateRequest = new UpdateRequest(update);

        //@formatter:off
        given(requestSpecification)
                .pathParam("issue_id", issueId)
                .body(updateRequest)
        .when()
                .put("/issue/{issue_id}")
        .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        //@formatter:on
    }
}
