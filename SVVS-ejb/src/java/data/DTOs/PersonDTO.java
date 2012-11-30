/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.IDepartment;
import data.models.IPerson;
import data.models.IRole;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class PersonDTO extends AbstractDTO<IPerson> implements IPersonDTO {

    protected String firstname;
    protected String lastname;
    protected String sex;
    protected String phone;
    protected String mail;
    protected String username;
    protected String password;
    protected IAddressDTO mainAddress;
    protected Long right = new Long(0);
    protected String birthdate;
    protected IContributionDTO contribution;
    protected List<ISportDTO> sports;
    protected List<IDepartmentDTO> departments;
    protected String contributionStatus;

    public PersonDTO() throws RemoteException {
        super();
        sports = new LinkedList<ISportDTO>();
        departments = new LinkedList<IDepartmentDTO>();
    }

    public PersonDTO(IPerson person) throws RemoteException {
        super();
        if(person == null) return;
        sports = new LinkedList<ISportDTO>();
        departments = new LinkedList<IDepartmentDTO>();
        extract(person);
    }

    @Override
    public void extract(IPerson model)throws RemoteException {
        try {
            this.id = model.getPersonID();
            this.firstname = model.getFirstname();
            this.lastname = model.getLastname();
            this.sex = model.getSex();
            this.phone = model.getPhone();
            this.mail = model.getMail();
            this.username = model.getUsername();
            this.password = model.getPassword();
            this.mainAddress = new AddressDTO(model.getMainAddress());
    //        this.right = model.getRight();
            this.birthdate = (model.getBirthdate() == null) ? "" : model.getBirthdate().toString();

            this.contribution = (model.getLastContribution() == null) ? null : new ContributionDTO(model.getLastContribution());

            for (IRole role : model.getRoles()) {
                if (role.getSport() != null) {
                    sports.add(new SportDTO(role.getSport()));
                }
                if (role.getRoleRight() != null) {
                    this.right = this.right.longValue() | new Long(role.getRoleRight().getRight()).longValue();
                }
            }

            for (IDepartment dept : model.getDepartments()) {
                departments.add(new DepartmentDTO(dept));
            }

            this.contributionStatus = model.getLastContributionStatus();
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        try {
            return getFirstname() + " " + getLastname();
        } catch (RemoteException ex) {
            Logger.getLogger(PersonDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    @Override
    public String getFirstname() throws RemoteException{
        return firstname;
    }

    @Override
    public void setFirstname(String firstname) throws RemoteException{
        this.firstname = firstname;
    }

    @Override
    public String getLastname() throws RemoteException{
        return lastname;
    }

    @Override
    public void setLastname(String lastname)throws RemoteException {
        this.lastname = lastname;
    }

    @Override
    public String getSex()throws RemoteException {
        return sex;
    }

    @Override
    public void setSex(String sex) throws RemoteException{
        this.sex = sex;
    }

    @Override
    public String getPhone() throws RemoteException{
        return phone;
    }

    @Override
    public void setPhone(String phone)throws RemoteException {
        this.phone = phone;
    }

    @Override
    public String getMail()throws RemoteException{
        return mail;
    }

    @Override
    public void setMail(String mail)throws RemoteException {
        this.mail = mail;
    }

    @Override
    public String getUsername()throws RemoteException {
        return username;
    }

    @Override
    public void setUsername(String username) throws RemoteException{
        this.username = username;
    }

    @Override
    public String getPassword() throws RemoteException{
        return password;
    }

    @Override
    public void setPassword(String password) throws RemoteException{
        this.password = password;
    }

    @Override
    public IAddressDTO getMainAddress() throws RemoteException{
        return mainAddress;
    }

    @Override
    public void setMainAddress(IAddressDTO mainAddress) throws RemoteException{
        this.mainAddress = mainAddress;
    }

    @Override
    public Long getRight() throws RemoteException{
        return right;
    }

    @Override
    public void setRight(Long right) throws RemoteException{
        this.right = right;
    }

    @Override
    public String getBirthdate() throws RemoteException{
        return birthdate;
    }

    @Override
    public void setBirthdate(String birthdate)throws RemoteException {
        this.birthdate = birthdate;
    }

    @Override
    public IContributionDTO getContribution() throws RemoteException{
        return contribution;
    }

    @Override
    public void setContribution(IContributionDTO contribution) throws RemoteException{
        this.contribution = contribution;
    }

    @Override
    public List<ISportDTO> getSports()throws RemoteException {
        return sports;
    }

    @Override
    public void setSports(List<ISportDTO> sports)throws RemoteException {
        this.sports = sports;
    }

    @Override
    public List<IDepartmentDTO> getDepartments()throws RemoteException {
        return departments;
    }

    @Override
    public void setDepartments(List<IDepartmentDTO> departments)throws RemoteException {
        this.departments = departments;
    }

    @Override
    public String getContributionStatus()throws RemoteException {
        return contributionStatus;
    }

    @Override
    public void setContributionStatus(String contributionStatus) throws RemoteException{
        this.contributionStatus = contributionStatus;
    }

    public boolean hasRight(long right) throws RemoteException{
        return ((this.right & right) > 0) ? true : false;
    }
}
