/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.person.create.States;

import business.AController;
import business.controller.person.PersonController;
import business.controller.person.create.PersonCreation;
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
public class PersonCreateLoadSportState extends AController implements IPersonCreateState {

    PersonCreation _creator;

   public PersonCreateLoadSportState(PersonCreation creator) throws RemoteException{
       super();
        _creator = creator;
    }

    @Override
    public LinkedList<ICountryDTO> loadCountries() throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<ISportDTO> loadSports() throws RemoteException {
        /*LinkedList<ISportDTO> sports = new LinkedList<ISportDTO>();

        for (ISport iS : SportDAO.getInstance().getAll(HibernateUtil.getCurrentSession())) {
            SportDTO sdto = new SportDTO(iS);
            sports.add(sdto);
        }
        _creator.setState(new PersonCreateLoadContributionState(_creator));


        return sports;*/
        _creator.setState(new PersonCreateLoadContributionState(_creator));
        return PersonController.getInstance().loadSports();
    }


    @Override
    public LinkedList<IContributionDTO> loadContributions() throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IPersonDTO CreatePerson(String firstname, String lastname, String sex, String phone, String mail, String username, String password, Date birthday, int right, String street, String postcode, String city, String country, int contributionID) throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void AssignToSport(LinkedList<String> sport, int personID) throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IPersonDTO CreatePersonDTO() throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IPersonDTO SaveDTO(IPersonDTO dto) throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
