package client.entity;

public class Test {

    private String title;
    private String preconditions;
    private String steps;
    private String expected;
    private Integer id;
    private Integer priorityId;
    private Integer sectionId;
    private Integer suiteId;
    private Integer templateId;
    private Integer automationStatus;
    private Integer caseId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreconditions() {
        return preconditions;
    }

    public void setPreconditions(String preconditions) {
        this.preconditions = preconditions;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(Integer suiteId) {
        this.suiteId = suiteId;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getAutomationStatus() {
        return automationStatus;
    }

    public void setAutomationStatus(Integer automationStatus) {
        this.automationStatus = automationStatus;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "title='" + title + '\'' +
                ", preconditions='" + preconditions + '\'' +
                ", steps='" + steps + '\'' +
                ", expected='" + expected + '\'' +
                ", id=" + id +
                ", priorityId=" + priorityId +
                ", sectionId=" + sectionId +
                ", suiteId=" + suiteId +
                ", templateId=" + templateId +
                ", automationStatus=" + automationStatus +
                ", caseId=" + caseId +
                '}';
    }
}
