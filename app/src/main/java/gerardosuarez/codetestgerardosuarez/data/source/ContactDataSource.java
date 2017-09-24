package gerardosuarez.codetestgerardosuarez.data.source;

import android.support.annotation.NonNull;

import gerardosuarez.codetestgerardosuarez.data.entities.RContact;
import gerardosuarez.codetestgerardosuarez.model.Contact;
import io.realm.Realm;
import io.realm.RealmResults;

public interface ContactDataSource {

    RealmResults<RContact> getContacts(@NonNull Realm realm);

    RContact getContactById(@NonNull Realm realm, int id);

    void createContact(@NonNull Realm realm, @NonNull RContact contact);

    void updateContact(@NonNull Realm realm, @NonNull RContact contact);

    void deleteContactById(@NonNull Realm realm, int contactId);

    RealmResults<RContact> getContactsByName(@NonNull Realm realm, @NonNull String contactName);
}
