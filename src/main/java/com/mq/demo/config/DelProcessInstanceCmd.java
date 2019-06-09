package com.mq.demo.config;

import org.flowable.common.engine.impl.AbstractEngineConfiguration;
import org.flowable.common.engine.impl.interceptor.Command;
import org.flowable.common.engine.impl.interceptor.CommandContext;
import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;

/**
 * 自定义级联删除
 */
public class DelProcessInstanceCmd implements Command<Void> {
    private String processInstanceId = null;
    //备注
    private String deleteReason = null;

    public DelProcessInstanceCmd(String processInstanceId, String deleteReason) {
        this.processInstanceId = processInstanceId;
        this.deleteReason = deleteReason;
    }

    @Override
    public Void execute(CommandContext commandContext) {
        AbstractEngineConfiguration currentEngineConfiguration = commandContext.getCurrentEngineConfiguration();
        if (currentEngineConfiguration != null) {
            ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl) currentEngineConfiguration;
            processEngineConfiguration.getExecutionEntityManager().deleteProcessInstance(processInstanceId, deleteReason, true);
        }
        return null;
    }
}
