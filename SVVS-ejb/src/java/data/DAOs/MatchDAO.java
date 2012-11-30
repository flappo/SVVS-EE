/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.MatchDTO;
import data.DTOs.IMatchDTO;
import data.models.IMatch;
import data.models.Match;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class MatchDAO extends AbstractDAO<IMatch, IMatchDTO> implements IMatchDAO{

    private static IMatchDAO instance;
    
    private MatchDAO() throws RemoteException{
        super("data.models.Match");
    }
    
    public static IMatchDAO getInstance() throws RemoteException{
        if(instance == null){
            instance = new MatchDAO();
        }
        return instance;
    }
    
    @Override
    public IMatch create()  throws RemoteException{
        return new Match();
    }

    @Override
    public IMatchDTO extractDTO(IMatch model) throws RemoteException {
        try {
            return new MatchDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(MatchDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
