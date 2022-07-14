package group.revealed.runner;

import group.revealed.configuration.Configuration;
import group.revealed.core.jira.JiraService;
import group.revealed.core.jira.JiraServiceImpl;
import group.revealed.core.xray.XrayService;
import group.revealed.core.xray.XrayServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestConfig {

    private final XrayService xrayService = new XrayServiceImpl();
    private final JiraService jiraService = new JiraServiceImpl();
    private final Configuration configuration = new Configuration();


    public TestConfig()
    {
    }

    @BeforeSuite
    public void beforeSuite() throws IOException {
        // get feature file here
        xrayService.getFeatureFileByFilter(10104, configuration.getXrayClientId(), configuration.getXrayClientSecret());
    }

    @AfterSuite
    public void afterSuite() throws Exception
    {
        createAllureProperties();
        DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm");
        final String timestamp = dateFormat.format(new Date().getTime());
        final String profile = "Demo tests";

        final String summary = String.format("Automated tests results [%s]; Test profile: %s",
                timestamp, profile);

        // upload results
        String executionTask = xrayService.uploadCucumberResults("TestRunner.json", configuration.getXrayClientId(), configuration.getXrayClientSecret());
        System.out.println("summary:"+summary+" exec task:"+executionTask+" url:"+configuration.getJiraURL());
        // update title
//        jiraService.updateSummary(executionTask, summary);
        // update environment
 //       jiraService.updateTestEnvironmentsField(executionTask, String.join(" ", configuration.getPlatform(), configuration.getBrowser()));
        // from open into to do
     //   jiraService.changeIssueStatus(executionTask, 10103, configuration.getJiraURL(), configuration.getJiraUsername(), configuration.getJiraToken());
        // from to do into close
        jiraService.changeIssueStatus(executionTask, 11, configuration.getJiraURL(), configuration.getJiraUsername(), configuration.getJiraToken());
    }

    /**
     * Create environment.properties for allure reporting.
     * https://docs.qameta.io/allure/#_environment
     */
    private void createAllureProperties() {
        final var path = "target/allure-results/";
        var directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(path + "environment.properties");
        // the true will append the new data, false create new empty file
        try (FileWriter fw = new FileWriter(file, false)) {
            fw.write("Time=" + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date().getTime()));
            fw.write("\n");
            fw.write("Platform=" + (!StringUtils.isBlank(configuration.getPlatformVersion()) ? "MOBILE" : "DESKTOP"));
            fw.write("\n");
            fw.write("OS=" + configuration.getPlatform().toUpperCase());
            fw.write("\n");
            fw.write("Browser=" + configuration.getBrowser().toUpperCase());
            fw.write("\n");
            fw.write("BrowserVersion=" + configuration.getBrowserVersion().toUpperCase());
            fw.write("\n");
            fw.write("Environment=Test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*    @Test
    public void changeIssueStatus()
    {
        RequestSpecification requestSpecification;

        String jiraURL = configuration.getJiraURL();
        String jiraUsername = configuration.getJiraUsername();
        String jiraToken = configuration.getJiraToken();
        String issueId = "29329";
        Integer status = 3;

        PreemptiveBasicAuthScheme preemptiveBasicAuthScheme = new PreemptiveBasicAuthScheme();
        preemptiveBasicAuthScheme.setUserName(jiraUsername);
        preemptiveBasicAuthScheme.setPassword(jiraToken);

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(jiraURL+"/issue/29329");
        builder.setAuth(preemptiveBasicAuthScheme);
        builder.setContentType(ContentType.JSON);
        requestSpecification = builder.build();
        Issue rez = given(requestSpecification)
              //  .pathParam("issue_id", issueId)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().as(Issue.class);
*//*        final Transition transition = new Transition(String.valueOf(status));
        final ChangeIssueStatus changeIssueStatus = new ChangeIssueStatus();
        changeIssueStatus.setTransition(transition);
        //@formatter:off
        given(requestSpecification)
                .pathParam("issue_id", issueId)
                .body(changeIssueStatus)
                .when()
                .post("/issue/{issue_id}/transitions")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);*//*
        //@formatter:on
    }*/

}
