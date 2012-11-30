/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.RoleRightsDTO;
import data.DTOs.IDTO;
import data.DTOs.IRoleRightsDTO;
import data.models.IModel;
import data.models.IRoleRights;
import data.models.RoleRights;
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
public class RoleRightsDAO extends AbstractDAO<IRoleRights, IRoleRightsDTO> implements IRoleRightsDAO{

    private static IRoleRightsDAO instance;
    
    private RoleRightsDAO() throws RemoteException{
        super("data.models.RoleRights");
    }
    
    public static IRoleRightsDAO getInstance() throws RemoteException{
        if (instance == null){
            instance = new RoleRightsDAO();
        }
        return instance;
    }
    
    @Override
    public IRoleRights create() throws RemoteException {
        return new RoleRights();
    }

    @Override
    public IRoleRightsDTO extractDTO(IRoleRights model) throws RemoteException {
        try {
            return new RoleRightsDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(RoleRightsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

    @Override
    public IRoleRights getById(Session s, int id)  throws RemoteException{

        Query query = s.createQuery("FROM " + getTable() + " where roleRightsID =:id");
        query.setInteger("id", id);
        return (IRoleRights) query.uniqueResult();
    }
    
}
