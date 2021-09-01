package client.entity;

public class TestRun {

    private Integer id;
    private Integer suiteId;
    private Integer projectId;
    private Integer planId;
    private String name;
    private String url;
    private Integer retestCount;
    private Integer untestedCount;
    private Integer passedCount;
    private Integer failedCount;
    private Integer blockedCount;
    /*
        private Integer customStatus1Count;
        private Integer customStatus2Count;
        private Integer customStatus3Count;
        private Integer customStatus4Count;
        private Integer customStatus5Count;
        private Integer customStatus6Count;
        private Integer customStatus7Count;
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(Integer suiteId) {
        this.suiteId = suiteId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRetestCount() {
        return retestCount;
    }

    public void setRetestCount(Integer retestCount) {
        this.retestCount = retestCount;
    }

    public Integer getUntestedCount() {
        return untestedCount;
    }

    public void setUntestedCount(Integer untestedCount) {
        this.untestedCount = untestedCount;
    }

    public Integer getPassedCount() {
        return passedCount;
    }

    public void setPassedCount(Integer passedCount) {
        this.passedCount = passedCount;
    }

    public Integer getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(Integer failedCount) {
        this.failedCount = failedCount;
    }

    public Integer getBlockedCount() {
        return blockedCount;
    }

    public void setBlockedCount(Integer blockedCount) {
        this.blockedCount = blockedCount;
    }

    @Override
    public String toString() {
        return "TestRun{" +
                "id=" + id +
                ", suiteId=" + suiteId +
                ", projectId=" + projectId +
                ", planId=" + planId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", retestCount=" + retestCount +
                ", untestedCount=" + untestedCount +
                ", passedCount=" + passedCount +
                ", failedCount=" + failedCount +
                ", blockedCount=" + blockedCount +
                '}';
    }
}
