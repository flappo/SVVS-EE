/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.AddressDTO;
import data.DTOs.CountryDTO;
import data.DTOs.IAddressDTO;
import data.DTOs.ICountryDTO;
import data.models.IAddress;
import data.models.Address;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Michael
 */
public class AddressDAO extends AbstractDAO<IAddress, IAddressDTO> implements IAddressDAO {

    private static IAddressDAO instance;

    private AddressDAO() throws RemoteException {
        super("data.models.Address");
    }

    public static IAddressDAO getInstance() throws RemoteException {
        if (instance == null) {
            instance = new AddressDAO();
        }
        return instance;
    }

    @Override
    public IAddress create()  throws RemoteException{
        return new Address();
    }

    @Override
    public List<IAddress> getByCity(Session s, String city) throws RemoteException {

        Query query = s.createQuery("FROM " + getTable() + " WHERE city = :city");
        query.setString(":city", city);
        return query.list();
    }

    @Override
    public IAddressDTO extractDTO(IAddress model) throws RemoteException {
        try {
            return new AddressDTO(model);
        } catch (RemoteException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IAddress getById(Session s, int id) throws RemoteException {

        Query query = s.createQuery("FROM " + getTable() + " Where addressID =:id");
        query.setInteger("id", id);
        return (IAddress) query.uniqueResult();
    }

    @Override
    public IAddressDTO saveDTO(Session s, IAddressDTO dto) throws RemoteException {

        if (dto == null) {
            return null;
        }
        try {
            return new AddressDTO(saveDTOgetModel(s, dto));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IAddress saveDTOgetModel(Session s, IAddressDTO dto)  throws RemoteException{

        if (dto == null) {
            return null;
        }

        IAddress address = (dto.getId() == 0) ? null : getById(s, dto.getId());

        if (address == null) {
            address = create();
        }
        try {
            address.setCountry(CountryDAO.getInstance().saveDTOgetModel(s, dto.getCountry()));
        } catch (RemoteException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        address.setCity(dto.getCity());
        address.setPostcode(dto.getPostcode());
        address.setStreet(dto.getStreet());

        s.saveOrUpdate(address);

        return address;
    }
}
