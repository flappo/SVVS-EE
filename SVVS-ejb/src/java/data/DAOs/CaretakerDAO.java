/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.CaretakerDTO;
import data.DTOs.ICaretakerDTO;
import data.models.ICaretaker;
import data.models.IDepartment;
import data.models.IPerson;
import data.models.IRole;
import data.models.ISport;
import data.models.Caretaker;
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
public class CaretakerDAO extends AbstractDAO<ICaretaker, ICaretakerDTO> implements ICaretakerDAO{

    private static ICaretakerDAO instance;
    
    private CaretakerDAO() throws RemoteException {
        super("data.models.Caretaker");
    }
    
    public static ICaretakerDAO getInstance() throws RemoteException{
        if ( instance == null){
            instance = new CaretakerDAO();
        }
        return instance;
    }
    
    @Override
    public ICaretaker create()  throws RemoteException{
        return new Caretaker();
    }

    @Override
    public ICaretakerDTO extractDTO(ICaretaker model)  throws RemoteException{
        try {
            return new CaretakerDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(CaretakerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     @Override
    public List<ICaretaker> getByPerson(Session s,IPerson person) throws RemoteException{
        
        Query query = s.createQuery("FROM "+getTable()+" WHERE person = :person");
        query.setParameter("person", person);
        List<ICaretaker> roles = new LinkedList<ICaretaker>();
        roles = query.list();
        
        if (roles.isEmpty()){
            return null;
        }
        
        return roles;
                
    }
     
      @Override
    public ICaretaker getByAll(Session s,IPerson person) throws RemoteException{
        
        Query query = s.createQuery("FROM "+getTable()+" WHERE person = :person");
        query.setParameter("person", person);
        List<ICaretaker> results = query.list();
        
        if(results == null || results.size()<1){
            return null;
        }
        
        return results.get(0);
                
    }
}
