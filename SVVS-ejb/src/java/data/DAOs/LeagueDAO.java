/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.LeagueDTO;
import data.DTOs.ILeagueDTO;
import data.models.ILeague;
import data.models.League;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author uubu
 */
public class LeagueDAO extends AbstractDAO<ILeague, ILeagueDTO> implements ILeagueDAO {

    private static ILeagueDAO instance;
    
    private LeagueDAO() throws RemoteException{
        super("data.models.League");
    }
    
    public static ILeagueDAO getInstance() throws RemoteException{
        if (instance == null){
            instance = new LeagueDAO();
        }
        return instance;
    }
    
    @Override
    public ILeague create()  throws RemoteException{
        return new League();
    }

    @Override
    public ILeagueDTO extractDTO(ILeague model)  throws RemoteException{
        try {
            return new LeagueDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(LeagueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     @Override
    public ILeague getByName(Session s, String name)  throws RemoteException{

        Query query = s.createQuery("FROM " + getTable() + " WHERE name = :name");
        query.setString("name", name);
        return (ILeague) query.uniqueResult();
    }
    
}
