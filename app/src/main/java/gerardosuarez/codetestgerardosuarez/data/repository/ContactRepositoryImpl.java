package gerardosuarez.codetestgerardosuarez.data.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import gerardosuarez.codetestgerardosuarez.data.entities.RContact;
import gerardosuarez.codetestgerardosuarez.data.mapper.RealmModelContactMapper;
import gerardosuarez.codetestgerardosuarez.model.Contact;
import gerardosuarez.codetestgerardosuarez.data.source.ContactDataSource;
import io.realm.Realm;
import io.realm.RealmResults;

/*
Repository that manages differents source of gerardosuarez.codetestgerardosuarez.data (Services, localDb, SharedPreferences or Files)
In this app there are only the local database source
 */
public class ContactRepositoryImpl implements ContactRepository {

    private ContactDataSource dataSource;
    private RealmModelContactMapper mapper;

    public ContactRepositoryImpl(@NonNull final ContactDataSource dataSource, @NonNull final RealmModelContactMapper mapper) {
        this.dataSource = dataSource;
        this.mapper = mapper;
    }

    @Nullable
    @Override
    public List<Contact> getContacts() {
        List<Contact> list;
        try (Realm realm = Realm.getDefaultInstance()) {
            list = new ArrayList<>();
            RealmResults<RContact> realmContactList = dataSource.getContacts(realm);
            transformRealResultsToList(realmContactList, list);
        }
        return list;
    }

    @Nullable
    @Override
    public Contact getContactById(final int id) {
        Contact contact;
        try (Realm realm = Realm.getDefaultInstance()) {
            contact = mapper.rContactToContact(dataSource.getContactById(realm, id));
        }
        return contact;
    }

    @Override
    public void createContact(@NonNull final Contact contact) {
        try (Realm realm = Realm.getDefaultInstance()) {
            dataSource.createContact(realm, mapper.contactToRContact(contact));
        }
    }

    @Override
    public void updateContact(@NonNull Contact contact) {
        try (Realm realm = Realm.getDefaultInstance()) {
            dataSource.updateContact(realm, mapper.contactToRContact(contact));
        }
    }

    @Override
    public void deleteContactById(int contactId) {
        try (Realm realm = Realm.getDefaultInstance()) {
            dataSource.deleteContactById(realm, contactId);
        }
    }

    @Nullable
    @Override
    public List<Contact> getContactsByName(@NonNull final String contactName) {
        List<Contact> list;
        try (Realm realm = Realm.getDefaultInstance()) {
            list = new ArrayList<>();
            RealmResults<RContact> realmContactList = dataSource.getContactsByName(realm, contactName);
            transformRealResultsToList(realmContactList, list);
        }
        return list;
    }

    //Common methods
    @Nullable
    private List<Contact> transformRealResultsToList(@Nullable final RealmResults<RContact> realmContactList, @NonNull List<Contact> list) {
        if (realmContactList != null) {
            for (RContact rContact : realmContactList) {
                list.add(mapper.rContactToContact(rContact));
            }
        }
        return list;
    }
}
