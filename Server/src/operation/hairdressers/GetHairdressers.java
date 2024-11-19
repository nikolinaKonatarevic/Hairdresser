/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.hairdressers;

import domain.Hairdresser;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Nikolina
 */
public class GetHairdressers extends AbstractGenericOperation{
    
    private List<Hairdresser> hairdressers;

    @Override
    protected void preconditions(Object param) throws Exception {
    // no preconditions
    }
        
    @Override
    protected void executeOperation(Object param) throws Exception {
        hairdressers = repository.get((Hairdresser)param);
        //System.out.println("GetHairdressers "+ hairdressers);
    }

    public List<Hairdresser> getHairdressers() {
        return hairdressers;
    }
    
    
    
}
