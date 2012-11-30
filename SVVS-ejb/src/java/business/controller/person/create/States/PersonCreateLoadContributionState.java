/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.person.create.States;


import business.AController;
import business.controller.person.create.PersonCreation;
import data.DAOs.ContributionDAO;
import data.DTOs.ContributionDTO;
import data.DTOs.PersonDTO;
import data.hibernate.HibernateUtil;
import data.DTOs.IContributionDTO;
import data.DTOs.ICountryDTO;
import data.DTOs.IPersonDTO;
import data.DTOs.ISportDTO;
import data.models.IContribution;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.LinkedList;

/**
 *
 * @author phil
 */
public class PersonCreateLoadContributionState extends AController implements IPersonCreateState {

    PersonCreation _creator;

    public PersonCreateLoadContributionState(PersonCreation creator) throws RemoteException {
        super();
        _creator = creator;
    }

    @Override
    public LinkedList<ICountryDTO> loadCountries()throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<ISportDTO> loadSports()throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<IContributionDTO> loadContributions() throws RemoteException{
        LinkedList<IContributionDTO> contributions = new LinkedList<IContributionDTO>();
        
        for (IContribution iC : ContributionDAO.getInstance().getAll(HibernateUtil.getCurrentSession())) {
            ContributionDTO cdto = new ContributionDTO(iC);
            contributions.add(cdto);
        }
        
        _creator.setState(new PersonCreateState(_creator));
        return contributions;
    }

    @Override
    public PersonDTO CreatePerson(String firstname, String lastname, String sex,
    String phone, String mail, String username, String password,
    Date birthday, int right, String street, String postcode, String city, String country, int contributionID)throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void AssignToSport(LinkedList<String> sport, int personID)throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IPersonDTO CreatePersonDTO() throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IPersonDTO SaveDTO(IPersonDTO dto)throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
