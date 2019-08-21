package com.myspace.hr_hiring.wih;

import java.util.HashMap;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailSimulator implements WorkItemHandler {

    Logger logger = LoggerFactory.getLogger(MailSimulator.class);

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        String from = String.valueOf(workItem.getParameter("From"));
        String subject = String.valueOf(workItem.getParameter("Subject"));
        String body = String.valueOf(workItem.getParameter("Body"));
        String to = String.valueOf(workItem.getParameter("To"));

        logger.info("******************** EMAIL SIMULATOR / *******************");
        logger.info("From:" + from);
        logger.info("To:" + to);
        logger.info("Subject: " + subject);
        logger.info("--------------------------------------------");
        logger.info(body);
        logger.info("--------------------------------------------");
        logger.info("******************** / EMAIL SIMULATOR  *******************");

        manager.completeWorkItem(workItem.getId(), new HashMap<String, Object>());
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        manager.abortWorkItem(workItem.getId());
    }

}