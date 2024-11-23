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
public class CreateHairdresser extends AbstractGenericOperation{

    private Hairdresser hairdresser;
    IValidator validator; 

    public CreateHairdresser(Hairdresser hairdresser) {
        this.hairdresser=hairdresser;
    }
    
    
    @Override
    protected void preconditions(Object param) throws Exception {
        validator = new RegularValidator();
        System.out.println(((Hairdresser)param).getJMBG());
        validator.validateHairdresser((Hairdresser)param);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        hairdresser = (Hairdresser) repository.create(hairdresser);
    }

    public Hairdresser getHairdresser() {
        return hairdresser;
    }
    
    
    
}
