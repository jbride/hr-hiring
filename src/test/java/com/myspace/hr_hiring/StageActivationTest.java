package com.myspace.hr_hiring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import org.jbpm.services.api.model.UserTaskInstanceDesc;
import static org.junit.Assert.assertEquals;

public class StageActivationTest extends AbstractCaseEndToEndTest {

    @Test
    public void testEmptyCaseActivatesDocsReview() {
        Map<String, Object> data = new HashMap<>();
        String caseId = startCase(data);

        // Get the tasks.
        List<Long> taskIds = getTasksInCase(caseId, 1, 1);

        UserTaskInstanceDesc task = runtimeDataService.getTaskById(taskIds.get(0));
        assertEquals("Talent Acquisition Documents Review", task.getName());
    }

    @Test
    public void testExternalApplicantActivatesDocsReview() {
        Map<String, Object> data = new HashMap<>();
        data.put("internal", false);
        String caseId = startCase(data);

        // Get the tasks.
        List<Long> taskIds = getTasksInCase(caseId, 1, 1);

        UserTaskInstanceDesc task = runtimeDataService.getTaskById(taskIds.get(0));
        assertEquals("Talent Acquisition Documents Review", task.getName());
    }

    @Test
    public void testInternalApplicantActivatesApplicantReview() {
        Map<String, Object> data = new HashMap<>();
        data.put("internal", true);
        String caseId = startCase(data);

        // Get the tasks.
        List<Long> taskIds = getTasksInCase(caseId, 1, 1);

        UserTaskInstanceDesc task = runtimeDataService.getTaskById(taskIds.get(0));
        assertEquals("Schedule Interviews", task.getName());
    }

    @Test
    public void testExternalApplicantWithRequiresAdditionalDocsActivatesAdditionalDocumentationRequired() {
        Map<String, Object> data = new HashMap<>();
        data.put("internal", false);
        data.put("additionalDocumentsRequested", true);
        String caseId = startCase(data);

        // Get the tasks.
        List<Long> taskIds = getTasksInCase(caseId, 1, 1);

        UserTaskInstanceDesc task = runtimeDataService.getTaskById(taskIds.get(0));
        assertEquals("Submit Additional Documentation", task.getName());
    }

    @Test
    public void testInternalApplicantWithDocumentationActivatesApplicantReview() {
        Map<String, Object> data = new HashMap<>();
        data.put("internal", false);
        data.put("documentationApproved", true);
        String caseId = startCase(data);

        // Get the tasks.
        List<Long> taskIds = getTasksInCase(caseId, 1, 1);

        UserTaskInstanceDesc task = runtimeDataService.getTaskById(taskIds.get(0));
        assertEquals("Schedule Interviews", task.getName());
    }

    @Test
    public void testApplicantRejectedRequiresAdditionalNotes(){
        Map<String, Object> data = new HashMap<>();
        data.put("applicantRejected", true);
        String caseId = startCase(data);

        // Get the tasks.
        List<Long> taskIds = getTasksInCase(caseId, 1, 1);

        UserTaskInstanceDesc task = runtimeDataService.getTaskById(taskIds.get(0));
        assertEquals("Additional notes for rejection", task.getName());
    }

    @Test
    public void testRejectedJobOffer(){
        Map<String, Object> data = new HashMap<>();
        data.put("jobOfferRejected", true);
        String caseId = startCase(data);

        // Get the tasks.
        List<Long> taskIds = getTasksInCase(caseId, 1, 1);

        UserTaskInstanceDesc task = runtimeDataService.getTaskById(taskIds.get(0));
        assertEquals("Job Offer Modification", task.getName());
    }

    @Test
    public void testApplicantReviewCompletedRequestsJobOffer() {
        Map<String, Object> data = new HashMap<>();
        data.put("applicantReviewCompleted", true);
        String caseId = startCase(data);

        // Get the tasks.
        List<Long> taskIds = getTasksInCase(caseId, 1, 1);

        UserTaskInstanceDesc task = runtimeDataService.getTaskById(taskIds.get(0));
        assertEquals("Job Offer Acceptance", task.getName());
    }

}