/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.ManagerDTO;
import data.DTOs.IManagerDTO;
import data.models.IDepartment;
import data.models.IManager;
import data.models.IPerson;
import data.models.IRole;
import data.models.ISport;
import data.models.Manager;
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
public class ManagerDAO extends AbstractDAO<IManager, IManagerDTO> implements IManagerDAO {

    private static IManagerDAO instance;
    
    private ManagerDAO() throws RemoteException{
        super("data.models.Manager");
    }
    
    public static IManagerDAO getInstance() throws RemoteException{
        if(instance == null){
            instance = new ManagerDAO();
        }
        return instance;
    }
    
    @Override
    public IManager create()  throws RemoteException{
        return new Manager();
    }

    @Override
    public IManagerDTO extractDTO(IManager model) throws RemoteException {
        try {
            return new ManagerDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(ManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     @Override
    public List<IManager> getByPerson(Session s,IPerson person) throws RemoteException{
        
        Query query = s.createQuery("FROM "+getTable()+" WHERE person = :person");
        query.setParameter("person", person);
        List<IManager> roles = new LinkedList<IManager>();
        roles = query.list();
        
        if (roles.isEmpty()){
            return null;
        }
        
        return roles;        
    }
     
      @Override
    public IManager getByAll(Session s,IPerson person, IDepartment department) throws RemoteException{
        
        Query query = s.createQuery("FROM "+getTable()+" WHERE person = :person AND department = :department");
        query.setParameter("person", person);
        query.setParameter("department", department);
        
        return (IManager) query.uniqueResult();
                
    }
}
