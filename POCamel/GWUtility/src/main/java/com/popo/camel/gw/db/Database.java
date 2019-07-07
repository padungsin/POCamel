/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.popo.camel.gw.db;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.popo.camel.gw.callhistory.model.CallHistory;
import com.popo.camel.gw.callhistory.model.GWReference;
import com.popo.camel.gw.callhistory.model.RouteConfig;
import com.popo.camel.gw.repository.CallHistoryRepository;
import com.popo.camel.gw.repository.GWReferenceRepository;
import com.popo.camel.gw.repository.RouteConfigRepository;
@ComponentScan("com.popo.camel.gw.callhistory.db")
@Component
public class Database {

	@Autowired
	GWReferenceRepository gwReferenceRepository;
	
    @Autowired
    CallHistoryRepository callHistoryRepository;	
    
    @Autowired
    RouteConfigRepository routeConfigRepository;	

	public String generateSequence() throws ParseException {
		DecimalFormat df = new DecimalFormat("0000000000");

		List<GWReference> gwReferences = gwReferenceRepository.findAll();
		long ref = 0;
		GWReference gwReference = null;
		if (gwReferences.size() == 0) {
			gwReference = new GWReference();
			ref = 1;

		} else {
			gwReference = gwReferences.get(0);
			ref = df.parse(gwReference.getRef()).longValue();
			ref +=1;
		}
		gwReference.setRef(df.format(ref));
		gwReferenceRepository.save(gwReference);
		return gwReference.getRef();

	}

    public CallHistory saveCallHistory(CallHistory callHistory){
    	return callHistoryRepository.save(callHistory);
    	
    }
    
    public CallHistory getCallHistory(String ref, int seq){
    	return callHistoryRepository.findByRefAndSeq(ref, seq);
    	
    }
    
    public RouteConfig getRoute(String appId, String countryCode, String serviceType){
    	return routeConfigRepository.findByAppIdAndCountryCodeAndServiceType(appId, countryCode, serviceType);
    	
    }

}
