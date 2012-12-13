/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.tournament;

import business.AController;
import business.controller.departments.DepartmentController;
import data.DAOs.SportDAO;
import data.DAOs.TeamDAO;
import data.DAOs.TournamentDAO;
import data.DTOs.TeamDTO;
import data.hibernate.HibernateUtil;
import data.DTOs.IDepartmentDTO;
import data.DTOs.ISportDTO;
import data.DTOs.ITeamDTO;
import data.DTOs.ITournamentDTO;
import data.DTOs.TournamentDTO;
import data.models.ISport;
import data.models.ITeam;
import data.models.ITournament;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author phil
 */
public class TournamentController extends AController implements ITournamentController {

    private static TournamentController instance;

    private TournamentController() throws RemoteException {
        super();
    }

    public static TournamentController getInstance() throws RemoteException {
        if (instance == null) {
            return (instance = new TournamentController());
        }
        return instance;
    }

    @Override
    public LinkedList<ISportDTO> loadSport(List<IDepartmentDTO> department) throws RemoteException {
        DepartmentController deptCon = DepartmentController.getInstance();
        LinkedList<ISportDTO> sports = new LinkedList<ISportDTO>();

        for (ISportDTO iS : SportDAO.getInstance().getAllDTO(HibernateUtil.getCurrentSession())) {
            if (department == null) {
                sports.add(iS);
            } else {
                for (IDepartmentDTO dept : department) {
                    if (deptCon.isSportInDepartment(dept, iS)) {
                        sports.add(iS);
                    }
                }
            }
        }

        return sports;
    }

    @Override
    public LinkedList<ITeamDTO> loadTeams(String sportname) throws RemoteException {
        ISport sport = SportDAO.getInstance().getByName(HibernateUtil.getCurrentSession(), sportname);
        LinkedList<ITeamDTO> teams = new LinkedList<ITeamDTO>();
        for (ITeam iTeam : TeamDAO.getInstance().getBySport(HibernateUtil.getCurrentSession(), sport)) {
            teams.add(new TeamDTO(iTeam));
        }
        return teams;
    }

    @Override
    public LinkedList<ITournamentDTO> loadTournaments() throws RemoteException {
        return new LinkedList<ITournamentDTO>(TournamentDAO.getInstance().getAllDTO(HibernateUtil.getCurrentSession()));
    }

    @Override
    public ITournament loadTournament(int ID) throws RemoteException {
        List<ITournament> tournaments = TournamentDAO.getInstance().getAll(HibernateUtil.getCurrentSession());
        for (ITournament iT : tournaments) {
            if (iT.getTournamentID() == ID) {
                return iT;
            }
        }
        return null;
    }

    @Override
    public ITournamentDTO loadTournamentDTO(int ID) throws RemoteException {
        List<ITournament> tournaments = TournamentDAO.getInstance().getAll(HibernateUtil.getCurrentSession());
        for (ITournament iT : tournaments) {
            if (iT.getTournamentID() == ID) {
                return new TournamentDTO(iT); 
            }
        }
        return null;
    }
}
