/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.JMS;

import business.AController;
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
import java.util.Properties;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
import javax.naming.*;

/**
 *
 * @author Evgeniya Spiegel
 */
@MessageDriven(mappedName = "jms/mdb", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageController extends AController implements IMessageController, MessageListener {

    private InitialContext initialContext;
    private ConnectionFactory connectionFactory;
    private Destination destination;
    private Session session;
    private Connection connection;
    private static MessageController instance;
    private  LinkedList<IMessage> messages;

    private MessageController() throws RemoteException {
        super();
        messages = new LinkedList<IMessage>();
        Properties props = new Properties();

        props.setProperty("java.naming.factory.initial",
                "com.sun.enterprise.naming.SerialInitContextFactory");

        props.setProperty("java.naming.factory.url.pkgs",
                "com.sun.enterprise.naming");

        props.setProperty("java.naming.factory.state",
                "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        try {
            initialContext = new InitialContext(props);
            connectionFactory = (ConnectionFactory) initialContext.lookup("jms/CF");
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.close();
        } catch (Exception e) {
        }



    }

    public static MessageController getInstance() throws RemoteException, Exception {
        if (instance == null) {
            instance = new MessageController();
        }
        return instance;
    }

    @Override
    public void createQueue(String username) throws RemoteException, Exception {

        Queue queue = session.createQueue("jms/" + username);
        initialContext.bind("jms/" + username, queue);
    }
    //Consumer is a sportsman with username username, and he(she)should get the loaded messages  

    @Override
    public List<IMessage> LoadMessages(String username) throws RemoteException, Exception {

        List<IMessage> userMessages = new LinkedList<IMessage>();
        for(IMessage msg:messages){
            if(msg.getReceiver().equals(username)){
                userMessages.add(msg);
            }
        }
        return userMessages;
//        connection = connectionFactory.createConnection();
//        connection.start();
//        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//
//
//
//        Destination dest = (Destination) initialContext.lookup("jms/" + username);
//        LinkedList<IMessage> messages = new LinkedList<IMessage>();
//
//        // Create a MessageConsumer
//        MessageConsumer consumer = session.createConsumer(dest);
//        // Start the connection, causing message delivery to begin
//        // Receive the messages sent to the destination
//
//
//        ObjectMessage msg = (ObjectMessage) consumer.receive(1000l);
//        while (msg != null) {
//            messages.add((IMessage) msg.getObject());
//            msg = (ObjectMessage) consumer.receive(1000l);
//        }
//
//        connection.close();
//        return messages;
    }
    //tinvite has a list of persons to invite

    @Override
    public boolean createInviteMessage(ITournamentInviteDTO tinvite) throws RemoteException {
        ISportsmanDTO sportsman = tinvite.getSportsman();
        ITournamentDTO tournament = tinvite.getTournament();
        ITrainingTeamDTO team = tinvite.getTeam();
        IMessage inviteMessage = new TournamentInviteMessage(sportsman, tournament, team);
        saveMessage(inviteMessage, sportsman.getPerson().getUsername());
        return true;
    }

    @Override
    public boolean createSportsmanCreatedMessage(List<String> usernames, ISportsmanDTO sportsman) throws RemoteException {

        SportsmanCreatedMessage sportsmanCreateMessage = new SportsmanCreatedMessage(sportsman);
        for (String un : usernames) {
            saveMessage(sportsmanCreateMessage, un);
        }
        return true;
    }

    @Override
    public boolean createSportsmanAssignedMessage(List<String> usernames, ISportsmanDTO sportsman, ITrainingTeamDTO team) throws RemoteException {
        SportsmanAssignedMessage sportsmanAssignedMessage = new SportsmanAssignedMessage(team, sportsman);
        for (String un : usernames) {
            saveMessage(sportsmanAssignedMessage, un);
        }

        return true;
    }

    private void saveMessage(IMessage o, String username) throws RemoteException {
        try {
            
            o.setReceiver(username);//MDB
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = (Destination) initialContext.lookup("jms/queue");//MDB
            //destination = (Destination) initialContext.lookup("jms/" + username);
            MessageProducer producer = session.createProducer(destination);
            ObjectMessage msg = session.createObjectMessage(o);
            producer.send(msg);
            connection.close();

        } catch (Exception e) {
        }
    }

    @Override
    public void onMessage(Message message) {
        messages.add((IMessage)message);
    }
}
