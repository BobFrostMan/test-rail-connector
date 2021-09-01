package client;

import client.api.APIClient;
import client.api.APIException;
import client.entity.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRailClient {

    private APIClient client;

    public TestRailClient(String baseUri, String username, String password) {
        client = new APIClient(baseUri);
        client.setUser(username);
        client.setPassword(password);
    }

    //region Project management

    public List<Project> getProjects() {
        JSONArray responseArr;
        try {
            responseArr = (JSONArray) client.sendGet("get_projects", null);
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
        List<Project> res = new ArrayList<>();
        for (int i = 0; i < responseArr.length(); i++) {
            JSONObject projectObj = responseArr.getJSONObject(i);
            Project project = new Project();
            project.setId(projectObj.getInt("id"));
            project.setName(projectObj.getString("name"));
            project.setUrl(projectObj.getString("url"));
            res.add(project);
        }
        return res;
    }

    public Project getProjectByName(String name) {
        JSONArray responseArr;
        try {
            responseArr = (JSONArray) client.sendGet("get_projects", null);
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < responseArr.length(); i++) {
            JSONObject projectObj = responseArr.getJSONObject(i);
            if (projectObj.getString("name").equals(name)) {
                Project project = new Project();
                project.setId(projectObj.getInt("id"));
                project.setName(projectObj.getString("name"));
                project.setUrl(projectObj.getString("url"));
                return project;
            }
        }
        return null;
    }
    //endregion

    //region TestCase management
    public List<TestCase> getTestCases(int projectId, int suiteId, int sectionId) {
        JSONArray responseArr;
        try {
            responseArr = (JSONArray) client.sendGet("get_cases/" + projectId + "&suite_id=" + suiteId + "&section_id=" + sectionId, null);
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
        List<TestCase> testCases = new ArrayList<>();
        for (int i = 0; i < responseArr.length(); i++) {
            JSONObject testCaseObject = responseArr.getJSONObject(i);
            TestCase testCase = new TestCase();
            testCase.setId(testCaseObject.getInt("id"));
            testCase.setSuiteId(testCaseObject.getInt("suite_id"));
            testCase.setPriorityId(testCaseObject.getInt("priority_id"));
            testCase.setSectionId(testCaseObject.getInt("section_id"));
            testCase.setTemplateId(testCaseObject.getInt("template_id"));
            testCase.setPreconditions(String.valueOf(testCaseObject.get("custom_preconds")));
            testCase.setSteps(String.valueOf(testCaseObject.get("custom_steps")));
            testCase.setExpected(String.valueOf(testCaseObject.get("custom_expected")));
            testCase.setAutomationStatus(Integer.valueOf(String.valueOf(testCaseObject.get("custom_ta_status"))));
            testCases.add(testCase);
        }
        return testCases;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public TestCase createTestCase(TestCase testCase, int sectionId) {
        Map req = new HashMap();
        req.put("title", testCase.getTitle());
        req.put("template_id", testCase.getTemplateId());
        req.put("priority_id", testCase.getPriorityId());
        req.put("custom_expected", testCase.getExpected());
        req.put("custom_preconds", testCase.getPreconditions());
        req.put("custom_steps", testCase.getSteps());
        req.put("custom_ta_status", testCase.getAutomationStatus());
        try {
            JSONObject testCaseObject = (JSONObject) client.sendPost("add_case/" + sectionId, req);
            TestCase createdTestCase = new TestCase();
            createdTestCase.setId(testCaseObject.getInt("id"));
            createdTestCase.setSuiteId(testCaseObject.getInt("suite_id"));
            createdTestCase.setPriorityId(testCaseObject.getInt("priority_id"));
            createdTestCase.setSectionId(testCaseObject.getInt("section_id"));
            createdTestCase.setTemplateId(testCaseObject.getInt("template_id"));
            createdTestCase.setPreconditions(String.valueOf(testCaseObject.get("custom_preconds")));
            createdTestCase.setSteps(String.valueOf(testCaseObject.get("custom_steps")));
            createdTestCase.setAutomationStatus(Integer.valueOf(String.valueOf(testCaseObject.get("custom_ta_status"))));
            return createdTestCase;
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
    }

    public TestCase updateTestCase(int testCaseId, TestCase testCase) {
        Map req = new HashMap();
        req.put("title", testCase.getTitle());
        req.put("template_id", testCase.getTemplateId());
        req.put("priority_id", testCase.getPriorityId());
        req.put("custom_expected", testCase.getExpected());
        req.put("custom_preconds", testCase.getPreconditions());
        req.put("custom_steps", testCase.getSteps());
        req.put("custom_ta_status", testCase.getAutomationStatus());
        try {
            JSONObject testCaseObject = (JSONObject) client.sendPost("update_case/" + testCaseId, req);
            TestCase updatedResultCase = new TestCase();
            updatedResultCase.setId(testCaseObject.getInt("id"));
            updatedResultCase.setSuiteId(testCaseObject.getInt("suite_id"));
            updatedResultCase.setPriorityId(testCaseObject.getInt("priority_id"));
            updatedResultCase.setSectionId(testCaseObject.getInt("section_id"));
            updatedResultCase.setTemplateId(testCaseObject.getInt("template_id"));
            updatedResultCase.setPreconditions(String.valueOf(testCaseObject.get("custom_preconds")));
            updatedResultCase.setSteps(String.valueOf(testCaseObject.get("custom_steps")));
            updatedResultCase.setAutomationStatus(Integer.valueOf(String.valueOf(testCaseObject.get("custom_ta_status"))));
            return updatedResultCase;
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
    }
    //endregion


    //region Test Suite management
    public List<TestSuite> getTestSuites(int projectId) {
        JSONArray responseArr;
        try {
            responseArr = (JSONArray) client.sendGet("get_suites/" + projectId, null);
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
        List<TestSuite> testSuites = new ArrayList<>();
        for (int i = 0; i < responseArr.length(); i++) {
            JSONObject suiteObj = responseArr.getJSONObject(i);
            TestSuite suite = new TestSuite();
            suite.setId(suiteObj.getInt("id"));
            suite.setProjectId(suiteObj.getInt("project_id"));
            suite.setName(suiteObj.getString("name"));
            suite.setMaster(suiteObj.getBoolean("is_master"));
            suite.setUrl(suiteObj.getString("url"));
            testSuites.add(suite);
        }
        return testSuites;
    }

    public TestSuite getMasterTestSuite(int projectId) {
        JSONArray responseArr;
        try {
            responseArr = (JSONArray) client.sendGet("get_suites/" + projectId, null);
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < responseArr.length(); i++) {
            JSONObject suiteObj = responseArr.getJSONObject(i);
            if (suiteObj.getBoolean("is_master")) {
                TestSuite suite = new TestSuite();
                suite.setId(suiteObj.getInt("id"));
                suite.setProjectId(suiteObj.getInt("project_id"));
                suite.setName(suiteObj.getString("name"));
                suite.setMaster(suiteObj.getBoolean("is_master"));
                suite.setUrl(suiteObj.getString("url"));
                return suite;
            }
        }
        return null;
    }
    //endregion

    //region Templates management
    public Template getDefaultTemplate(int projectId) {
        JSONArray responseArr;
        try {
            responseArr = (JSONArray) client.sendGet("get_templates/" + projectId, null);
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < responseArr.length(); i++) {
            JSONObject templateObj = responseArr.getJSONObject(i);
            if (templateObj.getBoolean("is_default")) {
                Template template = new Template();
                template.setId(templateObj.getInt("id"));
                template.setName(templateObj.getString("name"));
                template.setDefault(templateObj.getBoolean("is_default"));
                return template;
            }
        }
        return null;
    }
    //endregion

    //region Sections management
    public List<Section> getSections(int projectId, int suiteId) {
        JSONArray responseArr;
        try {
            responseArr = (JSONArray) client.sendGet("get_sections/" + projectId + "&suite_id=" + suiteId, null);
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
        List<Section> testSuiteSections = new ArrayList<>();
        for (int i = 0; i < responseArr.length(); i++) {
            JSONObject sectionObj = responseArr.getJSONObject(i);
            Section section = new Section();
            section.setId(sectionObj.getInt("id"));
            Object parentId = sectionObj.get("parent_id");
            section.setParentId("null".equals(parentId.toString()) ? null : sectionObj.getInt("parent_id"));
            section.setSuiteId(sectionObj.getInt("suite_id"));
            section.setName(sectionObj.getString("name"));
            section.setDescription(String.valueOf(sectionObj.get("description")));
            testSuiteSections.add(section);
        }
        return testSuiteSections;
    }

    public Section getSectionByName(String sectionName, int projectId, int suiteId) {
        JSONArray responseArr;
        try {
            responseArr = (JSONArray) client.sendGet("get_sections/" + projectId + "&suite_id=" + suiteId, null);
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < responseArr.length(); i++) {
            JSONObject sectionObj = responseArr.getJSONObject(i);
            if (sectionObj.getString("name").equals(sectionName)) {
                Section section = new Section();
                section.setId(sectionObj.getInt("id"));
                Object parentId = sectionObj.get("parent_id");
                section.setParentId("null".equals(parentId.toString()) ? null : sectionObj.getInt("parent_id"));
                section.setSuiteId(sectionObj.getInt("suite_id"));
                section.setName(sectionObj.getString("name"));
                section.setDescription(String.valueOf(sectionObj.get("description")));
                return section;
            }
        }
        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Section addSection(String name, String description, int projectId) {
        Map req = new HashMap();
        req.put("name", name);
        req.put("description", description);
        JSONObject sectionObj;
        try {
            sectionObj = (JSONObject) client.sendPost("add_section/" + projectId, req);
            Section section = new Section();
            section.setId(sectionObj.getInt("id"));
            Object parentId = sectionObj.get("parent_id");
            section.setParentId("null".equals(parentId.toString()) ? null : sectionObj.getInt("parent_id"));
            section.setSuiteId(sectionObj.getInt("suite_id"));
            section.setName(sectionObj.getString("name"));
            section.setDescription(sectionObj.getString("description"));
            return section;
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
    }
    //endregion

    //region Test Run Management
    public List<TestRun> getRuns(int projectId) {
        JSONArray responseArr;
        try {
            responseArr = (JSONArray) client.sendGet("get_runs/" + projectId, null);
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
        List<TestRun> runs = new ArrayList<>();
        for (int i = 0; i < responseArr.length(); i++) {
            JSONObject testRunObj = responseArr.getJSONObject(i);
            TestRun testRun = new TestRun();
            testRun.setId(testRunObj.getInt("id"));
            testRun.setName(testRunObj.getString("name"));
            Integer planId = testRunObj.isNull("plan_id") ? null : testRunObj.getInt("plan_id");
            testRun.setPlanId(planId);
            testRun.setProjectId(testRunObj.getInt("project_id"));
            testRun.setSuiteId(testRunObj.getInt("suite_id"));
            testRun.setUrl(testRunObj.getString("url"));
            testRun.setUntestedCount(testRunObj.getInt("untested_count"));
            testRun.setBlockedCount(testRunObj.getInt("blocked_count"));
            testRun.setFailedCount(testRunObj.getInt("failed_count"));
            testRun.setPassedCount(testRunObj.getInt("passed_count"));
            testRun.setRetestCount(testRunObj.getInt("retest_count"));
            runs.add(testRun);
        }
        return runs;
    }

    public TestRun getRun(int runId) {
        try {
            JSONObject testRunObj = (JSONObject) client.sendGet("get_run/" + runId, null);
            TestRun testRun = new TestRun();
            testRun.setId(testRunObj.getInt("id"));
            testRun.setName(testRunObj.getString("name"));
            Integer planId = testRunObj.isNull("plan_id") ? null : testRunObj.getInt("plan_id");
            testRun.setPlanId(planId);
            testRun.setProjectId(testRunObj.getInt("project_id"));
            testRun.setSuiteId(testRunObj.getInt("suite_id"));
            testRun.setUrl(testRunObj.getString("url"));
            testRun.setBlockedCount(testRunObj.getInt("blocked_count"));
            testRun.setFailedCount(testRunObj.getInt("failed_count"));
            testRun.setPassedCount(testRunObj.getInt("passed_count"));
            testRun.setRetestCount(testRunObj.getInt("retest_count"));
            return testRun;
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
    }

    public TestRun addTestRun(int projectId, int suiteId, String name, String description) {
        Map req = new HashMap();
        req.put("name", name);
        req.put("description", description);
        req.put("suite_id", suiteId);
        JSONObject testRunObj;
        try {
            testRunObj = (JSONObject) client.sendPost("add_run/" + projectId, req);
            TestRun testRun = new TestRun();
            testRun.setId(testRunObj.getInt("id"));
            testRun.setProjectId(projectId);
            testRun.setSuiteId(suiteId);
            testRun.setName(testRunObj.getString("name"));
            testRun.setUrl(testRunObj.getString("url"));
            testRun.setUntestedCount(testRunObj.getInt("untested_count"));
            testRun.setBlockedCount(testRunObj.getInt("blocked_count"));
            testRun.setFailedCount(testRunObj.getInt("failed_count"));
            testRun.setPassedCount(testRunObj.getInt("passed_count"));
            testRun.setRetestCount(testRunObj.getInt("retest_count"));
            return testRun;
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
    }
    //endregion

    //region Results management
    public List<TestResult> getResultsForRun(int runId) {
        JSONArray responseArr;
        try {
            responseArr = (JSONArray) client.sendGet("get_results_for_run/" + runId, null);
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
        List<TestResult> testResults = new ArrayList<>();
        for (int i = 0; i < responseArr.length(); i++) {
            JSONObject testResultObj = responseArr.getJSONObject(i);
            TestResult testResult = new TestResult();
            testResult.setId(testResultObj.getInt("id"));
            Integer statusId = testResultObj.isNull("status_id") ? null : testResultObj.getInt("status_id");
            testResult.setStatusId(statusId);
            testResult.setTestId(testResultObj.getInt("test_id"));
            Integer assigneeId = testResultObj.isNull("assignedto_id") ? null : testResultObj.getInt("assignedto_id");
            testResult.setAssignedToId(assigneeId);
            testResults.add(testResult);
        }
        return testResults;
    }

    public void addTestResult(int runId, int caseId, int testStatus) {
        Map req = new HashMap();
        req.put("status_id", testStatus);
        try {
            client.sendPost("add_result_for_case/" + runId + "/" + caseId, req);
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Test> getTestsForRun(int runId){
        JSONArray responseArr;
        try {
            responseArr = (JSONArray) client.sendGet("get_tests/" + runId + "&limit=1000&offset=0", null);
        } catch (IOException | APIException e) {
            throw new RuntimeException(e);
        }
        List<Test> testsList = new ArrayList<>();
        for (int i = 0; i < responseArr.length(); i++) {
            JSONObject testCaseObject = responseArr.getJSONObject(i);
            Test test = new Test();
            test.setId(testCaseObject.getInt("id"));
            test.setCaseId(testCaseObject.getInt("case_id"));
            Integer suiteId = testCaseObject.isNull("suite_id") ? null : testCaseObject.getInt("suite_id");
            test.setTitle(testCaseObject.getString("title"));
            test.setSuiteId(suiteId);
            test.setPriorityId(testCaseObject.getInt("priority_id"));
            Integer sectionId = testCaseObject.isNull("section_id") ? null : testCaseObject.getInt("section_id");
            test.setSectionId(sectionId);
            test.setTemplateId(testCaseObject.getInt("template_id"));
            test.setPreconditions(String.valueOf(testCaseObject.get("custom_preconds")));
            test.setSteps(String.valueOf(testCaseObject.get("custom_steps")));
            test.setExpected(String.valueOf(testCaseObject.get("custom_expected")));
            Integer automationStatus = testCaseObject.isNull("custom_ta_status") ? null : testCaseObject.getInt("custom_ta_status");
            test.setAutomationStatus(automationStatus);
            testsList.add(test);
        }
        return testsList;
    }
    //endregion
}
