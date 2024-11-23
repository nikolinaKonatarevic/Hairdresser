/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.hairdressers;

import domain.Hairdresser;
import operation.AbstractGenericOperation;
import validator.IValidator;
import validator.components.RegularValidator;

/**
 *
 * @author Nikolina
 */
public class UpdateHairdresser  extends AbstractGenericOperation{

    private Hairdresser hairdresser;
    IValidator validator;

    public UpdateHairdresser() {
    }
    
    
    @Override
    protected void preconditions(Object param) throws Exception {
           }

    @Override
    protected void executeOperation(Object param) throws Exception {
        hairdresser = (Hairdresser) repository.update((Hairdresser)param);
    }

    public Hairdresser getHairdresser() {
        return hairdresser;
    }
    
}
