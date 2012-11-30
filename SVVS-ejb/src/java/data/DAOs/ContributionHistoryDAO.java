/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.ContributionHistoryDTO;
import data.DTOs.IContributionHistoryDTO;
import data.models.IContribution;
import data.models.IContributionHistory;
import data.models.ContributionHistory;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author uubu
 */
public class ContributionHistoryDAO extends AbstractDAO<IContributionHistory, IContributionHistoryDTO> implements IContributionHistoryDAO{

    private static IContributionHistoryDAO instance;
    
    private ContributionHistoryDAO() throws RemoteException {
        super("data.models.ContributionHistory");
    }
    
    public static IContributionHistoryDAO getInstance() throws RemoteException{
        if (instance == null){
            instance = new ContributionHistoryDAO();
        }
        return instance;
    }
    
    @Override
    public IContributionHistory create() throws RemoteException {
        return new ContributionHistory();
    }

    @Override
    public IContributionHistoryDTO extractDTO(IContributionHistory model) throws RemoteException {
        try {
            return new ContributionHistoryDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(ContributionHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}