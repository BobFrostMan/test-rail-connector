package client.entity;

public enum TestStatus {
    PASSED(1),
    BLOCKED(2),
    UNTESTED(3),
    RETEST(4),
    FAILED(5);

    private final int status;

    TestStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
