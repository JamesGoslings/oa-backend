package com.myPro.auth.test;

import com.myPro.auth.service.utils.PostUtil;
import com.myPro.process.controller.ProcessTemplateController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUtil {

    @Autowired
    private PostUtil postUtil;

    @Test
    public void postUtilTest(){
        System.out.println("============================================>>>>>>>>");
        postUtil.initPostCodeAll();
        System.out.println("============================================>>>>>>>>");
    }

    @Autowired
    private ProcessTemplateController templateController;
    @Test
    public void saveXmlTest(){
        String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<bpmn:definitions xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmn=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" id=\"Definitions_1\" targetNamespace=\"http://bpmn.io/schema/bpmn\">\n" +
                "  <bpmn:process id=\"myqingjia0\" name=\"我的请假\" isExecutable=\"true\">\n" +
                "    <bpmn:startEvent id=\"StartEvent_1\">\n" +
                "      <bpmn:outgoing>Flow_0gmuv1v</bpmn:outgoing>\n" +
                "    </bpmn:startEvent>\n" +
                "    <bpmn:sequenceFlow id=\"Flow_0gmuv1v\" sourceRef=\"StartEvent_1\" targetRef=\"Activity_14dutgd\" />\n" +
                "    <bpmn:userTask id=\"Activity_14dutgd\" name=\"张三审批0\" activiti:assignee=\"zhangsan0\">\n" +
                "      <bpmn:incoming>Flow_0gmuv1v</bpmn:incoming>\n" +
                "      <bpmn:outgoing>Flow_0394wj3</bpmn:outgoing>\n" +
                "    </bpmn:userTask>\n" +
                "    <bpmn:sequenceFlow id=\"Flow_0394wj3\" sourceRef=\"Activity_14dutgd\" targetRef=\"Activity_1mcq5ua\" />\n" +
                "    <bpmn:userTask id=\"Activity_1mcq5ua\" name=\"李四审批0\" activiti:assignee=\"lisi0\">\n" +
                "      <bpmn:incoming>Flow_0394wj3</bpmn:incoming>\n" +
                "      <bpmn:outgoing>Flow_0c4mhi1</bpmn:outgoing>\n" +
                "    </bpmn:userTask>\n" +
                "    <bpmn:endEvent id=\"Event_1pxsc0y\">\n" +
                "      <bpmn:incoming>Flow_0c4mhi1</bpmn:incoming>\n" +
                "    </bpmn:endEvent>\n" +
                "    <bpmn:sequenceFlow id=\"Flow_0c4mhi1\" sourceRef=\"Activity_1mcq5ua\" targetRef=\"Event_1pxsc0y\" />\n" +
                "  </bpmn:process>\n" +
                "  <bpmndi:BPMNDiagram id=\"BPMNDiagram_1\">\n" +
                "    <bpmndi:BPMNPlane id=\"BPMNPlane_1\" bpmnElement=\"myqingjia0\">\n" +
                "      <bpmndi:BPMNEdge id=\"Flow_0gmuv1v_di\" bpmnElement=\"Flow_0gmuv1v\">\n" +
                "        <di:waypoint x=\"209\" y=\"120\" />\n" +
                "        <di:waypoint x=\"260\" y=\"120\" />\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNEdge id=\"Flow_0394wj3_di\" bpmnElement=\"Flow_0394wj3\">\n" +
                "        <di:waypoint x=\"360\" y=\"120\" />\n" +
                "        <di:waypoint x=\"420\" y=\"120\" />\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNEdge id=\"Flow_0c4mhi1_di\" bpmnElement=\"Flow_0c4mhi1\">\n" +
                "        <di:waypoint x=\"520\" y=\"120\" />\n" +
                "        <di:waypoint x=\"582\" y=\"120\" />\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNShape id=\"_BPMNShape_StartEvent_2\" bpmnElement=\"StartEvent_1\">\n" +
                "        <dc:Bounds x=\"173\" y=\"102\" width=\"36\" height=\"36\" />\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNShape id=\"Activity_12oacaf_di\" bpmnElement=\"Activity_14dutgd\">\n" +
                "        <dc:Bounds x=\"260\" y=\"80\" width=\"100\" height=\"80\" />\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNShape id=\"Activity_0rsfd1q_di\" bpmnElement=\"Activity_1mcq5ua\">\n" +
                "        <dc:Bounds x=\"420\" y=\"80\" width=\"100\" height=\"80\" />\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNShape id=\"Event_1pxsc0y_di\" bpmnElement=\"Event_1pxsc0y\">\n" +
                "        <dc:Bounds x=\"582\" y=\"102\" width=\"36\" height=\"36\" />\n" +
                "      </bpmndi:BPMNShape>\n" +
                "    </bpmndi:BPMNPlane>\n" +
                "  </bpmndi:BPMNDiagram>\n" +
                "</bpmn:definitions>";
        System.out.println(templateController.saveXml(data, 4L));
    }
}
