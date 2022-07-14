package group.revealed.core.jira;

public interface JiraService
{
    //@formatter:off
    void changeIssueStatus(String issueId, Integer status, String jiraURL, String jiraUsername, String jiraToken);
    void updateTestEnvironmentsField(String issueId, String environment);
    void updateSummary(String issueId, String summary);
    //@formatter:on
}
