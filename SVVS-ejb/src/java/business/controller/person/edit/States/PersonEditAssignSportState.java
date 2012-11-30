/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.person.edit.States;

import business.AController;
import business.controller.person.PersonController;
import business.controller.person.edit.IPersonEdit;
import data.DAOs.RoleDAO;
import data.hibernate.HibernateUtil;
import data.DTOs.IContributionDTO;
import data.DTOs.ICountryDTO;
import data.DTOs.IPersonDTO;
import data.DTOs.ISportDTO;
import data.models.Role;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.LinkedList;

/**
 *
 * @author phil
 */
class PersonEditAssignSportState extends AController implements IPersonEditState {

    IPersonEdit _editor;
    public PersonEditAssignSportState(IPersonEdit editor)throws RemoteException {
        super();
        _editor = editor;
    }

    @Override
    public LinkedList<ISportDTO> loadSports() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IPersonDTO editPerson(int PersonID, String firstname, String lastname, String sex, String phone, String mail, String username, String password, Date birthday, int right, String street, String postcode, String city, String country, int contributionID)throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void AssignToSport(LinkedList<String> sport, int personID) throws RemoteException {
          for (String sportname : sport) {
             //assigning values
            Role role = new Role();
            role.setPerson(PersonController.getInstance().loadPersonWithIDNonDTO(personID));
            role.setSport(PersonController.getInstance().loadSport(sportname));
            
            RoleDAO.getInstance().add(HibernateUtil.getCurrentSession(), role);
        }
    }

    @Override
    public LinkedList<ICountryDTO> loadCountries() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<IContributionDTO> loadContributions() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
