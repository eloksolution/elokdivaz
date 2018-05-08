package in.eloksolutions.beaty.dataobjects;

/**
 * Created by welcome on 2/24/2018.
 */

public class CustomerOBJ {
    String id;
    String firstName;
    String lastName;
    String email;
    String address_1;
    String address_2;
    String city;
    String state;
    String myParalors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMyParalors() {
        return myParalors;
    }

    public void setMyParalors(String myParalors) {
        this.myParalors = myParalors;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address_1='" + address_1 + '\'' +
                ", address_2='" + address_2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", myParalors='" + myParalors + '\'' +
                '}';
    }
}
