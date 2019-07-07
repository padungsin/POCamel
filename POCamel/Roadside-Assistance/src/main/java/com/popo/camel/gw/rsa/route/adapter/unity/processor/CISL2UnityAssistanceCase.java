package com.popo.camel.gw.rsa.route.adapter.unity.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.popo.camel.gw.rsa.model.AssistanceCase;
import com.popo.camel.gw.rsa.model.AssistanceCase.CaseType;
import com.popo.camel.gw.rsa.route.adapter.unity.UnityCreateAssistanceCase;
import com.popo.camel.gw.unity.model.UnityFile;
import com.popo.camel.gw.unity.model.UnityFile.EventType;

@Component
public class CISL2UnityAssistanceCase implements Processor {


	@Override
	public void process(Exchange exchange) throws Exception {
		
		Message in = exchange.getIn();
		
		AssistanceCase assistanceCase = in.getBody(AssistanceCase.class);
		UnityFile unityFile = new UnityFile();
		unityFile.setFileId(assistanceCase.getCaseId());
		unityFile.setPolicyId(assistanceCase.getPolicyNumber());
		
		if(assistanceCase.getCaseType().equals(CaseType.XI1)){
			unityFile.setEventType(EventType.accident);
		}else if(assistanceCase.getCaseType().equals(CaseType.XH1)){
			unityFile.setEventType(EventType.breakdown);
		}
		unityFile.setLat(assistanceCase.getLat());
		unityFile.setLng(assistanceCase.getLng());
		
		in.setBody(unityFile);
		
	}

}
