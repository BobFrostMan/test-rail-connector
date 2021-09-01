import client.TestRailClient;
import client.entity.*;

import java.util.List;

public class Demo {

    public static void main(String[] args) {
        //Please provide email, password, url as a command line arguments before you run this
        String email = System.getProperty("email");
        String password = System.getProperty("password");
        String testRailUrl = System.getProperty("url");

        TestRailClient testRail = new TestRailClient(testRailUrl, email, password);
        //TestRail has different projects
        Project project = testRail.getProjectByName("Flink - Consumer Apps");
        //Each project has different test suites
        TestSuite suite = testRail.getMasterTestSuite(project.getId());
        //For each project there should be a template of test case
        Template template = testRail.getDefaultTemplate(project.getId());
        //Each test suite may have different sections (nested sections), to group test cases
        Section section = testRail.getSectionByName("UI Automation smoke test suite", project.getId(), suite.getId());
        if (section == null) {
            section = testRail.addSection("UI Automation smoke test suite", "Automatically generated section for test automation", project.getId());
        }
        //In each section there can be multiple test cases
        List<TestCase> testCaseList = testRail.getTestCases(project.getId(), suite.getId(), section.getId());

        //Test case can be added/updated deleted from section
        TestCase newTestCase = new TestCase();
        newTestCase.setAutomationStatus(TestAutomationStatus.DONE.getStatus());
        newTestCase.setTitle("Check that test cases possible to be added via API");
        newTestCase.setPreconditions("Take lot's of coffee, tobacco and anything you need to implement API client for Test Rail");
        newTestCase.setExpected("You should be really angry and exhausted, but glad that you implemented that and it's finally over :)");
        newTestCase.addStep("Create simple API client")
                .addStep("Create wrapper client to execute Test Rail API requests")
                .addStep("Organize objects structure for Test Rail API client")
                .addStep("Read lot's of docs, and references to Test Rail API on https://www.gurock.com/testrail/docs/api/reference/cases#addcase")
                .addStep("Try to implement TestRail API client")
                .addStep("Loose your mind and get mad because this API is terrible")
                .addStep("Waste lots of nerves, coffee, tobacco, and hours of sleep")
                .addStep("Implement it anyway :(");
        TestCase createdTestCase = testRail.createTestCase(newTestCase, section.getId());
        newTestCase.setExpected(newTestCase.getExpected() + "\n\rTC will be also updated");
        TestCase updatedTestCase = testRail.updateTestCase(createdTestCase.getId(), newTestCase);

        //Each project can has a few test runs
        List<TestRun> runs = testRail.getRuns(project.getId());

        TestRun tr1 = testRail.addTestRun(project.getId(), suite.getId(), "New automation test run", "Description");

        //Each test run has multiple tests
        List<Test> tests = testRail.getTestsForRun(tr1.getId());
        for (Test test : tests) {
            //for each test run test result can be added for some test (linked by testCaseId)
            testRail.addTestResult(tr1.getId(), test.getCaseId(), TestStatus.PASSED.getStatus());
        }
        //For each run test result, test results can be received
        List<TestResult> res = testRail.getResultsForRun(tr1.getId());
    }
}
