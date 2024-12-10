package com.heromotocorp.vida.core.config.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;


@ObjectClassDefinition(
        name = "Workflow Scheduler Config",
        description = "This is Workflow Scheduler Configuration")
public @interface WorkflowSchedulerConfig {

        /**
         * Scheduler Name.
         *
         * @return the string
         */
        @AttributeDefinition(name = "Scheduler Name", description = "Name for Workflow Scheduler", type = AttributeType.STRING)
        public String schedulerName();

        /**
         * Scheduler enable.
         *
         * @return the boolean
         */
        @AttributeDefinition(name = "Scheduler enable", description = "enable Workflow Scheduler", type = AttributeType.BOOLEAN)
        public boolean enabled();

        /**
         * Scheduler Cron Expression.
         *
         * @return the string
         */
        @AttributeDefinition(name = "Scheduler Cron Expression", description = "Cron Expression for Workflow Scheduler", type = AttributeType.STRING)
        public String cronExpression();

        /**
         * Experience Fragment Path.
         *
         * @return the string
         */
        @AttributeDefinition(name = "Experience Fragment Path", description = "Experience Fragment Path", type = AttributeType.STRING)
        public String experienceFragmentPath();

        /**
         * Scheduler Workflow Model.
         *
         * @return the string
         */
        @AttributeDefinition(name = "Scheduler Workflow Model", description = "Scheduler Workflow Model", type = AttributeType.STRING)
        public String model();


}
