/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.TournamentDTO;
import data.DTOs.ITournamentDTO;
import data.models.ITournament;
import data.models.Tournament;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author uubu
 */
public class TournamentDAO extends AbstractDAO<ITournament, ITournamentDTO> implements ITournamentDAO {

    private static ITournamentDAO instance;

    private TournamentDAO() throws RemoteException {
        super("data.models.Tournament");
    }

    public static ITournamentDAO getInstance() throws RemoteException {
        if (instance == null) {
            instance = new TournamentDAO();
        }
        return instance;
    }

    @Override
    public ITournament create()  throws RemoteException{
        return new Tournament();
    }

    @Override
    public ITournamentDTO extractDTO(ITournament model)  throws RemoteException{
        try {
            return new TournamentDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(TournamentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ITournament getById(Session s, int id)  throws RemoteException{

        Query query = s.createQuery("FROM " + getTable() + " Where tournamentID =:id");
        query.setInteger("id", id);
        return (ITournament) query.uniqueResult();
    }
}
