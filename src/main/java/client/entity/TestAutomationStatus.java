package client.entity;

public enum  TestAutomationStatus {

    NOT_APPLICABLE(4),
    IN_PROGRESS(3),
    DONE(2);

    private final int status;

    TestAutomationStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
