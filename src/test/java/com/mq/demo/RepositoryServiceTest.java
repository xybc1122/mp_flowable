package com.mq.demo;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.zip.ZipInputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryServiceTest {
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
     * 查询历史数据
     */

}
