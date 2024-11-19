/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.servicetypes;

import domain.ServiceType;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Nikolina
 */
public class GetServiceTypes extends AbstractGenericOperation{

    private List<ServiceType> services;
    @Override
    protected void preconditions(Object param) throws Exception {
        //no preconditions
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        services = repository.get((ServiceType)param);
    }

    public List<ServiceType> getServices() {
        return services;
    }
    
    
    
}
