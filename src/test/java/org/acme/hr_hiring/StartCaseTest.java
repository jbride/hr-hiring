package org.acme.hr_hiring;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.process.ProcessInstance;

public class StartCaseTest extends JbpmJUnitBaseTestCase {

    private RuntimeEngine runtime;
    private KieSession kieSession;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        createRuntimeManager("org/acme/hr_hiring/hiring-case-definition.bpmn");
        runtime = getRuntimeEngine();
        kieSession = runtime.getKieSession();
    }

    public StartCaseTest(){
        // Setup data source and session persistence
        super(true, true);
    }

    @Test
    public void TestEmptyJobApplicationRejected() {
        ProcessInstance _pi = kieSession.startProcess("hr-hiring.hiring-case-definition");
        assertNodeActive(_pi.getId(), kieSession, "Additional Notes on Rejection");
        kieSession.abortProcessInstance(_pi.getId());
    }

    // TODO: Create unit testing for other start methods and their results.
}