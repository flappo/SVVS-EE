/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.touramentTeam;

import business.AController;
import business.controller.JMS.MessageController;
import business.controller.person.create.States.PersonCreateAssignSportState;
import business.messages.jms.interfaces.ITournamentInviteMessage;
import data.DAOs.SportsmanDAO;
import data.DAOs.TournamentDAO;
import data.DAOs.TournamentInviteDAO;
import data.DAOs.TrainingTeamDAO;
import data.DTOs.ISportsmanDTO;
import data.DTOs.ISportsmanTrainingTeamDTO;
import data.DTOs.ITournamentDTO;
import data.DTOs.ITournamentInviteDTO;
import data.DTOs.ITrainingTeamDTO;
import data.DTOs.SportsmanDTO;
import data.DTOs.TournamentInviteDTO;
import data.DTOs.TrainingTeamDTO;
import data.hibernate.HibernateUtil;
import data.models.ISportsman;
import data.models.ITournament;
import data.models.ITournamentInvite;
import data.models.ITrainingTeam;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author uubu
 */
public class TournamentTeamController extends AController implements ITournamentTeamController {
    
    private static TournamentTeamController instance;
    
    private TournamentTeamController() throws RemoteException {
        super();
    }
    
    public static TournamentTeamController getInstance() throws RemoteException {
        if (instance == null) {
            return (instance = new TournamentTeamController());
        }
        return instance;
    }
    
    @Override
    public List<ITournamentInviteDTO> loadTournamentTeams() throws RemoteException {
        
        return TournamentInviteDAO.getInstance().getAllTeamDTOs(HibernateUtil.getCurrentSession());
    }
    
    @Override
    public List<ISportsmanDTO> loadPlayersOfTeam(ITournamentDTO tournament, ITrainingTeamDTO team) throws RemoteException {
        
        Session s = HibernateUtil.getCurrentSession();
        ITournament t = TournamentDAO.getInstance().getById(s, tournament.getId());
        ITrainingTeam tt = TrainingTeamDAO.getInstance().getById(s, team.getId());
        
        List<ISportsmanDTO> smdto = new LinkedList<ISportsmanDTO>();
        
        for (ITournamentInvite ti : TournamentInviteDAO.getInstance().getByTournamentAndTeam(s, t, tt)) {
            if (ti.getSportsman() != null) {
                smdto.add(new SportsmanDTO(ti.getSportsman()));
            }
        }
        
        return smdto;
    }
    
    @Override
    public List<ISportsmanDTO> loadNotAssignedPlayersOfTeam(ITournamentDTO tournament, ITrainingTeamDTO team) throws RemoteException {
        
        List<ISportsmanDTO> allAssignedSportsman = loadPlayersOfTeam(tournament, team);
        List<ISportsmanDTO> returnList = new LinkedList<ISportsmanDTO>();
        ITrainingTeamDTO t = new TrainingTeamDTO(TrainingTeamDAO.getInstance().getById(HibernateUtil.getCurrentSession(), team.getId()));
        for (ISportsmanTrainingTeamDTO stt : t.getSportsmen()) {
            ISportsmanDTO smdto = stt.getSportsman();
            if (!allAssignedSportsman.contains(smdto)) {
                returnList.add(smdto);
            }
        }
        return returnList;
        
    }
    
    @Override
    public void assignPlayer(ITournamentDTO tournament, ITrainingTeamDTO team, ISportsmanDTO sportsman) throws RemoteException {
        
        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.beginTransaction();
        
        ITournament tournamentM = TournamentDAO.getInstance().getById(s, tournament.getId());
        ITrainingTeam teamM = TrainingTeamDAO.getInstance().getById(s, team.getId());
        ISportsman sportsmanM = SportsmanDAO.getInstance().getById(s, sportsman.getId());
        
        
        ITournamentInvite ti = TournamentInviteDAO.getInstance().getByAll(s, tournamentM, teamM, sportsmanM);
        
        if (ti == null) {
            
            ti = TournamentInviteDAO.getInstance().create();
            
            ti.setTournament(tournamentM);
            ti.setTeam(teamM);
            ti.setSportsman(sportsmanM);
            ti.setAccepted(false);
            
            TournamentInviteDAO.getInstance().add(s, ti);
        }
        
        ITournamentInviteDTO dieter = new TournamentInviteDTO(ti);
        tx.commit();
        
        MessageController mc = null;
        try {
            mc = MessageController.getInstance();
            List<String> names = new LinkedList<String>();
            mc.createInviteMessage(dieter);
        } catch (Exception ex) {
            Logger.getLogger(PersonCreateAssignSportState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void removePlayer(ITournamentDTO tournament, ITrainingTeamDTO team, ISportsmanDTO sportsman) throws RemoteException {
        
        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.beginTransaction();
        
        ITournament tournamentM = TournamentDAO.getInstance().getById(s, tournament.getId());
        ITrainingTeam teamM = TrainingTeamDAO.getInstance().getById(s, team.getId());
        ISportsman sportsmanM = SportsmanDAO.getInstance().getById(s, sportsman.getId());
        
        
        ITournamentInvite ti = TournamentInviteDAO.getInstance().getByAll(s, tournamentM, teamM, sportsmanM);
        
        TournamentInviteDAO.getInstance().remove(s, ti);
        tx.commit();
        
    }
    
    @Override
    public void acceptInvite(ITournamentInviteMessage tim, boolean accepted) throws RemoteException {
        
        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.beginTransaction();
        
        ITournament tournamentM = TournamentDAO.getInstance().getById(s, tim.getTournament().getId());
        ITrainingTeam teamM = TrainingTeamDAO.getInstance().getById(s, tim.getTeam().getId());
        ISportsman sportsmanM = SportsmanDAO.getInstance().getById(s, tim.getSportsman().getId());
        
        ITournamentInvite ti;
        ti = TournamentInviteDAO.getInstance().getByAll(s, tournamentM, teamM, sportsmanM);
        
        if (accepted) {
            ti.setAccepted(accepted);
            TournamentInviteDAO.getInstance().add(s, ti);
        } else {
            TournamentInviteDAO.getInstance().remove(s, ti);
        }
        
        tx.commit();
        
    }
}
