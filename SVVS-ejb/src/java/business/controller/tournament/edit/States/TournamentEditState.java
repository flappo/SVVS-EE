/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.tournament.edit.States;

import business.AController;
import business.controller.tournament.TournamentController;
import business.controller.tournament.edit.TournamentEdit;
import data.DAOs.SportDAO;
import data.DAOs.TeamDAO;
import data.DAOs.TournamentDAO;
import data.hibernate.HibernateUtil;
import data.DTOs.ITournamentDTO;
import data.models.ISport;
import data.models.ITeam;
import data.models.ITournament;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author phil
 */
public class TournamentEditState extends AController implements ITournamentEditState  {
     TournamentEdit _editor;

    public TournamentEditState(TournamentEdit editor)throws RemoteException {
        super();
        _editor = editor;
    }

    @Override
    public ITournamentDTO loadTournament(int ID)throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void EditTournament(int ID, String name, String location, BigDecimal fee, boolean finished, List<String> TeamNames) throws RemoteException {
 
        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.beginTransaction();
        
        ITournament tournament = TournamentController.getInstance().loadTournament(ID);
        tournament.setName(name);
        tournament.setLocation(location);
        tournament.setFee(fee);
        tournament.setFinished(finished);
        
        List<ITeam> teams = tournament.getTeams();
        //für jedes  team in der stringliste werden alle teams durchgegangen ob der name darin vorhanden ist
        for (String team : TeamNames) {
            for (ITeam it : TeamDAO.getInstance().getAll(HibernateUtil.getCurrentSession())) {
                if (it.getName().equals(team)) {
                    teams.add(it);
                }
            }
        }

        tournament.setTeams(teams);

        TournamentDAO.getInstance().update(HibernateUtil.getCurrentSession(), tournament);
        _editor.setCurState(new TournamentEditAddMatchState(_editor));
        
        tx.commit();
    }

    @Override
    public void AddMatch(int TournamentID, String location, Date date, String Team1Name, String Team2Name, Integer goalsTeam1, Integer goalsTeam2) throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
