/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.person.edit;

import business.AController;
import business.controller.person.edit.States.IPersonEditState;
import business.controller.person.edit.States.PersonEditLoadCountryState;
import data.DTOs.IContributionDTO;
import data.DTOs.ICountryDTO;
import data.DTOs.IPersonDTO;
import data.DTOs.ISportDTO;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.LinkedList;

/**
 * First loadsports
 * Second EditPerson
 * Third AssignToSport
 * @author phil
 */
public class PersonEdit  extends AController  implements IPersonEdit {
    IPersonEditState _state;

    public IPersonEditState getState() throws RemoteException{
        return _state;
    }

    public void setState(IPersonEditState _state) throws  RemoteException{
        this._state = _state;
    }

    public PersonEdit() throws RemoteException {
        super();
        setState(new PersonEditLoadCountryState(this));
    }

    @Override
    public LinkedList<ISportDTO> loadSports() throws RemoteException {
        return _state.loadSports();
    }

    @Override
    public IPersonDTO editPerson(int PersonID, String firstname, String lastname, String sex, String phone, String mail, String username, String password, Date birthday, int right, String street, String postcode, String city, String country, int contributionID) throws RemoteException {
        return _state.editPerson(PersonID, firstname, lastname, sex, phone, mail, username, password, birthday, right, street, postcode, city, country, contributionID);
    }

    @Override
    public void AssignToSport(LinkedList<String> sport, int personID) throws RemoteException {
        _state.AssignToSport(sport, personID);
    }

    @Override
    public LinkedList<ICountryDTO> loadCountries() throws RemoteException {
        return _state.loadCountries();
    }

    @Override
    public LinkedList<IContributionDTO> loadContributions() throws RemoteException {
        return _state.loadContributions();
    }

   
}
