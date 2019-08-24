package com.myspace.hr_hiring.wih;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailSimulator implements WorkItemHandler {

    Logger logger = LoggerFactory.getLogger(MailSimulator.class);

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        String from = workItem.getParameter("From").toString();
        String to = workItem.getParameter("To").toString();
        String subject = workItem.getParameter("Subject").toString();
        String body = workItem.getParameter("Body").toString();

        logger.info("******************** EMAIL SIMULATOR / *******************");
        logger.info("From:" + from);
        logger.info("To:" + to);
        logger.info("Subject: " + subject);
        logger.info("--------------------------------------------");
        logger.info(body);
        logger.info("--------------------------------------------");

        logger.info("******************** / EMAIL SIMULATOR  *******************");

        manager.completeWorkItem(workItem.getId(), null);
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        manager.abortWorkItem(workItem.getId());
    }

}