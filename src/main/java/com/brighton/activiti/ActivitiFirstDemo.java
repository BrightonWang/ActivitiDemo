package com.brighton.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

/**
 * <p>
 * </p>
 *
 * @author Brigh
 * @version ActivitiFirstDemo,  16:16 2019-06-14 Brigh Exp $
 */
public class ActivitiFirstDemo {
    public static void main(String[] args) {
        //创建ProcessEnginConfiguration
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //通过ProcessEngineConfiguration创建ProcessEngine,此时会创建数据库
//        ProcessEngine processEngine = configuration.buildProcessEngine();
        //使用classpath下的activiti.cfg.xml中的配置创建processEngine(默认ProcessEngine)
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //使用processEngine创建RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //部署对象
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday.bpmn")
                .addClasspathResource("diagram/holiday.png")
                .name("请假申请流程")
                .deploy();
        /**
            创建25张表格
         * ACT_RE_*: 'RE'表示 repository。 这个前缀的表包含了流程定义和流程静态资源 （图片，
         * 规则，等等）。
         * ACT_RU_*: 'RU'表示 runtime。 这些运行时的表，包含流程实例，任务，变量，异步任务，
         * 等运行中的数据。 Activiti 只在流程实例执行过程中保存这些数据， 在流程结束时就会删
         * 除这些记录。 这样运行时表可以一直很小速度很快。
         * ACT_HI_*: 'HI'表示 history。 这些表包含历史数据，比如历史流程实例， 变量，任务等
         * 等。
         * ACT_GE_*: GE 表示 general。 通用数据， 用于不同场景下。
         */
        System.out.println("流程引擎: --"+processEngine);
        System.out.println("流程部署id: --"+deployment.getId());
        System.out.println("流程部署名称: --"+deployment.getName());
        

    }
}
