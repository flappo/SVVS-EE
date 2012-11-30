/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.AdministratorDTO;
import data.DTOs.IAdministratorDTO;
import data.models.IAdministrator;
import data.models.IDepartment;
import data.models.IPerson;
import data.models.IRole;
import data.models.ISport;
import data.models.Administrator;
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
public class AdministratorDAO extends AbstractDAO<IAdministrator, IAdministratorDTO> implements IAdministratorDAO {

    private static IAdministratorDAO instance;
    
    private AdministratorDAO() throws RemoteException{
        super("data.models.Administrator");
    }
    
    public static IAdministratorDAO getInstance() throws RemoteException{
        if ( instance == null){
            instance = new AdministratorDAO();
        }
        return instance;
    }
    
    @Override
    public IAdministrator create()  throws RemoteException{
        return new Administrator();
    }

    @Override
    public IAdministratorDTO extractDTO(IAdministrator model)  throws RemoteException{
        try {
            return new AdministratorDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  null;
    }
    
     @Override
    public List<IAdministrator> getByPerson(Session s,IPerson person) throws RemoteException{
        
        Query query = s.createQuery("FROM "+getTable()+" WHERE person = :person");
        query.setParameter("person", person);
        List<IAdministrator> roles = new LinkedList<IAdministrator>();
        roles = query.list();
        
        if (roles.isEmpty()){
            return null;
        }
        
        return roles;
                
    }
 @Override
    public IAdministrator getByAll(Session s,IPerson person) throws RemoteException{
        
        Query query = s.createQuery("FROM "+getTable()+" WHERE person = :person");
        query.setParameter("person", person);
        
        return (IAdministrator) query.uniqueResult();
                
    }

}
