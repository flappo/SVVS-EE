/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.SportsmanTrainingTeamDTO;
import data.DTOs.ISportsmanDTO;
import data.DTOs.ISportsmanTrainingTeamDTO;
import data.DTOs.ITrainingTeamDTO;
import data.models.IPerson;
import data.models.ISportsman;
import data.models.ISportsmanTrainingTeam;
import data.models.ITrainingTeam;
import data.models.SportsmanTrainingTeam;
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
public class SportsmanTrainingTeamDAO extends AbstractDAO<ISportsmanTrainingTeam, ISportsmanTrainingTeamDTO> implements ISportsmanTrainingTeamDAO{

    private static ISportsmanTrainingTeamDAO instance;
    
    private SportsmanTrainingTeamDAO() throws RemoteException{
        super("data.models.SportsmanTrainingTeam");
    }
    
    public static ISportsmanTrainingTeamDAO getInstance() throws RemoteException{
        if(instance == null){
            instance = new SportsmanTrainingTeamDAO();
        }
        return instance;
    }
    
    @Override
    public ISportsmanTrainingTeam create()  throws RemoteException{
        return new SportsmanTrainingTeam();
    }

    @Override
    public ISportsmanTrainingTeamDTO extractDTO(ISportsmanTrainingTeam model)  throws RemoteException{
        try {
            return new SportsmanTrainingTeamDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(SportsmanTrainingTeamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public ISportsmanTrainingTeam getById(Session s, int id) throws RemoteException{
        
        Query query = s.createQuery("FROM " + getTable() + " where sportsmanTrainingTeamID =:id");
        query.setInteger("id", id);
        return (ISportsmanTrainingTeam)query.uniqueResult();
    }
    
    @Override
    public List<ISportsmanTrainingTeam> getBySportsman(Session s, ISportsman sportsman) throws RemoteException{
        
        Query query = s.createQuery("FROM " + getTable() + " where sportsman = :sportsman");
        query.setParameter("sportsman", sportsman);
        return query.list();
    }
    

    @Override
    public ISportsmanTrainingTeam getBySportsmanAndTeam(Session s, ISportsman sportsman, ITrainingTeam team) throws RemoteException{
        
        Query query = s.createQuery("FROM " + getTable() + " where sportsman = :sportsman AND team = :team");
        query.setParameter("sportsman", sportsman);
        query.setParameter("team", team);
        return (ISportsmanTrainingTeam)query.uniqueResult();
    }
    
    @Override
    public ISportsmanTrainingTeam getBySportsmanAndTeamDTO(Session s, ISportsmanDTO sportsman, ITrainingTeamDTO team) throws RemoteException{
        
        ISportsman sm = null;
        try {
            sm = SportsmanDAO.getInstance().getById(s, sportsman.getId());
        } catch (RemoteException ex) {
            Logger.getLogger(SportsmanTrainingTeamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ITrainingTeam tt = null;
        try {
            tt = TrainingTeamDAO.getInstance().getById(s, team.getId());
        } catch (RemoteException ex) {
            Logger.getLogger(SportsmanTrainingTeamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return getBySportsmanAndTeam(s, sm, tt);
    }
    
}
