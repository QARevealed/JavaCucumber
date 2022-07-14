package group.revealed.core.xray;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Service that provide services for Xray's API. Exposed services are using gateway for access.
 */
public class XrayServiceImpl implements XrayService
{

  //  private RequestSpecification requestSpecification;
//    private static final String BASE_URI = "https://xray.cloud.xpand-it.com/api/v1/";
    private static final String BASE_URI = "https://xray.cloud.getxray.app/api/v1";

    String finalToken = "";

    /**
     * Default constructor.
     */
    public XrayServiceImpl()
    {


    }

    /**
     * Upload cucumber test results to Xray.
     *
     *
     * @return uploaded results ID
     */

    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
   public String uploadCucumberResults(String reportFileName, String clientId, String clientSecret) throws Exception
   {
       // Sending get request

//       RestAssured.baseURI = "https://xray.cloud.xpand-it.com/api/v1/authenticate";
       RestAssured.baseURI = "https://xray.cloud.getxray.app/api/v1/authenticate";

       String upit = "{ \"client_id\":" + clientId + ",\"client_secret\":" + clientSecret + " }";
       System.out.println(upit);

       RequestSpecification requestAuth = RestAssured.given();
       requestAuth.body(upit);
       requestAuth.contentType("application/json");

       Response responseAuth = requestAuth.post("");

       int statusCode = responseAuth.getStatusCode();
       System.out.println("status code:" + statusCode);
       Assert.assertEquals(200, statusCode);
       System.out.println("Login response:");
       System.out.println(responseAuth.asString());

       String responseBody = responseAuth.getBody().asString();
       finalToken = responseBody.replace("\"","");
       //RestAssured.baseURI
       URL url = new URL(BASE_URI+"/import/execution/cucumber");
       HttpURLConnection conn = (HttpURLConnection) url.openConnection();

       conn.setRequestProperty("Authorization","Bearer "+finalToken);
       conn.setDoOutput(true);
       conn.setRequestProperty("Accept", "application/json");
       conn.setRequestProperty("Content-Type","application/json");
       conn.setRequestMethod("POST");

       String file = ("target/cucumber/" + reportFileName);
       String json = readFileAsString(file);
       //System.out.println("jss:"+json);
       byte[] out = json.getBytes(StandardCharsets.UTF_8);
       OutputStream stream = conn.getOutputStream();
       stream.write(out);
       System.out.println(conn.getResponseCode() + " " + conn.getResponseMessage());
       String key ;
       try(BufferedReader br = new BufferedReader(
               new InputStreamReader(conn.getInputStream(), "utf-8"))) {
           StringBuilder response = new StringBuilder();
           String responseLine ;
           while ((responseLine = br.readLine()) != null) {
               response.append(responseLine.trim());
           }
           System.out.println(response.toString());
           JSONObject respJSON = new JSONObject(response.toString());
           key = respJSON.getString("key");

       }
       System.out.println("key:"+key);
       conn.disconnect();
       return key;
    }


/*    public String uploadTestNgResults(String projectKey, String reportFileName) {
        final File file = new File("target/surefire-reports/" + reportFileName);
        //@formatter:off
        return given(requestSpecification)
                .contentType("multipart/form-data")
                .queryParam("projectKey", projectKey)
                .multiPart("file", file)
                .when()
                .post("/import/execution/testng")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().path("testExecIssue.key").toString();
        //@formatter:on
    }*/

    /**
     * Link uploaded test results execution with test plan.
     *
     * @param resultId
     * @param testPlanId
     */
/*    public void addResultsIntoTestPlan(String resultId, String testPlanId) {
        List<String> testResults = new ArrayList<>();
        testResults.add(resultId);
        XrayTestPlanBodyV body = new XrayTestPlanBodyV(testResults);
        //@formatter:off
        given(requestSpecification)
                .pathParam("testPlan_id", testPlanId)
                .body(body)
                .when()
                .post("/api/testplan/{testPlan_id}/testexecution")
                .then()
                .statusCode(HttpStatus.SC_OK);
        //@formatter:on
    }*/

    /**
     * Get feature file for XRAY's task filtered by provided filter.
     *
     * @param filterId
     * @throws IOException
     */
    public void getFeatureFileByFilter(Integer filterId, String clientId, String clientSecret) throws IOException {
        //@formatter:off
        // Sending get request

//        RestAssured.baseURI = "https://xray.cloud.xpand-it.com/api/v1/authenticate";
        RestAssured.baseURI = "https://xray.cloud.getxray.app/api/v1/authenticate";

        String upit = "{ \"client_id\":" + clientId + ",\"client_secret\":" + clientSecret + " }";
        System.out.println(upit);

        RequestSpecification requestAuth = RestAssured.given();
        requestAuth.body(upit);
        requestAuth.contentType("application/json");

        Response responseAuth = requestAuth.post("");

        int statusCode = responseAuth.getStatusCode();
        System.out.println("status code:" + statusCode);
        Assert.assertEquals(200, statusCode);
        System.out.println("Login response:");
        System.out.println(responseAuth.asString());

        String responseBody = responseAuth.getBody().asString();
        finalToken = responseBody.replace("\"","");

//        URL url = new URL("https://xray.cloud.xpand-it.com/api/v1/export/cucumber?filter="+filterId);
        URL url = new URL("https://xray.cloud.getxray.app/api/v1/export/cucumber?filter="+filterId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("Authorization","Bearer "+finalToken);

        //conn.setRequestProperty("Content-Type","application/zip");
        conn.setRequestMethod("GET");


/*
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;

        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }

        in.close();
        // printing result from response
        System.out.println("Response:-" + response.toString());
*/

        //@formatter:on

        final String path = "src/test/resources/features/";
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(path + "featuresByFilter_" + filterId + ".zip");
        if (file.exists()) {
            Files.delete(Paths.get(file.getPath()));
        }

        InputStream in = conn.getInputStream();
        FileOutputStream out = new FileOutputStream(path + "featuresByFilter_" + filterId + ".zip");
        copy(in, out, 1024);
        out.close();

        String source = path + "featuresByFilter_" + filterId + ".zip";
        String destination = "src/test/resources/features/";


        try {
            ZipFile zipFile = new ZipFile(source);
 /*           if (zipFile.isEncrypted()) {
                zipFile.setPassword(password);
            }*/
            zipFile.extractAll(destination);
        } catch (ZipException e) {
            e.printStackTrace();
        }

      //  FileOutputStream fileOutputStream = new FileOutputStream(file, false);
        //IOUtils.write(response.toString(), fileOutputStream, Charset.defaultCharset());
       // fileOutputStream.close();

    }

    public static void copy(InputStream input, OutputStream output, int bufferSize) throws IOException {
        byte[] buf = new byte[bufferSize];
        int n = input.read(buf);
        while (n >= 0) {
            output.write(buf, 0, n);
            n = input.read(buf);
        }
        output.flush();
    }
}
