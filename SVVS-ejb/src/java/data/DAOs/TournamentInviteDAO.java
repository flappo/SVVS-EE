/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.TournamentInviteDTO;
import data.DTOs.ITournamentInviteDTO;
import data.models.ISportsman;
import data.models.ISportsmanTrainingTeam;
import data.models.ITournament;
import data.models.ITournamentInvite;
import data.models.ITrainingTeam;
import data.models.TournamentInvite;
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
public class TournamentInviteDAO extends AbstractDAO<ITournamentInvite, ITournamentInviteDTO> implements ITournamentInviteDAO {

    private static ITournamentInviteDAO instance;

    private TournamentInviteDAO() throws RemoteException {
        super("data.models.TournamentInvite");
    }

    public static ITournamentInviteDAO getInstance() throws RemoteException {
        if (instance == null) {
            instance = new TournamentInviteDAO();
        }
        return instance;
    }

    @Override
    public ITournamentInvite create()  throws RemoteException{
        return new TournamentInvite();
    }

    @Override
    public ITournamentInviteDTO extractDTO(ITournamentInvite model)  throws RemoteException{
        try {
            return new TournamentInviteDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(TournamentInviteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<ITournamentInvite> getBySportsman(Session s, ISportsman sportsman)  throws RemoteException{

        Query query = s.createQuery("FROM " + getTable() + " where sportsman = :sportsman");
        query.setParameter("sportsman", sportsman);
        return query.list();
    }

    @Override
    public List<ITournamentInvite> getByTournament(Session s, ITournament tournament) throws RemoteException {

        Query query = s.createQuery("FROM " + getTable() + " where tournament = :tournament");
        query.setParameter("tournament", tournament);
        return query.list();
    }

    @Override
    public List<ITournamentInvite> getByTournamentAndTeam(Session s, ITournament tournament, ITrainingTeam team) throws RemoteException {

        Query query = s.createQuery("FROM " + getTable() + " where tournament = :tournament AND team = :team");
        query.setParameter("tournament", tournament);
        query.setParameter("team", team);
        return query.list();
    }
    
    @Override
    public List<ITournamentInvite> getTeamsOfTournament(Session s, ITournament tournament) throws RemoteException {

        Query query = s.createQuery("FROM " + getTable() + " where tournament = :tournament AND sportsman = null");
        query.setParameter("tournament", tournament);
        return query.list();
    }
    
    @Override
     public List<ITournamentInviteDTO> getTeamsOfTournamentDTOs(Session s,  ITournament tournament) throws RemoteException{
        
        List<ITournamentInviteDTO> tid = new LinkedList<ITournamentInviteDTO>();
        for(ITournamentInvite ti: getTeamsOfTournament(s,tournament)){
             try {            
                 tid.add(new TournamentInviteDTO(ti));
             } catch (RemoteException ex) {
                 Logger.getLogger(TournamentInviteDAO.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        return tid;
    }
    
    
    @Override
    public List<ITournamentInvite> getAllTeams(Session s) throws RemoteException {

        Query query = s.createQuery("FROM " + getTable() + " where sportsman = null");
        return query.list();
    }
    
    @Override
    public List<ITournamentInviteDTO> getAllTeamDTOs(Session s) throws RemoteException{
        
        List<ITournamentInviteDTO> tid = new LinkedList<ITournamentInviteDTO>();
        for(ITournamentInvite ti: getAllTeams(s)){
            try {            
                tid.add(new TournamentInviteDTO(ti));
            } catch (RemoteException ex) {
                Logger.getLogger(TournamentInviteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return tid;
    }
    
    @Override
    public ITournamentInvite getByAll(Session s, ITournament tournament, ITrainingTeam team, ISportsman sportsman) throws RemoteException {

        Query query = s.createQuery("FROM " + getTable() + " where tournament = :tournament AND team = :team AND sportsman = :sportsman");
        query.setParameter("tournament", tournament);
        query.setParameter("team", team);
        query.setParameter("sportsman", sportsman);
        return (ITournamentInvite)query.uniqueResult();
    }

}
