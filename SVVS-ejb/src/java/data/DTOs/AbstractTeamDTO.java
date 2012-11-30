/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.DTOs;

import data.models.ITeam;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uubu
 */
public abstract class AbstractTeamDTO<V extends ITeam> extends AbstractDTO<V> {

    public AbstractTeamDTO() throws RemoteException {
        super();
    }
    
    protected String name;
    protected ISportDTO sport;
    protected ILeagueDTO league;

    public void extractTeam(V model) throws RemoteException {
        
        if (model == null) return;
        
        this.id = model.getTeamID();
        this.name = model.getName();
        this.sport = (model.getSport() != null) ? new SportDTO(model.getSport()) : null;
        this.league = (model.getLeague() != null) ? new LeagueDTO(model.getLeague()) : null;
    }

    public String getName() throws RemoteException {
        return name;
    }

    public void setName(String name) throws RemoteException {
        this.name = name;
    }

    public ILeagueDTO getLeague()throws RemoteException  {
        return league;
    }

    public void setLeague(ILeagueDTO league)throws RemoteException  {
        this.league = league;
    }
    
    public ISportDTO getSport() throws RemoteException {
        return sport;
    }

    public void setSport(ISportDTO sport) throws RemoteException {
        this.sport = sport;
    }
    
    
//    public List<ITournamentDTO> getTournaments() {
//        return tournaments;
//    }
//
//    public void setTournaments(List<ITournamentDTO> tournaments) {
//        this.tournaments = tournaments;
//    }

    @Override
    public String toString() {
        try {
            return getName();
        } catch (RemoteException ex) {
            Logger.getLogger(AbstractTeamDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
