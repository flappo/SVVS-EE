/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.IMatch;
import data.models.ITeam;
import data.models.ITournament;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public class TournamentDTO extends AbstractDTO<ITournament> implements ITournamentDTO {

    private String name;
    private String location;
    private String date;
    private double fee;
    private List<ITeamDTO> teams;
    private List<IMatchDTO> matches;
    private ISportDTO sport;
    private boolean finished;

    public TournamentDTO() throws RemoteException {
        super();
    }

    
    public TournamentDTO(ITournament model) throws RemoteException {
        super();
        if(model == null) return;
        teams = new LinkedList<ITeamDTO>();
        matches = new LinkedList<IMatchDTO>();
        extract(model);
    }

    @Override
    public void extract(ITournament model) throws RemoteException{
        try {
            this.id = model.getTournamentID();
            this.name = model.getName();
            this.date = model.getDate().toString();
            this.fee = (model.getFee() != null) ? model.getFee().doubleValue() : 0;
            this.location = model.getLocation();
            this.sport = new SportDTO(model.getSport());
            this.finished = model.isFinished();

            if (model.getTeams() != null) {
                for (ITeam team : model.getTeams()) {
                    teams.add(new TeamDTO(team));
                }
            }

            if (model.getMatches() != null) {
                for (IMatch match : model.getMatches()) {
                    matches.add(new MatchDTO(match));
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(TournamentDTO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getName()throws RemoteException {
        return name;
    }

    @Override
    public void setName(String name) throws RemoteException{
        this.name = name;
    }

    @Override
    public String getLocation() throws RemoteException{
        return location;
    }

    @Override
    public void setLocation(String location)throws RemoteException {
        this.location = location;
    }

    @Override
    public String getDate() throws RemoteException{
        return date;
    }

    @Override
    public void setDate(String date)throws RemoteException {
        this.date = date;
    }

    @Override
    public double getFee() throws RemoteException{
        return fee;
    }

    @Override
    public void setFee(double fee) throws RemoteException{
        this.fee = fee;
    }

    @Override
    public List<ITeamDTO> getTeams() throws RemoteException{
        return teams;
    }

    @Override
    public void setTeams(List<ITeamDTO> teams) throws RemoteException{
        this.teams = teams;
    }

    @Override
    public List<IMatchDTO> getMatches() throws RemoteException{
        return matches;
    }

    @Override
    public void setMatches(List<IMatchDTO> matches) throws RemoteException{
        this.matches = matches;
    }

    @Override
    public ISportDTO getSport()throws RemoteException {
        return sport;
    }

    @Override
    public void setSport(ISportDTO sport)throws RemoteException {
        this.sport = sport;
    }

    @Override
    public boolean isFinished() throws RemoteException{
        return finished;
    }

    @Override
    public void setFinished(boolean finished) throws RemoteException{
        this.finished = finished;
    }

    @Override
    public String toString() {
        try {
            return getName();
        } catch (RemoteException ex) {
            Logger.getLogger(TournamentDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getValues() throws RemoteException {
         try {
            return getName();
        } catch (RemoteException ex) {
            Logger.getLogger(TournamentDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    
}
