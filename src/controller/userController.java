package controller;

import java.util.HashMap;
import model.User;

public class userController {

    private static final HashMap<String, User> USERS = new HashMap();

    public static User updateUser(String[] recievedMessage, String user) {
        User u = new User(recievedMessage[0], //key
                recievedMessage[1], //name
                recievedMessage[2], //age
                recievedMessage[3], //sex
                recievedMessage[4], //temperature
                recievedMessage[5], //breathingRate
                recievedMessage[6], //heartRate
                recievedMessage[7], //oxygenSaturation
                recievedMessage[8], //systolicBloodPressure
                recievedMessage[9], //diastolicBloodPressure
                recievedMessage[10],//diastolicBloodPressure
                recievedMessage[11] //diastolicBloodPressure
        );
        USERS.put(user, u);
        return USERS.get(user);
    }
    
    public static boolean containsUser(String userKey){
        return USERS.containsKey(userKey);
    }

    public static User getUser(String user) {
        return USERS.get(user);
    }

    public static HashMap<String, User> getUSERS() {
        return USERS;
    }

}
