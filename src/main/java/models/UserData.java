package models;
 //This class acts as a blueprint matching your JSON structure
public class UserData {

	// These variable names must match the keys in your JSON file exactly
    public UserProfile validUser;
    public InvalidProfile invalidUser;
    public UserCredentials userCredentials;

    public static class UserProfile {
        public String name, password, day, month, year;
        public String firstName, lastName, company, address, country, state, city, zipcode, mobile;
    }

    public static class InvalidProfile {
        public String email, password;
    }
    
    public static class UserCredentials {
        public String email, password;
    }
}
