package group.revealed.pages;

public class testnaKlasa
{

/*    private final XrayService xrayService = new XrayServiceImpl();
    private final JiraService jiraService = new JiraServiceImpl();
    private final Configuration configuration = new Configuration();

    @Test
    public void changeIssueStatus()
    {
        RequestSpecification requestSpecification;

        String jiraURL = configuration.getJiraURL();
        String jiraUsername = configuration.getJiraUsername();
        String jiraToken = configuration.getJiraToken();
        String issueId = "29384";
        Integer status = 11;

        PreemptiveBasicAuthScheme preemptiveBasicAuthScheme = new PreemptiveBasicAuthScheme();
        preemptiveBasicAuthScheme.setUserName(jiraUsername);
        preemptiveBasicAuthScheme.setPassword(jiraToken);

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(jiraURL);
        builder.setAuth(preemptiveBasicAuthScheme);
        builder.setContentType(ContentType.JSON);
        requestSpecification = builder.build();
*//*        Issue rez = given(requestSpecification)
                //  .pathParam("issue_id", issueId)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().as(Issue.class);*//*
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
    }*/
}
