/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.person.create.States;

import business.AController;
import business.controller.person.create.PersonCreation;
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
import data.models.IContribution;
import data.models.IContributionHistory;
import data.models.ICountry;
import data.models.Address;
import data.models.ContributionHistory;
import data.models.Person;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.DateTime;

/**
 *
 * @author phil
 */
public class PersonCreateState extends AController implements IPersonCreateState {

    PersonCreation _creator;

    public PersonCreateState(PersonCreation creator) throws RemoteException{
        super();
        _creator = creator;
    }

    @Override
    public LinkedList<ICountryDTO> loadCountries() throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public IPersonDTO CreatePersonDTO()throws RemoteException{
        
        return PersonDAO.getInstance().createPersonDTO();
    }
    
    @Override
    public IPersonDTO SaveDTO(IPersonDTO dto)throws RemoteException{
        
        Session s = HibernateUtil.getCurrentSession();
        Transaction tx = s.beginTransaction();
        IPersonDTO personDTO = PersonDAO.getInstance().saveDTO(s, dto);
        tx.commit();
        return personDTO;
    }

    @Override
    public PersonDTO CreatePerson(String firstname, String lastname,
            String sex, String phone, String mail,
            String username, String password, Date birthday, int right,
            String street, String postcode, String city, String country, int contributionID) throws RemoteException {
        
        Transaction tx = HibernateUtil.getCurrentSession().beginTransaction();
        
        Address address = new Address();
        //assigning values
        address.setStreet(street);
        address.setCity(city);
        address.setPostcode(postcode);

        ICountry c = CountryDAO.getInstance().getByName(HibernateUtil.getCurrentSession(), country);
        address.setCountry(c);

        //fill getter and setter
        AddressDAO.getInstance().add(HibernateUtil.getCurrentSession(), address);

        Person person = new Person();
        //fill getter and setter
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
        
        LinkedList<IContributionHistory> chh = new LinkedList<IContributionHistory>();
        chh.add(ch);
        person.setContributionHistory(chh);

        PersonDAO.getInstance().add(HibernateUtil.getCurrentSession(), person);

        _creator.setState(new PersonCreateAssignSportState(_creator));
        
        PersonDTO createdPerson = new PersonDTO(person);
        tx.commit();

        return createdPerson;
    }

    /**
     * Return a Contribution searched with the id
     *
     * @param contributionID
     * @return
     */
    private IContribution getContributionByID(int contributionID) throws RemoteException{
        for (IContribution ic : ContributionDAO.getInstance().getAll(HibernateUtil.getCurrentSession())) {
            if (ic.getContributionID() == contributionID) {
                return ic;
            }
        }
        return null;
    }

    @Override
    public LinkedList<ISportDTO> loadSports() throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<IContributionDTO> loadContributions()throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void AssignToSport(LinkedList<String> sport, int personID) throws RemoteException{
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
