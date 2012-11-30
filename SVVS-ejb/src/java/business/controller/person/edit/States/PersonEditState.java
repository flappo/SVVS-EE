/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.person.edit.States;

import business.AController;
import business.controller.person.PersonController;
import business.controller.person.edit.PersonEdit;
import data.DAOs.AddressDAO;
import data.DAOs.ContributionDAO;
import data.DAOs.CountryDAO;
import data.DAOs.PersonDAO;
import data.DTOs.PersonDTO;
import data.hibernate.HibernateUtil;
import data.DTOs.IContributionDTO;
import data.DTOs.ICountryDTO;
import data.DTOs.IPersonDTO;
import data.DTOs.ISportDTO;
import data.models.IAddress;
import data.models.IContribution;
import data.models.IContributionHistory;
import data.models.ICountry;
import data.models.IPerson;
import data.models.ContributionHistory;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Transaction;
import org.joda.time.DateTime;

/**
 *
 * @author phil
 */
public class PersonEditState extends AController implements IPersonEditState {

    PersonEdit _editor; 
    
    public PersonEditState(PersonEdit editor)throws RemoteException {
        super();
        _editor = editor;
    }

    @Override
    public LinkedList<ISportDTO> loadSports() throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IPersonDTO editPerson(int PersonID, String firstname, String lastname,
    String sex, String phone, String mail, String username, String password,
    Date birthday, int right, String street, String postcode, String city, String country, int contributionID) throws RemoteException{
        
        Transaction tx = HibernateUtil.getCurrentSession().beginTransaction();
        
        IPerson person = PersonController.getInstance().loadPersonWithIDNonDTO(PersonID);

        IAddress address = person.getMainAddress();
        //assigning values
        address.setStreet(street);
        address.setCity(city);
        address.setPostcode(postcode);

        ICountry c = CountryDAO.getInstance().getByName(HibernateUtil.getCurrentSession(), country);
        address.setCountry(c);

        //fill getter and setter
        AddressDAO.getInstance().update(HibernateUtil.getCurrentSession(), address);

        person.setFirstname(firstname);
        person.setLastname(lastname);
        person.setSex(sex);
        person.setPhone(phone);
        person.setMail(mail);
        person.setUsername(username);
        person.setPassword(password);
        person.setMainAddress(address);
        person.setBirthdate(birthday);
     
        ContributionHistory ch = new ContributionHistory(person, getContributionByID(contributionID),
                DateTime.now().getYear(), DateTime.now().getMonthOfYear(), "0");

        List<IContributionHistory> chh = person.getContributionHistory();
        chh.add(ch);
        person.setContributionHistory(chh);

        PersonDAO.getInstance().update(HibernateUtil.getCurrentSession(), person);

        _editor.setState(new PersonEditAssignSportState(_editor));
        PersonDTO editedPerson = new PersonDTO(person);
        
        tx.commit();
        return editedPerson;
    }

    /**
     * Return a Contribution searched with the id
     *
     * @param contributionID
     * @return
     */
    private IContribution getContributionByID(int contributionID) throws RemoteException {
        for (IContribution ic : ContributionDAO.getInstance().getAll(HibernateUtil.getCurrentSession())) {
            if (ic.getContributionID() == contributionID) {
                return ic;
            }
        }
        return null;
    }

    @Override
    public void AssignToSport(LinkedList<String> sport, int personID)throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<ICountryDTO> loadCountries() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<IContributionDTO> loadContributions() throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
