/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.SportsmanDTO;
import data.DTOs.ISportsmanDTO;
import data.models.IDepartment;
import data.models.IPerson;
import data.models.IRole;
import data.models.ISport;
import data.models.ISportsman;
import data.models.Sportsman;
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
public class SportsmanDAO extends AbstractDAO<ISportsman, ISportsmanDTO> implements ISportsmanDAO {

    private static ISportsmanDAO instance;

    private SportsmanDAO() throws RemoteException {
        super("data.models.Sportsman");
    }

    public static ISportsmanDAO getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SportsmanDAO();
        }
        return instance;
    }

    @Override
    public ISportsman create()  throws RemoteException{
        return new Sportsman();
    }

    @Override
    public ISportsmanDTO extractDTO(ISportsman model)  throws RemoteException{
        try {
            return new SportsmanDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(SportsmanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<ISportsman> getByPerson(Session s, IPerson person)  throws RemoteException{

        Query query = s.createQuery("FROM " + getTable() + " WHERE person = :person");
        query.setParameter("person", person);
        List<ISportsman> roles = new LinkedList<ISportsman>();
        roles = query.list();

        if (roles.isEmpty()) {
            return null;
        }

        return roles;

    }

    @Override
    public ISportsman getByAll(Session s, IPerson person, IDepartment department, ISport sport)  throws RemoteException{

        Query query = s.createQuery("FROM " + getTable() + " WHERE person = :person AND department = :department AND sport = :sport");
        query.setParameter("person", person);
        query.setParameter("department", department);
        query.setParameter("sport", sport);

        return (ISportsman) query.uniqueResult();

    }

    @Override
    public ISportsman getById(Session s, int id)  throws RemoteException{

        Query query = s.createQuery("FROM " + getTable() + " where roleID =:id");
        query.setInteger("id", id);
        return (ISportsman) query.uniqueResult();
    }
}
