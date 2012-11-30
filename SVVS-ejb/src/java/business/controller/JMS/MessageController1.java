/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business.controller.JMS;

import business.messages.jms.SportsmanAssignedMessage;
import business.messages.jms.SportsmanCreatedMessage;
import business.messages.jms.TournamentInviteMessage;
import business.messages.jms.interfaces.IMessage;
import data.DTOs.ISportsmanDTO;
import data.DTOs.ITournamentDTO;
import data.DTOs.ITournamentInviteDTO;
import data.DTOs.ITrainingTeamDTO;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.*;
import javax.jms.*;

/**
 *
 * @author Evgeniya Spiegel
 */
public class MessageController1 {

    private InitialContext initialContext;
    private ConnectionFactory connectionFactory;
    private Destination destination;
    private Session session;
    private Connection connection;
    private static MessageController1 instance;

    private MessageController1() throws Exception {
        initialContext = new InitialContext();
        connectionFactory = (ConnectionFactory) initialContext.lookup("jms/ConnectionFactory");
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

    }

    public MessageController1 getInstance() throws Exception {
        if (instance == null) {
            instance = new MessageController1();
        }
        return instance;
    }

    public void createQueue(String username) throws Exception {
        Queue queue = session.createQueue("jms/"+username);
        initialContext.bind("jms/"+username, queue);
    }
    //Consumer is a sportsman with username username, and he(she)should get the loaded messages  

    public List<IMessage> LoadMessages(String username) throws Exception {
        Destination dest = (Destination) initialContext.lookup("jms/" + username);
        LinkedList<IMessage> messages = new LinkedList<IMessage>();


        // Create a MessageConsumer
        MessageConsumer consumer = session.createConsumer(dest);
// Start the connection, causing message delivery to begin
        connection.start();
// Receive the messages sent to the destination


        ObjectMessage msg = (ObjectMessage) consumer.receiveNoWait();
        while (msg != null) {
            messages.add((IMessage) msg.getObject());
            msg = (ObjectMessage) consumer.receiveNoWait();
        }
// Close the connection
        connection.close();
        System.out.println("Connection closed");

        return messages;
    }
    //tinvite has a list of persons to invite

    public boolean createInviteMessage(ITournamentInviteDTO tinvite) throws RemoteException {
        ISportsmanDTO sportsman = tinvite.getSportsman();
        ITournamentDTO tournament = tinvite.getTournament();
        ITrainingTeamDTO team = tinvite.getTeam();
        IMessage inviteMessage = new TournamentInviteMessage(sportsman, tournament, team);
        saveMessage(inviteMessage, sportsman.getPerson().getUsername());
        return true;
    }

    public boolean createSportsmanCreatedMessage(List<String> usernames, ISportsmanDTO sportsman) {
        SportsmanCreatedMessage sportsmanCreateMessage = new SportsmanCreatedMessage(sportsman);
        for (String un : usernames) {
            saveMessage(sportsmanCreateMessage, un);
        }
        return true;
    }

    public boolean createSportsmanAssignedMessage(List<String> usernames, ISportsmanDTO sportsman, ITrainingTeamDTO team) {
        SportsmanAssignedMessage sportsmanAssignedMessage = new SportsmanAssignedMessage(team, sportsman);
        for (String un : usernames) {
            saveMessage(sportsmanAssignedMessage, un);
        }

        return true;
    }

    private void saveMessage(IMessage o, String username) {
        try {
// get the Destination used to send the message by JNDI name
            destination = (Destination) initialContext.lookup("jms/" + username);
// Create a connection
            MessageProducer producer = session.createProducer(destination);
            ObjectMessage msg = session.createObjectMessage(o);
// send the message to the destination
            producer.send(msg);
// Close the connection
          // connection.close();
           // System.out.println("Connection closed");
        } catch (Exception e) {
        }
    }
}
