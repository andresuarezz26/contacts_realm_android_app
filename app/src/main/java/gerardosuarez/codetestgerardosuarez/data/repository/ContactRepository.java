package gerardosuarez.codetestgerardosuarez.data.repository;

import android.support.annotation.NonNull;

import java.util.List;

import gerardosuarez.codetestgerardosuarez.model.Contact;

public interface ContactRepository {

    List<Contact> getContacts();

    Contact getContactById(int id);

    void createContact(@NonNull Contact contact);

    void updateContact(@NonNull Contact contact);

    void deleteContactById(int contactId);

    List<Contact> getContactsByName(@NonNull String contactName);

}
