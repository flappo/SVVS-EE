/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.TeamDTO;
import data.DTOs.ITeamDTO;
import data.models.ILeague;
import data.models.ISport;
import data.models.ITeam;
import data.models.Team;
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
public class TeamDAO extends AbstractDAO<ITeam, ITeamDTO> implements ITeamDAO {

    private static ITeamDAO instance;

    private TeamDAO() throws RemoteException {
        super("data.models.Team");
    }

    public static ITeamDAO getInstance() throws RemoteException {
        if (instance == null) {
            instance = new TeamDAO();
        }
        return instance;
    }

    @Override
    public ITeam create()  throws RemoteException{
        return new Team();
    }

    @Override
    public ITeamDTO extractDTO(ITeam model)  throws RemoteException{
        try {
            return new TeamDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(TeamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<ITeam> getBySport(Session s, ISport model) throws RemoteException {

        Query query = s.createQuery("FROM " + getTable() + " WHERE sport = :model");
        query.setParameter("model", model);
        return query.list();
    }
    
    @Override
    public List<ITeam> getByLeague(Session s, ILeague model) throws RemoteException {
        Query query = s.createQuery("FROM " + getTable() + " WHERE league = :model");
        query.setParameter("model", model);
        return query.list();
    }

    @Override
    public List<ITeam> getBySportAndLeague(Session s, ISport model, ILeague league) throws RemoteException {
        if (model == null && league == null) {
            return null;
        } else if (model == null && league != null) {
            return getByLeague(s, league);
        } else if (league == null && model != null) {
            return getBySport(s, model);
        } else if (league != null && model != null) {
            Query query = s.createQuery("FROM " + getTable() + " WHERE sport = :model AND league = :league");
            query.setParameter("model", model);
            query.setParameter("league", league);
            return query.list();
        }
        return null;
    }
}
