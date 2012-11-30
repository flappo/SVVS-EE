/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.IAddress;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class AddressDTO extends AbstractDTO<IAddress> implements IAddressDTO {

    protected String street;
    protected String postcode;
    protected String city;
    protected ICountryDTO country;

    public AddressDTO() throws RemoteException {
        super();

    }

    public AddressDTO(IAddress model) throws RemoteException {
        super();
        extract(model);
    }

    @Override
    public void extract(IAddress model) throws RemoteException {
        if (model == null) {
            return;
        }
        this.id = model.getAddressID();
        this.street = model.getStreet();
        this.postcode = model.getPostcode();
        this.city = model.getCity();
        try {
            this.country = new CountryDTO(model.getCountry());
        } catch (RemoteException ex) {
            Logger.getLogger(AddressDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setStreet(String street) throws RemoteException {
        this.street = street;
    }

    @Override
    public String getStreet() throws RemoteException {
        return this.street;
    }

    @Override
    public void setPostcode(String postcode) throws RemoteException {
        this.postcode = postcode;
    }

    @Override
    public String getPostcode() throws RemoteException {
        return this.postcode;
    }

    @Override
    public void setCity(String city)throws RemoteException  {
        this.city = city;
    }

    @Override
    public String getCity()throws RemoteException  {
        return this.city;
    }

    @Override
    public void setCountry(ICountryDTO country) throws RemoteException {
        this.country = country;
    }

    @Override
    public ICountryDTO getCountry() throws RemoteException {
        return this.country;
    }

    @Override
    public String toString() {
        try {
            return street + ", " + postcode + " " + city + ", " + country.getName();
        } catch (RemoteException ex) {
            Logger.getLogger(AddressDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
