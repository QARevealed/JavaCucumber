package group.revealed.core.xray;

import java.io.IOException;

public interface XrayService
{
    //@formatter:off
    String uploadCucumberResults(String reportFileName, String clientId, String clientSecret) throws Exception;
   // String uploadTestNgResults(String projectKey, String reportFileName);
   // void addResultsIntoTestPlan(String resultId, String testPlanId);
    void getFeatureFileByFilter(Integer filterId, String clientId, String clientSecret) throws IOException;
    //@formatter:on
}