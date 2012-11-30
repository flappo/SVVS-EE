/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.country;

import business.AController;
import data.DAOs.CountryDAO;
import data.hibernate.HibernateUtil;
import data.DTOs.ICountryDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import javax.ejb.Stateless;
import javax.rmi.PortableRemoteObject;


/**
 *
 * @author phil
 */
@Stateless
public class CountryController extends AController implements CountryControllerRemote {

    public CountryController() throws RemoteException {
        super();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<ICountryDTO> getCountries() throws RemoteException {
        return CountryDAO.getInstance().getAllDTO(HibernateUtil.getCurrentSession());
    }
}
