package com.brighton.activitiTest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author Brigh
 * @version ActivitiInstanceAndTaskTest, 10:03 2019-06-17 Brigh Exp $
 */
public class ActivitiInstanceAndTaskTest {
    //快速创建流程引擎ProcessEngine
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    //开始一个流程实例
    @Test
    public void startProcessInstance(){
        //获取RunTimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //根据流程定义Key启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("test");
        runtimeService.updateBusinessKey(processInstance.getProcessInstanceId(),"19999");
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByk
        System.out.println("流程定义id： "+processInstance.getProcessDefinitionId());
        System.out.println("流程实例id： "+processInstance.getId());
        System.out.println("当前活动id： "+processInstance.getActivityId());
    }

    //查询当前个人待执行的任务
    @Test
    public void findPersonalTaskList(){
        //任务负责人
        String assignee = "zhangsan";
        //创建TaskService
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("test")
                .taskAssignee(assignee) //只查询该任务负责人的任务
                .list();
        for (Task task:list){
            System.out.println("流程实例id: "+task.getProcessInstanceId());
            System.out.println("任务id: "+task.getId());
            System.out.println("任务负责人id: "+task.getAssignee());
            System.out.println("任务名称id: "+task.getName());
        }
    }

    //完成任务
    @Test
    public void completTask(){
        //任务id
        String taskId = "80005";
        //创建TaskService
        TaskService taskService = processEngine.getTaskService();
        //完成任务
        taskService.complete(taskId);
        System.out.println("完成任务id="+taskId);
    }
}
