package gerardosuarez.codetestgerardosuarez.data.entities;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RContact extends RealmObject {

    public static final String PRIMARY_KEY = "id";
    public static final String NAME = "name";
    public static final String LASTNAME = "lastname";

    @PrimaryKey
    private int id;

    private String name;

    private String lastname;

    private String birthDate;

    private RealmList<RealmString> addresses;

    private RealmList<RealmString> phoneNumbers;

    private RealmList<RealmString> email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public RealmList<RealmString> getAddresses() {
        return addresses;
    }

    public void setAddresses(RealmList<RealmString> addresses) {
        this.addresses = addresses;
    }

    public RealmList<RealmString> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(RealmList<RealmString> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public RealmList<RealmString> getEmail() {
        return email;
    }

    public void setEmail(RealmList<RealmString> email) {
        this.email = email;
    }
}
