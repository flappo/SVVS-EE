/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DAOs;

import data.DTOs.CountryDTO;
import data.DTOs.ICountryDTO;
import data.models.ICountry;
import data.models.Country;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Remote;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Michael
 */
@Remote
public class CountryDAO extends AbstractDAO<ICountry, ICountryDTO> implements ICountryDAO {

    private static ICountryDAO instance;

    
    private CountryDAO() throws RemoteException {
        super("data.models.Country");
    }

    public static ICountryDAO getInstance() throws RemoteException {
        if (instance == null) {
            instance = new CountryDAO();
        }
        return instance;
    }

    @Override
    public ICountryDTO extractDTO(ICountry country)  throws RemoteException{
        try {
            return new CountryDTO(country);
        } catch (RemoteException ex) {
            Logger.getLogger(CountryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ICountry create()  throws RemoteException{
        return new Country();
    }

    @Override
    public ICountry getByName(Session s, String name) throws RemoteException {

        Query query = s.createQuery("FROM " + getTable() + " WHERE name = '" + name + "'");
        return (ICountry) query.list().get(0);
    }

    @Override
    public ICountry getByCode(Session s, String code)  throws RemoteException{

        Query query = s.createQuery("FROM " + getTable() + " WHERE code = :code");
        query.setString(":code", code);
        return (ICountry) query.list().get(0);
    }

    @Override
    public ICountry getById(Session s, int id) throws RemoteException {

        Query query = s.createQuery("FROM " + getTable() + " Where countryID =:id");
        query.setInteger("id", id);
        return (ICountry) query.uniqueResult();
    }

    @Override
    public ICountry saveDTOgetModel(Session s, ICountryDTO dto)  throws RemoteException{

        if (dto == null) {
            return null;
        }

        ICountry country = (dto.getId()==0)? null:getById(s, dto.getId());

        if (country == null) {
            country = create();
        }
        country.setName(dto.getName());
        country.setCode(dto.getCode());

        s.saveOrUpdate(country);

        return country;
    }

    @Override
    public ICountryDTO saveDTO(Session s, ICountryDTO dto)  throws RemoteException{
        try {
            return new CountryDTO(saveDTOgetModel(s, dto));
        } catch (RemoteException ex) {
            Logger.getLogger(CountryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
