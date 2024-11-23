/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation;

import repository.DbRepository;
import repository.Repository;
import repository.db.impl.RepositoryDBGeneric;

/**
 *
 * @author Nikolina
 */
public abstract class AbstractGenericOperation {
    
    protected final Repository repository;

    public AbstractGenericOperation() {
        this.repository= new RepositoryDBGeneric();
    }
    
    public final void execute(Object param) throws Exception {
        try{
        preconditions(param);
        startTransaction();
        executeOperation(param);
            System.out.println("Execute gotov ");
        commitTransaction();
        } catch (Exception e){
            System.out.println("Uslo je");
            rollbackTransaction();
            System.out.println("Izaslo je");
            throw e;
        } finally {
            disconnect();
        }
    }

    protected abstract void preconditions(Object param) throws Exception;

    private void startTransaction() throws Exception{
        ((DbRepository)repository).connect();
    }

    protected abstract void executeOperation(Object param) throws Exception;

    private void commitTransaction() throws Exception{
        ((DbRepository)repository).commit();
    }

    private void rollbackTransaction() throws Exception{
        ((DbRepository) repository).rollback();
    }

    private void disconnect() throws Exception{
        ((DbRepository)repository).disconnect();
    }

  
    
    
    
    
    
}
