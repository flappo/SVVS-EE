/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.person.create;

import business.AController;
import business.controller.person.create.States.IPersonCreateState;
import business.controller.person.create.States.PersonCreateLoadCountryState;
import data.DTOs.IContributionDTO;
import data.DTOs.ICountryDTO;
import data.DTOs.IPersonDTO;
import data.DTOs.ISportDTO;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.LinkedList;

/**
 * First loadCountries Second loadSports Third LoadContributions Fourth
 * CreatePerson Fifth AssignToSport
 *
 * @author phil
 */
public class PersonCreation extends AController implements IPersonCreation {

    IPersonCreateState _state;

    public IPersonCreateState getState() throws  RemoteException{
        return _state;
    }

    public void setState(IPersonCreateState _state) throws RemoteException{
        this._state = _state;
    }

    /**
     * First loadCountries
     * Second loadSports
     * Third LoadContributions 
     * Fourth CreatePerson
     * Fifth AssignToSport
     */
    public PersonCreation() throws RemoteException {
        super();
        setState(new PersonCreateLoadCountryState(this));
    }

    @Override
    public LinkedList<ICountryDTO> loadCountries() throws RemoteException{
        return _state.loadCountries();
    }

    @Override
    public LinkedList<ISportDTO> loadSports() throws RemoteException{
        return _state.loadSports();
    }

    @Override
    public LinkedList<IContributionDTO> loadContributions() throws RemoteException{
        return _state.loadContributions();
    }

    @Override
    public IPersonDTO CreatePerson(String firstname, String lastname,
            String sex, String phone, String mail,
            String username, String password, Date birthday, int right,
            String street, String postcode, String city, String country, int contributionID) throws RemoteException {
       return _state.CreatePerson(firstname, lastname, sex, phone, mail,
                username, password, birthday, right,
                street, postcode, city, country, contributionID);
    }

    @Override
    public void AssignToSport(LinkedList<String> sport, int personID) throws RemoteException {
        _state.AssignToSport(sport, personID);
    }
    
     @Override
    public IPersonDTO CreatePersonDTO() throws RemoteException{
        return _state.CreatePersonDTO();
    }
    
    @Override
    public IPersonDTO SaveDTO(IPersonDTO dto) throws RemoteException{
        
        return _state.SaveDTO(dto);
    }
}
