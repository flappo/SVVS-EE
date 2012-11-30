/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.DAOs.AddressDAO;
import data.DAOs.AdministratorDAO;
import data.DAOs.CaretakerDAO;
import data.DAOs.CoachDAO;
import data.DAOs.ContributionDAO;
import data.DAOs.CountryDAO;
import data.DAOs.DepartmentDAO;
import data.DAOs.LeagueDAO;
import data.DAOs.ManagerDAO;
import data.DAOs.MatchDAO;
import data.DAOs.PersonDAO;
import data.DAOs.RightDAO;
import data.DAOs.RoleDAO;
import data.DAOs.RoleRightsDAO;
import data.DAOs.SportDAO;
import data.DAOs.SportsmanDAO;
import data.DAOs.TeamDAO;
import data.DAOs.TournamentDAO;
import data.DAOs.TrainingTeamDAO;
import data.DAOs.IAddressDAO;
import data.DAOs.IAdministratorDAO;
import data.DAOs.ICaretakerDAO;
import data.DAOs.ICoachDAO;
import data.DAOs.IContributionDAO;
import data.DAOs.ICountryDAO;
import data.DAOs.IDepartmentDAO;
import data.DAOs.ILeagueDAO;
import data.DAOs.IManagerDAO;
import data.DAOs.IMatchDAO;
import data.DAOs.IPersonDAO;
import data.DAOs.IRightDAO;
import data.DAOs.IRoleDAO;
import data.DAOs.IRoleRightsDAO;
import data.DAOs.ISportDAO;
import data.DAOs.ISportsmanDAO;
import data.DAOs.ITeamDAO;
import data.DAOs.ITournamentDAO;
import data.DAOs.ITrainingTeamDAO;
import java.rmi.RemoteException;

/**
 *
 * @author Michael
 */
public class DataFacade {

    public static IAddressDAO getAddressDAO() throws RemoteException {
        return AddressDAO.getInstance();
    }

    public static IAdministratorDAO getAdministratorDAO() throws RemoteException {
        return AdministratorDAO.getInstance();
    }

    public static ICaretakerDAO getCaretakerDAO() throws RemoteException {
        return CaretakerDAO.getInstance();
    }

    public static ICoachDAO getCoachDAO() throws RemoteException {
        return CoachDAO.getInstance();
    }

    public static IContributionDAO getContributionDAO() throws RemoteException {
        return ContributionDAO.getInstance();
    }

    public static ICountryDAO getCountryDAO() throws RemoteException {
        return CountryDAO.getInstance();
    }

    public static IDepartmentDAO getDepartmentDAO() throws RemoteException {
        return DepartmentDAO.getInstance();
    }

    public static ILeagueDAO getLeagueDAO() throws RemoteException {
        return LeagueDAO.getInstance();
    }

    public static IManagerDAO getManagerDAO() throws RemoteException {
        return ManagerDAO.getInstance();
    }

    public static IMatchDAO getMatchDAO() throws RemoteException {
        return MatchDAO.getInstance();
    }

    public static IRightDAO getRightDAO() throws RemoteException {
        return RightDAO.getInstance();
    }

    public static IRoleDAO getRoleDAO() throws RemoteException {
        return RoleDAO.getInstance();
    }

    public static IPersonDAO getPersonDAO() throws RemoteException {
        return PersonDAO.getInstance();
    }

    public static IRoleRightsDAO getRoleRightsDAO() throws RemoteException {
        return RoleRightsDAO.getInstance();
    }

    public static ISportDAO getSportDAO() throws RemoteException {
        return SportDAO.getInstance();
    }

    public static ISportsmanDAO getSportsmanDAO() throws RemoteException {
        return SportsmanDAO.getInstance();
    }

    public static ITeamDAO getTeamDAO() throws RemoteException {
        return TeamDAO.getInstance();
    }

    public static ITournamentDAO getTournamentDAO() throws RemoteException {
        return TournamentDAO.getInstance();
    }

    public static ITrainingTeamDAO getTrainingTeamDAO() throws RemoteException {
        return TrainingTeamDAO.getInstance();
    }
}
