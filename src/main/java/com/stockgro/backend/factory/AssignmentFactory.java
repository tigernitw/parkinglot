package com.stockgro.backend.factory;

import com.stockgro.backend.model.enums.AssignmentType;
import com.stockgro.backend.service.ParkingAssignmentService;
import com.stockgro.backend.service.impl.RoundRobinParkingAssignmentService;
import com.stockgro.backend.service.impl.SequenceParkingAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class AssignmentFactory {

    @Autowired private SequenceParkingAssignmentService sequenceParkingAssignmentService;
    @Autowired private RoundRobinParkingAssignmentService roundRobinParkingAssignmentService;

    private Map<AssignmentType, ParkingAssignmentService> serviceMap;

    @PostConstruct
    public void init() {
        serviceMap = new HashMap<>();
        serviceMap.put(AssignmentType.SEQUENCE, sequenceParkingAssignmentService);
        serviceMap.put(AssignmentType.ROUND_ROBIN, roundRobinParkingAssignmentService);
    }

    public ParkingAssignmentService getService(AssignmentType serviceType) {
        ParkingAssignmentService service = serviceMap.get(serviceType);
        if (service == null) {
            throw new IllegalArgumentException("No such service implementation: " + serviceType);
        }
        return service;
    }

}
