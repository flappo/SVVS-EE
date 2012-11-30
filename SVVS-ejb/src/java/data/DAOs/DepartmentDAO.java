/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.DepartmentDTO;
import data.DTOs.IDepartmentDTO;
import data.models.IDepartment;
import data.models.ISport;
import data.models.Department;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author uubu
 */
public class DepartmentDAO extends AbstractDAO<IDepartment, IDepartmentDTO> implements IDepartmentDAO {

    private static IDepartmentDAO instance;

    private DepartmentDAO() throws RemoteException {
        super("data.models.Department");
    }

    public static IDepartmentDAO getInstance() throws RemoteException {
        if (instance == null) {
            instance = new DepartmentDAO();
        }
        return instance;
    }

    @Override
    public IDepartment create()  throws RemoteException{
        return new Department();
    }

    @Override
    public IDepartmentDTO extractDTO(IDepartment model)  throws RemoteException{
        try {
            return new DepartmentDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(DepartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public IDepartment getBySport(Session s, ISport model) throws RemoteException {

        Query query = s.createQuery("FROM " + getTable() + " WHERE sport = :model");
        query.setParameter("model", model);
        return (IDepartment) query.uniqueResult();

    }
    
     @Override
    public IDepartment getById(Session s, int id)  throws RemoteException{

        Query query = s.createQuery("FROM " + getTable() + " where departmentID =:id");
        query.setInteger("id", id);
        return (IDepartment) query.uniqueResult();
    }
}
