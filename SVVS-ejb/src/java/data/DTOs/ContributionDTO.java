/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.IContribution;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class ContributionDTO extends AbstractDTO<IContribution> implements IContributionDTO{

    public ContributionDTO() throws RemoteException {
        super();
    }

    
    private String name;
    private double value;

    public ContributionDTO(IContribution model) throws RemoteException {
        super();
        if(model == null) return;
        extract(model);
    }
 
    @Override
    public final void extract(IContribution model)throws RemoteException {
        this.id = model.getContributionID();
        this.name = model.getName();
        this.value = model.getValue().doubleValue();
    }
    
    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public void setName(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public double getValue()throws RemoteException  {
        return value;
    }

    @Override
    public void setValue(double value)throws RemoteException  {
        this.value = value;
    }

    @Override
    public String toString() {
        try {
            return getName();
        } catch (RemoteException ex) {
            Logger.getLogger(ContributionDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
