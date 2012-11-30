/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.RoleDTO;
import data.DTOs.IRoleDTO;
import data.models.IDepartment;
import data.models.IPerson;
import data.models.IRight;
import data.models.IRole;
import data.models.ISport;
import data.models.Role;
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
public class RoleDAO extends AbstractDAO<IRole, IRoleDTO> implements IRoleDAO{
    
    private static IRoleDAO instance;
    
    private RoleDAO() throws RemoteException{
        super("data.models.Role");
    }
    
    public static IRoleDAO getInstance() throws RemoteException{
        if (instance == null){
            instance = new RoleDAO();
        }
        return instance;
    }

    @Override
    public IRole create() throws RemoteException {
        return new Role();
    }

    @Override
    public IRoleDTO extractDTO(IRole model)  throws RemoteException{
        try {
            return new RoleDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<IRole> getBySportAndPerson(Session s,ISport sport, IPerson person) throws RemoteException{
        
        Query query = s.createQuery("FROM "+getTable()+" WHERE person = :person AND sport =:sport");
        query.setParameter("person", person);
        query.setParameter("sport", sport);
        List<IRole> roles = new LinkedList<IRole>();
        roles = query.list();
        
        if (roles.isEmpty()){
            return null;
        }
        
        return roles;
                
    }
    
    @Override
    public List<IRole> getBySport(Session s,ISport sport) throws RemoteException{
        
        Query query = s.createQuery("FROM "+getTable()+" WHERE  sport =:sport");
        query.setParameter("sport", sport);
        List<IRole> roles = new LinkedList<IRole>();
        roles = query.list();
        
        if (roles.isEmpty()){
            return null;
        }
        
        return roles;
                
    }
    @Override
    public List<IRole> getByPerson(Session s,IPerson person) throws RemoteException{
        
        Query query = s.createQuery("FROM "+getTable()+" WHERE person = :person");
        query.setParameter("person", person);
        List<IRole> roles = new LinkedList<IRole>();
        roles = query.list();
        
        if (roles.isEmpty()){
            return null;
        }
        
        return roles;
                
    }
    
     @Override
    public IRole getByAll(Session s,IPerson person, IDepartment department, ISport sport) throws RemoteException{
        
        Query query = s.createQuery("FROM "+getTable()+" WHERE person = :person AND department = :department AND sport = :sport");
        query.setParameter("person", person);
        query.setParameter("department", department);
        query.setParameter("sport", sport);
        
        return (IRole) query.uniqueResult();
                
    }
}
