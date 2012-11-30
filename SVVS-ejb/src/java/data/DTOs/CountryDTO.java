/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;


import data.models.ICountry;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class CountryDTO extends AbstractDTO<ICountry> implements ICountryDTO{

    private String name;
    private String code;

    public CountryDTO() throws RemoteException{
        super();
    }
    
    public CountryDTO(ICountry model)throws RemoteException{
        super();
        if(model == null) return;
        extract(model);
    }
    
    
    @Override
    public final void extract(ICountry model)throws RemoteException{
        this.id = model.getCountryID();
        this.name = model.getName();
        this.code = model.getCode();
    }
    
    @Override
    public String getName() throws RemoteException{
        return name;
    }

    @Override
    public void setName(String name) throws RemoteException{
        this.name = name;
    }

    @Override
    public String getCode() throws RemoteException{
        return code;
    }

    @Override
    public void setCode(String code)throws RemoteException {
        this.code = code;
    }

    @Override
    public String toString() {
        try {
            return getName();
        } catch (RemoteException ex) {
            Logger.getLogger(CountryDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
