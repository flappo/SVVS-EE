/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.RightDTO;
import data.DTOs.IRightDTO;
import data.models.IPerson;
import data.models.IRight;
import data.models.ISport;
import data.models.Right;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author uubu
 */
public class RightDAO extends AbstractDAO<IRight, IRightDTO> implements IRightDAO {

    private static IRightDAO instance;

    private RightDAO() throws RemoteException {
        super("data.models.Right");
    }

    public static IRightDAO getInstance() throws RemoteException {
        if (instance == null) {
            instance = new RightDAO();
        }
        return instance;
    }

    @Override
    public IRight create()  throws RemoteException{
        return new Right();
    }

    @Override
    public IRightDTO extractDTO(IRight model)  throws RemoteException{
        try {
            return new RightDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(RightDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Long getRightbyName(Session s, String name)  throws RemoteException{

        Query query = s.createQuery("FROM " + getTable() + " where name =:name");
        query.setString("name", name);
        IRight right = (IRight)query.uniqueResult();
        
        return right.getValue();


    }
}
