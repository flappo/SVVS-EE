/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.team.teamTOplayer;

import business.controller.JMS.MessageController;
import business.AController;
import business.controller.team.TeamController;
import data.DAOs.SportsmanDAO;
import data.DAOs.SportsmanTrainingTeamDAO;
import data.DAOs.TrainingTeamDAO;
import data.DTOs.SportsmanDTO;
import data.DTOs.SportsmanTrainingTeamDTO;
import data.hibernate.HibernateUtil;
import data.DAOs.ITrainingTeamDAO;
import data.DTOs.ISportsmanDTO;
import data.DTOs.ISportsmanTrainingTeamDTO;
import data.DTOs.ITrainingTeamDTO;
import data.models.ICoach;
import data.models.ISportsman;
import data.models.ISportsmanTrainingTeam;
import data.models.ITrainingTeam;
import data.models.SportsmanTrainingTeam;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author phil
 */
public class TeamToPlayer extends AController implements ITeamToPlayer {

    public TeamToPlayer() throws RemoteException {
        super();
    }

    @Override
    public LinkedList<ITrainingTeamDTO> loadTrainingTeams() throws RemoteException {
        return TeamController.getInstance().loadTrainingTeams();
    }

    @Override
    public LinkedList<ITrainingTeamDTO> loadTrainingTeams(String sportname) throws RemoteException {
        return TeamController.getInstance().loadTrainingTeams(sportname);
    }
    
    @Override
    public LinkedList<ITrainingTeamDTO> loadTrainingTeamsOfSportsman(ISportsmanDTO sportsman) throws RemoteException{
    LinkedList<ITrainingTeamDTO> sportlerteams = null;
    
        for (ITrainingTeamDTO iTrainingTeamDTO : loadTrainingTeams()) {
            for(ISportsmanTrainingTeamDTO ispttdto: iTrainingTeamDTO.getSportsmen()){
                if (ispttdto.getSportsman().getId() == sportsman.getId()) {
                    sportlerteams.add(iTrainingTeamDTO);
                }
            }
        }
    return sportlerteams;
    }

    @Override
    public LinkedList<ISportsmanDTO> loadSportsman(String sportname, List<ISportsmanDTO> ignoreList) throws RemoteException {

        LinkedList<ISportsmanDTO> sportsmen = new LinkedList<ISportsmanDTO>();

        for (ISportsman iS : SportsmanDAO.getInstance().getAll(HibernateUtil.getCurrentSession())) {
            if (iS.getSport().getName().equals(sportname)) {
                sportsmen.add(new SportsmanDTO(iS));
            }
        }

        for (ISportsmanDTO dto : ignoreList) {
            sportsmen.remove(dto);
        }
        //_context.setState(new PlayerToTeamState(_context));
        return sportsmen;
    }

    @Override
    public ISportsmanTrainingTeamDTO AddTeamToPlayer(ITrainingTeamDTO TrainingTeam, ISportsmanDTO Sportsman, String position) throws RemoteException {

        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.getTransaction();
        ISportsman sm = SportsmanDAO.getInstance().getById(s, Sportsman.getId());
        ITrainingTeam t = TrainingTeamDAO.getInstance().getById(s, TrainingTeam.getId());
        ISportsmanTrainingTeam sttModel = new SportsmanTrainingTeam(sm, t, position);

        tx.begin();
        SportsmanTrainingTeamDAO.getInstance().add(HibernateUtil.getCurrentSession(), sttModel);

        SportsmanTrainingTeamDTO addedPlayer = new SportsmanTrainingTeamDTO(sttModel);
        
        try {
            MessageController mc = MessageController.getInstance();
            List<String> coaches = new LinkedList<String>();
            
            for(ICoach coach: t.getCoaches()){
                coaches.add(coach.getPerson().getUsername());
            }
           
            mc.createSportsmanAssignedMessage(coaches, Sportsman, TrainingTeam);
        } catch (Exception ex) {
            Logger.getLogger(TeamToPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        tx.commit();
        return addedPlayer;
    }
}
