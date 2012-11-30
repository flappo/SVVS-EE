/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.TrainingTeamDTO;
import data.DTOs.ITrainingTeamDTO;
import data.models.ITrainingTeam;
import data.models.TrainingTeam;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author uubu
 */
public class TrainingTeamDAO extends AbstractDAO<ITrainingTeam, ITrainingTeamDTO> implements ITrainingTeamDAO {

    private static ITrainingTeamDAO instance;

    private TrainingTeamDAO() throws RemoteException {
        super("data.models.TrainingTeam");
    }

    public static ITrainingTeamDAO getInstance() throws RemoteException {
        if (instance == null) {
            instance = new TrainingTeamDAO();
        }
        return instance;
    }

    @Override
    public ITrainingTeam create()  throws RemoteException{
        return new TrainingTeam();
    }

    @Override
    public ITrainingTeam getByName(Session s, String name)  throws RemoteException{

        Query query = s.createQuery("FROM " + getTable() + " WHERE name LIKE '%" + name + "%'");
        return (ITrainingTeam) query.uniqueResult();
    }

    @Override
    public ITrainingTeamDTO extractDTO(ITrainingTeam model)  throws RemoteException{
        try {
            return new TrainingTeamDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(TrainingTeamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ITrainingTeam getById(Session s, int id)  throws RemoteException{

        Query query = s.createQuery("FROM " + getTable() + " Where teamID =:id");
        query.setInteger("id", id);
        return (ITrainingTeam) query.uniqueResult();
    }
}
