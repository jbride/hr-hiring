package com.myspace.hr_hiring;

import org.jbpm.services.api.model.UserTaskInstanceDesc;
import org.jbpm.services.api.model.ProcessInstanceDesc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.query.QueryFilter;

public class EmptyCaseEndToEndTest extends AbstractCaseEndToEndTest {

    @Test
    public void testEmptyCaseRequiresDocsReview() {
        Map<String, Object> data = new HashMap<>();
        // data.put("continue", false);
        String caseId = startCase(data);

        // Get the tasks.
        List<Long> taskIds = getTasksInCase(caseId, 1, 1);

        UserTaskInstanceDesc task = runtimeDataService.getTaskById(taskIds.get(0));
        assertEquals("Talent Acquisition Documents Review", task.getName());
    }
}