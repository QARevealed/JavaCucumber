package group.revealed.core.jira.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Fields
{

    @JsonProperty("customfield_15400")
    private Object customfield15400;
    @JsonProperty("customfield_15401")
    private Object customfield15401;
    @JsonProperty("customfield_10230")
    private Object customfield10230;
    @JsonProperty("fixVersions")
    private List<Object> fixVersions;
    @JsonProperty("customfield_10231")
    private Object customfield10231;
    @JsonProperty("customfield_10232")
    private Object customfield10232;
    @JsonProperty("customfield_11200")
    private Object customfield11200;
    @JsonProperty("resolution")
    private Resolution resolution;
    @JsonProperty("customfield_10233")
    private Object customfield10233;
    @JsonProperty("customfield_11201")
    private Object customfield11201;
    @JsonProperty("customfield_10234")
    private Object customfield10234;
    @JsonProperty("customfield_10235")
    private Object customfield10235;
    @JsonProperty("customfield_10225")
    private Object customfield10225;
    @JsonProperty("customfield_14704")
    private Object customfield14704;
    @JsonProperty("customfield_10226")
    private Object customfield10226;
    @JsonProperty("customfield_12403")
    private Object customfield12403;
    @JsonProperty("customfield_10503")
    private Customfield10503 customfield10503;
    @JsonProperty("customfield_10229")
    private Object customfield10229;
    @JsonProperty("customfield_10900")
    private Object customfield10900;
    @JsonProperty("customfield_10504")
    private Object customfield10504;
    @JsonProperty("customfield_10901")
    private Object customfield10901;
    @JsonProperty("customfield_10505")
    private Object customfield10505;
    @JsonProperty("customfield_10508")
    private Object customfield10508;
    @JsonProperty("customfield_10904")
    private Object customfield10904;
    @JsonProperty("customfield_10509")
    private String customfield10509;
    @JsonProperty("lastViewed")
    private Object lastViewed;
    @JsonProperty("customfield_12002")
    private Object customfield12002;
    @JsonProperty("customfield_12001")
    private Object customfield12001;
    @JsonProperty("priority")
    private Priority priority;
    @JsonProperty("customfield_10221")
    private Object customfield10221;
    @JsonProperty("customfield_10222")
    private Object customfield10222;
    @JsonProperty("customfield_10223")
    private Object customfield10223;
    @JsonProperty("customfield_12402")
    private Object customfield12402;
    @JsonProperty("labels")
    private List<Object> labels;
    @JsonProperty("customfield_10224")
    private Object customfield10224;
    @JsonProperty("customfield_10216")
    private Object customfield10216;
    @JsonProperty("customfield_10217")
    private Object customfield10217;
    @JsonProperty("customfield_10218")
    private Object customfield10218;
    @JsonProperty("customfield_13728")
    private Object customfield13728;
    @JsonProperty("aggregatetimeoriginalestimate")
    private Object aggregatetimeoriginalestimate;
    @JsonProperty("timeestimate")
    private Object timeestimate;
    @JsonProperty("versions")
    private List<Object> versions;
    @JsonProperty("customfield_10219")
    private Object customfield10219;
    @JsonProperty("customfield_11706")
    private List<Customfield11706Item> customfield11706;
    @JsonProperty("issuelinks")
    private List<Object> issuelinks;
    @JsonProperty("assignee")
    private Assignee assignee;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("components")
    private List<Object> components;
    @JsonProperty("customfield_15100")
    private Object customfield15100;
    @JsonProperty("customfield_10210")
    private Object customfield10210;
    @JsonProperty("customfield_11301")
    private String customfield11301;
    @JsonProperty("customfield_10600")
    private Object customfield10600;
    @JsonProperty("aggregatetimeestimate")
    private Object aggregatetimeestimate;
    @JsonProperty("customfield_10209")
    private Object customfield10209;
    @JsonProperty("creator")
    private Creator creator;
    @JsonProperty("subtasks")
    private List<Object> subtasks;
    @JsonProperty("reporter")
    private Reporter reporter;
    @JsonProperty("aggregateprogress")
    private Aggregateprogress aggregateprogress;
    @JsonProperty("customfield_12103")
    private Object customfield12103;
    @JsonProperty("customfield_13702")
    private Object customfield13702;
    @JsonProperty("customfield_13701")
    private Object customfield13701;
    @JsonProperty("customfield_13825")
    private List<Object> customfield13825;
    @JsonProperty("customfield_13703")
    private Object customfield13703;
    @JsonProperty("customfield_11803")
    private Object customfield11803;
    @JsonProperty("customfield_13827")
    private List<Object> customfield13827;
    @JsonProperty("customfield_11802")
    private Object customfield11802;
    @JsonProperty("customfield_13707")
    private Object customfield13707;
    @JsonProperty("progress")
    private Progress progress;
    @JsonProperty("votes")
    private Votes votes;
    @JsonProperty("worklog")
    private Worklog worklog;
    @JsonProperty("issuetype")
    private Issuetype issuetype;
    @JsonProperty("timespent")
    private Object timespent;
    @JsonProperty("customfield_15202")
    private Object customfield15202;
    @JsonProperty("customfield_15203")
    private Object customfield15203;
    @JsonProperty("project")
    private Project project;
    @JsonProperty("customfield_11000")
    private Object customfield11000;
    @JsonProperty("customfield_15200")
    private Object customfield15200;
    @JsonProperty("aggregatetimespent")
    private Object aggregatetimespent;
    @JsonProperty("customfield_13700")
    private List<Customfield13700Item> customfield13700;
    @JsonProperty("customfield_13820")
    private Customfield13820 customfield13820;
    @JsonProperty("customfield_10302")
    private Object customfield10302;
    @JsonProperty("customfield_10303")
    private Object customfield10303;
    @JsonProperty("customfield_13816")
    private Object customfield13816;
    @JsonProperty("customfield_13815")
    private List<Customfield13815Item> customfield13815;
    @JsonProperty("customfield_13818")
    private Object customfield13818;
    @JsonProperty("resolutiondate")
    private String resolutiondate;
    @JsonProperty("customfield_13817")
    private Object customfield13817;
    @JsonProperty("customfield_13819")
    private Object customfield13819;
    @JsonProperty("workratio")
    private int workratio;
    @JsonProperty("watches")
    private Watches watches;
    @JsonProperty("created")
    private String created;
    @JsonProperty("customfield_11501")
    private Object customfield11501;
    @JsonProperty("customfield_11900")
    private Object customfield11900;
    @JsonProperty("customfield_11902")
    private List<Object> customfield11902;
    @JsonProperty("customfield_11901")
    private Object customfield11901;
    @JsonProperty("customfield_11904")
    private Object customfield11904;
    @JsonProperty("customfield_11903")
    private Object customfield11903;
    @JsonProperty("updated")
    private String updated;
    @JsonProperty("timeoriginalestimate")
    private Object timeoriginalestimate;
    @JsonProperty("customfield_13001")
    private Object customfield13001;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("customfield_13000")
    private String customfield13000;
    @JsonProperty("customfield_11100")
    private Object customfield11100;
    @JsonProperty("timetracking")
    private Timetracking timetracking;
    @JsonProperty("customfield_10005")
    private Object customfield10005;
    @JsonProperty("customfield_10401")
    private Object customfield10401;
    @JsonProperty("customfield_14603")
    private Object customfield14603;
    @JsonProperty("attachment")
    private List<Object> attachment;
    @JsonProperty("customfield_10009")
    private String customfield10009;
    @JsonProperty("customfield_10803")
    private Object customfield10803;
    @JsonProperty("customfield_10807")
    private Object customfield10807;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("customfield_10000")
    private Object customfield10000;
    @JsonProperty("customfield_14602")
    private Object customfield14602;
    @JsonProperty("customfield_12303")
    private Object customfield12303;
    @JsonProperty("customfield_10004")
    private Object customfield10004;
    @JsonProperty("customfield_10236")
    private Object customfield10236;
    @JsonProperty("customfield_10237")
    private Object customfield10237;
    @JsonProperty("environment")
    private Object environment;
    @JsonProperty("customfield_11603")
    private Object customfield11603;
    @JsonProperty("customfield_10910")
    private Object customfield10910;
    @JsonProperty("customfield_11602")
    private Object customfield11602;
    @JsonProperty("customfield_11605")
    private Object customfield11605;
    @JsonProperty("duedate")
    private Object duedate;
    @JsonProperty("customfield_11607")
    private Object customfield11607;
    @JsonProperty("customfield_10914")
    private Object customfield10914;
    @JsonProperty("customfield_11606")
    private Object customfield11606;
    @JsonProperty("customfield_10915")
    private Object customfield10915;
    @JsonProperty("comment")
    private Comment comment;
    @JsonProperty("customfield_10917")
    private Object customfield10917;
}