package com.wojnarowicz.sfg.restmvc.bootstrap;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.gw.domain.PCContact;
import com.wojnarowicz.sfg.gw.domain.PCPolicy;
import com.wojnarowicz.sfg.gw.repository.PCPolicyRepository;

@Component
public class PCDataLoader implements CommandLineRunner {

    private final PCPolicyRepository pcPolicyRepository;
    
    @Autowired
    public PCDataLoader(PCPolicyRepository pcPolicyRepository) {
        this.pcPolicyRepository = pcPolicyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadPCPolicies();
        
    }

    private void loadPCPolicies() {
        PCPolicy policy1 = new PCPolicy();
        policy1.setPublicId("bc:1");
        policy1.setPolicyNumber("1001001");
        pcPolicyRepository.save(policy1);
        
        PCPolicy policy2 = new PCPolicy();
        policy2.setPublicId("bc:1");
        policy2.setPolicyNumber(LocalDateTime.now().toString());
        pcPolicyRepository.save(policy2);
        
        PCContact owner = new PCContact();
        owner.setPublicID("bc:11");
        owner.setFirstName("Witek");
        
        PCPolicy policy3 = new PCPolicy();
        policy3.setPublicId("bc:3");
        policy3.setPolicyNumber(LocalDateTime.now().toString());
        policy3.setOwner(owner);
        policy3.setInsured(owner);
        pcPolicyRepository.save(policy3);
    }
}
