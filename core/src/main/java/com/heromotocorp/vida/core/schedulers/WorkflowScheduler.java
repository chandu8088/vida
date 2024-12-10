package com.heromotocorp.vida.core.schedulers;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;
import com.heromotocorp.vida.core.config.impl.WorkflowSchedulerConfig;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = WorkflowSchedulerConfig.class)
public class WorkflowScheduler implements Runnable {


    private final Logger logger = LoggerFactory.getLogger(WorkflowScheduler.class);

    String schedulerName;

    int schedulerId;

    boolean enable;

    String cronExpression;

    String experienceFragmentPath;

    String modelName;

    @Reference
    Scheduler scheduler;

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Activate
    private void activate(WorkflowSchedulerConfig configuration) {
        this.schedulerId = configuration.schedulerName().hashCode();
        this.modelName = configuration.model();
        this.experienceFragmentPath = configuration.experienceFragmentPath();
        this.enable = configuration.enabled();
        logger.info("**** Workflow Scheduler ****");
        addScheduler(configuration);
    }

    @Modified
    protected void modified(WorkflowSchedulerConfig configuration) {
        // Remove the scheduler registered with old configuration
        removeScheduler(configuration);
        this.modelName = configuration.model();
        this.experienceFragmentPath = configuration.experienceFragmentPath();
        this.enable = configuration.enabled();
        // Add the scheduler registered with new configuration
        addScheduler(configuration);

    }

    @Deactivate
    protected void deactivated(WorkflowSchedulerConfig configuration) {
        logger.info("**** Removing Workflow Scheduler Successfully on deactivation ****");
        removeScheduler(configuration);
    }

    private void addScheduler(WorkflowSchedulerConfig configuration) {
        if(enable){
            ScheduleOptions scheduleOptions = scheduler.EXPR(configuration.cronExpression());
            logger.info("cron Expression for workflow scheduler is {}",cronExpression);
            scheduleOptions.name(String.valueOf(configuration.schedulerName().hashCode()));
            scheduleOptions.canRunConcurrently(false);
            scheduler.schedule(this, scheduleOptions);
        }
        logger.info("##### scheduler added ####");

    }

    private void removeScheduler(WorkflowSchedulerConfig configuration) {
        logger.info("**** Removing Workflow Scheduler Successfully **** {}", schedulerName);
        scheduler.unschedule(String.valueOf(schedulerId));

    }

    @Override
    public void run() {
        if (enable){
            if (experienceFragmentPath != null && experienceFragmentPath.contains(",")) {
                String[] experienceFragmentArray = experienceFragmentPath.split(",");
                for (String xfPath : experienceFragmentArray) {
                    startWorkflow(xfPath);
                }
            } else {
                startWorkflow(experienceFragmentPath);
            }
        }
        logger.info("scheduler Run Method executed");
    }

    private void startWorkflow(String payload) {
        try {
            ResourceResolver resolver = CommonUtils.getResourceResolver(resolverFactory,
                    Constants.READWRITEUSER);
            logger.info("workflow model name {}",modelName);
            WorkflowSession workflowSession = resolver.adaptTo(WorkflowSession.class);
            WorkflowModel workflowModel = workflowSession.getModel(modelName);
            WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", payload);
            workflowSession.startWorkflow(workflowModel, workflowData);
            logger.info("******Workflow Scheduler has been started ******{}", workflowModel.getTitle());
        } catch (WorkflowException | NullPointerException e) {
            logger.error("Error while running the workflow {0}",e);
        }
    }

}
