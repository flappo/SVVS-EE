/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.ContributionDTO;
import data.DTOs.IContributionDTO;
import data.models.IContribution;
import data.models.IPerson;
import data.models.Contribution;
import java.math.BigDecimal;
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
public class ContributionDAO extends AbstractDAO<IContribution, IContributionDTO> implements IContributionDAO{

    private static IContributionDAO instance;
    
    private ContributionDAO() throws RemoteException {
        super("data.models.Contribution");
    }
    
    public static IContributionDAO getInstance() throws RemoteException{
        if (instance == null){
            instance = new ContributionDAO();
        }
        return instance;
    }
    
    @Override
    public IContribution create()  throws RemoteException{
        return new Contribution();
    }

    @Override
    public IContributionDTO extractDTO(IContribution model) throws RemoteException {
        try {
            return new ContributionDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(ContributionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<IContribution> getByPerson(Session s,IPerson model) throws RemoteException {
        
        Query query = s.createQuery("FROM " + getTable() + " WHERE person = :model");
        query.setParameter("model", model);
        return query.list();    
    }
    

    @Override
    public IContribution getById(Session s, int id) throws RemoteException {

        Query query = s.createQuery("FROM " + getTable() + " Where contributionID =:id");
        query.setInteger("id", id);
        return (IContribution) query.uniqueResult();
    }

    @Override
    public IContribution saveDTOgetModel(Session s, IContributionDTO dto) throws RemoteException {

        if (dto == null) {
            return null;
        }

        IContribution contribution = getById(s, dto.getId());

        if (contribution == null) {
            contribution = create();
        }
        contribution.setName(dto.getName());
        contribution.setValue(BigDecimal.valueOf(dto.getValue()));

        s.saveOrUpdate(contribution);

        return contribution;
    }


    @Override
    public IContributionDTO saveDTO(Session s, IContributionDTO dto)  throws RemoteException{
        try {
            return new ContributionDTO(saveDTOgetModel(s, dto));
        } catch (RemoteException ex) {
            Logger.getLogger(ContributionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
