package utils;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * There is a class where I store user`s data and data which we get from the response
 */

@JacksonXmlRootElement(localName = "user")
public class UserInfo {
    public UserInfo() {
    }


    private final String TIME_FORMAT = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z";
    private String testPassword = "U*dJ1F*e4*";
    private String testLogIn = "automation_test@keepitqa.com";
    private String testId = "hbnbdm-sw0tha-0j8g61";


    public String getTIME_FORMAT() {
        return TIME_FORMAT;
    }

    public String getTestPassword() {
        return testPassword;
    }

    public String getTestLogIn() {
        return testLogIn;
    }

    public String getTestId() {
        return testId;
    }


    @JacksonXmlProperty(localName = "enabled")
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }


    @JacksonXmlProperty(localName = "created")
    private String created;

    public String getCreated() {
        return created;
    }

    @JacksonXmlProperty(localName = "product")
    private String product;

    public String getProduct() {
        return product;
    }


    @JacksonXmlProperty(localName = "id")
    private String id;

    public String getId() {
        return id;
    }

    @JacksonXmlProperty(localName = "parent")
    private String parent;


    public String getParent() {
        return parent;
    }

    @JacksonXmlProperty(localName = "subscribed")
    private boolean subscribed;

    public boolean isSubscribed() {
        return subscribed;
    }
}