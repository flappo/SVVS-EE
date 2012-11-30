/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.sport;

import business.AController;
import data.DAOs.SportDAO;
import data.DTOs.ISportDTO;
import data.hibernate.HibernateUtil;
import java.rmi.RemoteException;
import java.util.List;
import javax.rmi.PortableRemoteObject;

/**
 *
 * @author phil
 */
public class SportController extends AController implements ISportController {

    public SportController() throws RemoteException {
        super();
    }

    @Override
    public List<ISportDTO> getAllSports() throws RemoteException{
        return SportDAO.getInstance().getAllDTO(HibernateUtil.getCurrentSession());
    }
}
