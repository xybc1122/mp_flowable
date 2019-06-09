package com.mq.demo;

import com.mq.demo.config.DelProcessInstanceCmd;
import org.apache.commons.io.FileUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlowableServiceTest {
    /**
     * 流程部署服务类
     */
    @Autowired
    private RepositoryService repositoryService;
    /**
     * 流程运行实例服务类
     */
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private IdentityService identityService;
    @Autowired
    private ManagementService managementService;

    /**
     * addClasspathResource 部署流程
     */
    @Test
    public void addClasspathResource() {
        String resource = "processes/proInspectionWareProcess.bpmn20.xml";
        DeploymentBuilder deploymentBuilder =
                repositoryService.createDeployment().name("test1").category("测试分类1").key("test").
                        addClasspathResource(resource);
        Deployment deploy = deploymentBuilder.deploy();
        System.out.println(deploy);
    }

    /**
     * 通过key来设置启动流程
     */
    @Test
    public void startProcessInstanceByKey() {
        //设置启动人
        identityService.setAuthenticatedUserId("ceh");
        String pKey = "purchaseOrder";
        Map<String, Object> sMap = new HashMap<>();
        sMap.put("depGroup", "供应中心");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(pKey, sMap);
        System.out.println(processInstance.getId());
        System.out.println(processInstance.getName());
    }

    /**
     * 查询指定流程 个人任务
     */
    @Test
    public void taskAssignee() {
        String pKey = "purchaseOrder";
        String assignee = "张三";
        List<Task> taskList = taskService.createTaskQuery().
                processDefinitionKey(pKey).taskAssignee(assignee).list();
        for (Task t : taskList) {
            System.out.println(t);
        }
    }

    /**
     * 查询流程当前的状态
     */
    @Test
    public void queryProcessInstanceState() {
        String pId = "ceh-da8e9a02-1245-411b-9738-1c807726d7c1";
        ProcessInstance p = runtimeService.createProcessInstanceQuery().
                processInstanceId(pId).singleResult();
        System.out.println(p);
    }

    /**
     * 查询正在执行的实例 也可以获取当前实例节点
     */
    @Test
    public void queryExecution() {
        List<Execution> executions = runtimeService.createExecutionQuery().list();
        for (Execution e : executions) {
            if (e.getParentId() != null) {
                System.out.println(e.getId());
                System.out.println(e.getActivityId());
                System.out.println(e.getName());
            }
        }
    }

    /**
     * 查询历史流程实例
     */
    @Test
    public void queryHistory() {
        String pId = "ceh-9d887afb-3ea1-42cc-ad6c-bec13b184daf";
        HistoricProcessInstance hpi =
                historyService.createHistoricProcessInstanceQuery().
                        processInstanceId(pId).singleResult();
        System.out.println("流程定义id--" + hpi.getProcessDefinitionId());
        System.out.println("流程实例id--" + hpi.getId());
        System.out.println("开始时间--" + hpi.getStartTime());
        System.out.println("开始节点--" + hpi.getStartActivityId());
        System.out.println("结束节点--" + hpi.getEndActivityId());
        System.out.println("结束时间--" + hpi.getEndTime());
        if (hpi.getEndTime() == null) {
            System.out.println("当前实例正在运行");
            return;
        }
        System.out.println("当前实例已经结束");
    }


    /**
     * 查询历史活动信息
     */
    @Test
    public void createHistoricActivityInstanceQuery() {
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().
                list();
        for (HistoricActivityInstance h : list) {
            System.out.println("流程定义--" + h.getProcessDefinitionId());
            System.out.println(h.getActivityId());

        }

    }

    public void generateHighLightedActivitiesDiagram(String processDefinitionId, String activityId) throws IOException {
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        String imageType = "PNG";
        List<String> highLightedActivities = new ArrayList<String>();
        //highLightedActivities.add("sid-03FFE656-10E2-4E8A-A92B-1EA78ED614B4");
        highLightedActivities.add(activityId);
        // highLightedActivities.add("sid-30100D78-929C-45A7-9C06-1589C50E7E19");
        List<String> highLightedFlows = new ArrayList<String>();
        //highLightedFlows.add("sid-4152E673-0E8F-437B-8DCB-D35A664F6201");
        String activityFontName = "宋体";
        String labelFontName = "宋体";
        String annotationFontName = "宋体";
        ClassLoader customClassLoader = null;
        double scaleFactor = 1.0D;
        boolean drawSequenceFlowNameWithNoLabelDI = true;
        ProcessDiagramGenerator processDiagramGenerator = new DefaultProcessDiagramGenerator();
        InputStream inputStream = processDiagramGenerator.generateDiagram(bpmnModel, imageType, highLightedActivities, highLightedFlows
                , activityFontName, labelFontName, annotationFontName, null, scaleFactor, true);

        FileUtils.copyInputStreamToFile(inputStream, new File("E:/" + "1.png"));
    }

    /**
     * 查询任务节点历史信息 可配合高亮图片使用
     * act_ru_task
     */
    @Test
    public void createHistoricTaskInstanceQuery() throws IOException {
        List<HistoricTaskInstance> hs = historyService.createHistoricTaskInstanceQuery().
                listPage(1, 1);
        for (HistoricTaskInstance h : hs) {
            System.out.println("流程定义--" + h.getProcessDefinitionId());
            System.out.println(h.getAssignee());
            System.out.println(h.getId());
            generateHighLightedActivitiesDiagram(h.getProcessDefinitionId(), h.getTaskDefinitionKey());
        }
    }

    /**
     * 删除流程实例ID
     */
    @Test
    public void deleteProcessInstance() {
        String processInstanceId = "ceh-414ea04d-8f6c-481d-86cd-9fb9b74eda5b";
        //备注
        String deleteReason = "测试删除";
        managementService.executeCommand(new DelProcessInstanceCmd(processInstanceId,deleteReason));
    }

}
