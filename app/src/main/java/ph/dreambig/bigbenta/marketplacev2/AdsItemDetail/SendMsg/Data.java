package ph.dreambig.bigbenta.marketplacev2.AdsItemDetail.SendMsg;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

@SerializedName("message")
@Expose
private String message;

    @SerializedName("contact_number")
    @Expose
    private String contactNumber;



    @SerializedName("email_sender")
    @Expose
    private String emailSender;
/**
* 
* @return
* The message
*/

public String getEmailSender() {
    return emailSender;
}

    /**
     *
     * @param emailSender
     * The email_sender
     */
    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
    }
public String getMessage() {
return message;
}

/**
* 
* @param message
* The message
*/
public void setMessage(String message) {
this.message = message;
}

    public String getContactNumber() {
        return contactNumber;
    }

    /**
     *
     * @param contactNumber
     * The contact_number
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


}