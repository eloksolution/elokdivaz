package in.eloksolutions.beaty.dataobjecs;

/**
 * Created by welcome on 3/1/2018.
 */

public class ContactUsDataObjects {
    private String ownerPhone;
    private String email;
    private String whatsapp;

    public ContactUsDataObjects(String ownerPhone, String email, String whatsapp) {
        this.ownerPhone = ownerPhone;
        this.email = email;
        this.whatsapp = whatsapp;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    @Override
    public String toString() {
        return "ContactUsDTO{" +
                "ownerPhone='" + ownerPhone + '\'' +
                ", email='" + email + '\'' +
                ", whatsapp='" + whatsapp + '\'' +
                '}';
    }
}
