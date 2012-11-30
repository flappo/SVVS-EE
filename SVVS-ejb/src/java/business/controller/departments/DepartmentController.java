/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.departments;

import business.AController;
import data.DAOs.DepartmentDAO;
import data.DTOs.DepartmentDTO;
import data.DTOs.IDepartmentDTO;
import data.DTOs.ISportDTO;
import data.hibernate.HibernateUtil;
import data.models.IDepartment;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author phil
 */
public class DepartmentController extends AController implements IDepartmentController {

    private static DepartmentController instance;

    private DepartmentController() throws RemoteException {
        super();
    }

    public static DepartmentController getInstance() throws RemoteException {
        if (instance == null) {
            return (instance = new DepartmentController());
        }
        return instance;
    }

    @Override
    public List<IDepartmentDTO> loadDepartments() throws RemoteException {
        LinkedList<IDepartmentDTO> deps = new LinkedList<IDepartmentDTO>();
        for (IDepartment iDep : DepartmentDAO.getInstance().getAll(HibernateUtil.getCurrentSession())) {
            deps.add(new DepartmentDTO(iDep));
        }
        return deps;
    }

    @Override
    public boolean isSportInDepartment(IDepartmentDTO dept, ISportDTO sport) throws RemoteException {
        
        for(ISportDTO s : dept.getSports()) {
            if(s.equals(sport)) {
                return true;
            }
        }
        
        return false;
        
    }
}
