package ph.dreambig.bigbenta.marketplacev2;

import android.content.Context;
import android.content.SharedPreferences;


public class UserInformation {

    private String FirstName;
    private String LastName;
    private String ProfilePicture;
    private String Email;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String NAME="Name";
    private static final String EMAIL="Email";
    private static final String LASTNAME="LastName";
    private static final String PROFILEPICTURE="ProfilePicture";
    private static final String PREF_NAME ="Login";
    int PRIVATE_MODE = 0;
    Context context;

    public void putData(String firstName,String lastName, String email,  String profilePicture){
        editor.putString(NAME,firstName);
        editor.putString(EMAIL,email);
        editor.putString(LASTNAME,lastName);
        editor.putString(PROFILEPICTURE,profilePicture);
        editor.commit();

    }



    public UserInformation(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public String getFirstName() {
        return sharedPreferences.getString(NAME,"");
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    public void updateFirstName(String firstName) {
        editor.putString(NAME, firstName).apply();
    }

    public void updateLastName(String lastName) {
        editor.putString(LASTNAME, lastName).apply();
    }

    public void updateEmail(String emaill) {
        editor.putString(EMAIL, emaill).apply();
    }


    public String getLastName() {
        return sharedPreferences.getString(LASTNAME,"");
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getProfilePicture() {
        return sharedPreferences.getString(PROFILEPICTURE,"");
    }

    public void setProfilePicture(String profilePicture) {
        editor.putString(PROFILEPICTURE,profilePicture);
    }

    public String getEmail() {
        return sharedPreferences.getString(EMAIL,"");
    }

    public void setEmail(String email) {
        Email = email;
    }
}
