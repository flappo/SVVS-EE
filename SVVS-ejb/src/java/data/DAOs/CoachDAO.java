/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.CoachDTO;
import data.DTOs.ICoachDTO;
import data.models.ICoach;
import data.models.IDepartment;
import data.models.IPerson;
import data.models.IRole;
import data.models.ISport;
import data.models.Coach;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author uubu
 */
public class CoachDAO extends AbstractDAO<ICoach, ICoachDTO> implements ICoachDAO {

    private static ICoachDAO instance;
    
    private CoachDAO() throws RemoteException {
        super("data.models.Coach");
    }
    
    public static ICoachDAO getInstance() throws RemoteException{
        if ( instance == null){
            instance = new CoachDAO();
        }
        return instance;
    }
    
    @Override
    public ICoach create()  throws RemoteException{
        return new Coach();
    }

    @Override
    public ICoachDTO extractDTO(ICoach model)  throws RemoteException{
        try {
            return new CoachDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(CoachDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
     @Override
    public List<ICoach> getByPerson(Session s,IPerson person) throws RemoteException{
        
        Query query = s.createQuery("FROM "+getTable()+" WHERE person = :person");
        query.setParameter("person", person);
        List<ICoach> roles = new LinkedList<ICoach>();
        roles = query.list();
        
        if (roles.isEmpty()){
            return null;
        }
        
        return roles;
                
    }
     
      @Override
    public ICoach getByAll(Session s,IPerson person, IDepartment department, ISport sport) throws RemoteException{
        
        Query query = s.createQuery("FROM "+getTable()+" WHERE person = :person AND department = :department AND sport = :sport");
        query.setParameter("person", person);
        query.setParameter("department", department);
        query.setParameter("sport", sport);
        
        return (ICoach) query.uniqueResult();
                
    }
}
