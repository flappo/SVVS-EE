/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.IContributionHistory;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class ContributionHistoryDTO  extends AbstractDTO<IContributionHistory> implements IContributionHistoryDTO {

    private IContributionDTO contribution;
    private int year;
    private int month;
    private String status;

    public ContributionHistoryDTO() throws RemoteException {
        super();
    }
    
     public ContributionHistoryDTO(IContributionHistory model) throws RemoteException {
         super();
        if (model == null) return;
        extract(model);
    }
    
    @Override
    public void extract(IContributionHistory model)throws RemoteException  {
        this.id = model.getContributionHistoryID();
        this.month = model.getMonth();
        this.year = model.getYear();
        try {
            this.contribution = new ContributionDTO(model.getContribution());
        } catch (RemoteException ex) {
            Logger.getLogger(ContributionHistoryDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }

    @Override
    public IContributionDTO getContribution() throws RemoteException {
        return contribution;
    }

    @Override
    public void setContribution(IContributionDTO contribution) throws RemoteException {
        this.contribution = contribution;
    }

    @Override
    public int getYear()throws RemoteException  {
        return year;
    }

    @Override
    public void setYear(int year) throws RemoteException {
        this.year = year;
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public void setMonth(int month)throws RemoteException  {
        this.month = month;
    }

    @Override
    public String getStatus()throws RemoteException  {
        return status;
    }

    @Override
    public void setStatus(String status) throws RemoteException {
        this.status = status;
    }
    
    
}
