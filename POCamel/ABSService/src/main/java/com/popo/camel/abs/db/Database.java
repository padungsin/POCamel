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
package com.popo.camel.abs.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.popo.camel.abs.model.DuplicatePolicyExcetion;
import com.popo.camel.abs.model.Person;
import com.popo.camel.abs.model.Person.PersonType;
import com.popo.camel.abs.model.Policy;

@Component
public class Database {

	@Autowired
	PersonRepository personRepository;
	@Autowired
	PolicyRepository policyRepository;
	@Autowired
	AssistanceCaseRepository assistanceCaseRepository;

	public Policy createPolicy(Policy policy) throws DuplicatePolicyExcetion{

		
		try {
			policyRepository.save(policy);

			for (Person person : policy.getParties()) {
				person.setPolicy(policy);
				personRepository.save(person);
			}
			
			return policy;
		} catch (DataIntegrityViolationException dive) {
			throw new DuplicatePolicyExcetion("Duplicate policy: " + policy.getAbsPolicyNumber());
		}

	}

}
