/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.person.edit.States;

import business.AController;
import business.controller.person.PersonController;
import business.controller.person.edit.PersonEdit;
import data.DTOs.IContributionDTO;
import data.DTOs.ICountryDTO;
import data.DTOs.IPersonDTO;
import data.DTOs.ISportDTO;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.LinkedList;

/**
 *
 * @author phil
 */
public class PersonEditLoadSportState extends AController implements IPersonEditState{

    PersonEdit _editor;

    public PersonEditLoadSportState(PersonEdit editor)throws RemoteException {
        super();
        _editor = editor;
    }

    @Override
    public LinkedList<ISportDTO> loadSports() throws RemoteException {
        _editor.setState(new PersonEditState(_editor));
        return PersonController.getInstance().loadSports();
    }

    @Override
    public IPersonDTO editPerson(int PersonID, String firstname, String lastname,
    String sex, String phone, String mail, String username, String password, Date birthday,
    int right, String street, String postcode, String city, String country, int contributionID)throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void AssignToSport(LinkedList<String> sport, int personID) throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<ICountryDTO> loadCountries() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<IContributionDTO> loadContributions()throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    
}
