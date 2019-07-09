package com.myspace.hr_hiring;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kie.api.runtime.query.QueryContext;
import org.jbpm.casemgmt.api.model.instance.CaseMilestoneInstance;
import org.jbpm.casemgmt.api.model.instance.MilestoneStatus;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MilestoneAchievementTest extends AbstractCaseEndToEndTest {

    @Test
    public void testEmptyCaseHasAllMilestones() {
        Map<String, Object> data = new HashMap<>();
        String caseId = startCase(data);

        // Get the milestones
        Collection<CaseMilestoneInstance> milestones = caseRuntimeDataService.getCaseInstanceMilestones(caseId, false,
                new QueryContext());
        List<String> expectedMilestones = Arrays.asList("Milestone 1: Job Application Submitted",
                "Milestone 2: Applicant Review", "Milestone 3: Job Offer Acceptance");
        for (CaseMilestoneInstance milestone : milestones) {
            assertTrue("Expected milestone not found", expectedMilestones.contains(milestone.getName()));
            assertEquals("Wrong milestone status(" + milestone.getName() + ")", MilestoneStatus.Available,
                    milestone.getStatus());
            assertFalse("Should not be achieved", milestone.isAchieved());
            assertNull("Achieved date should be null", milestone.getAchievedAt());
        }
    }

    @Test
    public void testExternalApplicantHasAllMilestones() {
        Map<String, Object> data = new HashMap<>();
        data.put("internal", false);
        String caseId = startCase(data);

        // Get the milestones
        Collection<CaseMilestoneInstance> milestones = caseRuntimeDataService.getCaseInstanceMilestones(caseId, false,
                new QueryContext());
        List<String> expectedMilestones = Arrays.asList("Milestone 1: Job Application Submitted",
                "Milestone 2: Applicant Review", "Milestone 3: Job Offer Acceptance");
        for (CaseMilestoneInstance milestone : milestones) {
            assertTrue("Expected milestone not found", expectedMilestones.contains(milestone.getName()));
            assertEquals("Wrong milestone status(" + milestone.getName() + ")", MilestoneStatus.Available,
                    milestone.getStatus());
            assertFalse("Should not be achieved", milestone.isAchieved());
            assertNull("Achieved date should be null", milestone.getAchievedAt());
        }
    }

    @Test
    public void testInternalApplicantActivatesApplicantReview() {
        Map<String, Object> data = new HashMap<>();
        data.put("internal", true);
        String caseId = startCase(data);

        // Get the milestones
        Collection<CaseMilestoneInstance> milestones = caseRuntimeDataService.getCaseInstanceMilestones(caseId, false,
                new QueryContext());
        List<String> expectedMilestones = Arrays.asList("Milestone 1: Job Application Submitted",
                "Milestone 2: Applicant Review", "Milestone 3: Job Offer Acceptance");
        for (CaseMilestoneInstance milestone : milestones) {
            assertTrue("Expected milestone not found", expectedMilestones.contains(milestone.getName()));
            if ("Milestone 1: Job Application Submitted".equals(milestone.getName())) {
                assertEquals("Wrong milestone status(" + milestone.getName() + ")", MilestoneStatus.Completed,
                        milestone.getStatus());
                assertTrue("Should be achieved", milestone.isAchieved());
                assertNotNull("Achieved date should not be null", milestone.getAchievedAt());
            } else {
                assertEquals("Wrong milestone status(" + milestone.getName() + ")", MilestoneStatus.Available,
                        milestone.getStatus());
                assertFalse("Should not be achieved", milestone.isAchieved());
                assertNull("Achieved date should be null", milestone.getAchievedAt());
            }

        }
    }

    @Test
    public void testExternalApplicantApprovedDocsActivatesApplicantReview() {
        Map<String, Object> data = new HashMap<>();
        data.put("internal", false);
        data.put("documentationApproved", true);
        String caseId = startCase(data);

        // Get the milestones
        Collection<CaseMilestoneInstance> milestones = caseRuntimeDataService.getCaseInstanceMilestones(caseId, false,
                new QueryContext());
        List<String> expectedMilestones = Arrays.asList("Milestone 1: Job Application Submitted",
                "Milestone 2: Applicant Review", "Milestone 3: Job Offer Acceptance");
        for (CaseMilestoneInstance milestone : milestones) {
            assertTrue("Expected milestone not found", expectedMilestones.contains(milestone.getName()));
            if ("Milestone 1: Job Application Submitted".equals(milestone.getName())) {
                assertEquals("Wrong milestone status(" + milestone.getName() + ")", MilestoneStatus.Completed,
                        milestone.getStatus());
                assertTrue("Should be achieved", milestone.isAchieved());
                assertNotNull("Achieved date should not be null", milestone.getAchievedAt());
            } else {
                assertEquals("Wrong milestone status(" + milestone.getName() + ")", MilestoneStatus.Available,
                        milestone.getStatus());
                assertFalse("Should not be achieved", milestone.isAchieved());
                assertNull("Achieved date should be null", milestone.getAchievedAt());
            }

        }
    }

    @Test
    public void testApplicantReviewCompleted() {
        Map<String, Object> data = new HashMap<>();
        data.put("internal", true);
        data.put("applicantReviewCompleted", true);
        String caseId = startCase(data);

        // Get the milestones
        Collection<CaseMilestoneInstance> milestones = caseRuntimeDataService.getCaseInstanceMilestones(caseId, false,
                new QueryContext());
        List<String> expectedMilestones = Arrays.asList("Milestone 1: Job Application Submitted",
                "Milestone 2: Applicant Review", "Milestone 3: Job Offer Acceptance");
        for (CaseMilestoneInstance milestone : milestones) {
            assertTrue("Expected milestone not found", expectedMilestones.contains(milestone.getName()));
            if ("Milestone 1: Job Application Submitted".equals(milestone.getName())
                    || "Milestone 2: Applicant Review".equals(milestone.getName())) {
                assertEquals("Wrong milestone status(" + milestone.getName() + ")", MilestoneStatus.Completed,
                        milestone.getStatus());
                assertTrue("Should be achieved", milestone.isAchieved());
                assertNotNull("Achieved date should not be null", milestone.getAchievedAt());
            } else {
                assertEquals("Wrong milestone status(" + milestone.getName() + ")", MilestoneStatus.Available,
                        milestone.getStatus());
                assertFalse("Should not be achieved", milestone.isAchieved());
                assertNull("Achieved date should be null", milestone.getAchievedAt());
            }

        }
    }

    @Test
    public void testJobOfferAccepted() {
        Map<String, Object> data = new HashMap<>();
        data.put("internal", true);
        data.put("applicantReviewCompleted", true);
        data.put("jobOfferAccepted", true);
        String caseId = startCase(data);

        // Get the milestones
        Collection<CaseMilestoneInstance> milestones = caseRuntimeDataService.getCaseInstanceMilestones(caseId, false,
                new QueryContext());
        List<String> expectedMilestones = Arrays.asList("Milestone 1: Job Application Submitted",
                "Milestone 2: Applicant Review", "Milestone 3: Job Offer Acceptance");
        for (CaseMilestoneInstance milestone : milestones) {
            assertTrue("Expected milestone not found", expectedMilestones.contains(milestone.getName()));
            assertEquals("Wrong milestone status(" + milestone.getName() + ")", MilestoneStatus.Completed,
                    milestone.getStatus());
            assertTrue("Should not be achieved", milestone.isAchieved());
            assertNotNull("Achieved date should be null", milestone.getAchievedAt());
        }
    }
}