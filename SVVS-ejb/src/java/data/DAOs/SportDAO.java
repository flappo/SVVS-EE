/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.SportDTO;
import data.DTOs.ISportDTO;
import data.models.ISport;
import data.models.Sport;
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
public class SportDAO extends AbstractDAO<ISport, ISportDTO> implements ISportDAO {

    private static ISportDAO instance;

    private SportDAO() throws RemoteException {
        super("data.models.Sport");
    }

    public static ISportDAO getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SportDAO();
        }
        return instance;
    }

    @Override
    public ISport create()  throws RemoteException{
        return new Sport();
    }

    @Override
    public ISportDTO extractDTO(ISport model) throws RemoteException {
        try {
            return new SportDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(SportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ISport getByName(Session s, String name)  throws RemoteException{

        Query query = s.createQuery("FROM " + getTable() + " WHERE name = :name");
        query.setString("name", name);
        return (ISport) query.uniqueResult();
    }

    @Override
    public ISport getById(Session s, int id)  throws RemoteException{

        Query query = s.createQuery("FROM " + getTable() + " Where sportID =:id");
        query.setInteger("id", id);
        return (ISport) query.uniqueResult();
    }

    @Override
    public ISport saveDTOgetModel(Session s, ISportDTO dto)  throws RemoteException{

        if (dto == null) {
            return null;
        }

        ISport sport = (dto.getId() == 0) ? null : getById(s, dto.getId());

        if (sport == null) {
            sport = create();
        }
        sport.setName(dto.getName());
        sport.setMaxPlayers(dto.getMaxPlayers());

        s.saveOrUpdate(sport);

        return sport;
    }

    @Override
    public ISportDTO saveDTO(Session s, ISportDTO dto) throws RemoteException {
        try {
            return new SportDTO(saveDTOgetModel(s, dto));
        } catch (RemoteException ex) {
            Logger.getLogger(SportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
