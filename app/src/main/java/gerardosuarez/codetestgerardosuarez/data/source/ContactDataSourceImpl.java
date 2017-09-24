package gerardosuarez.codetestgerardosuarez.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import gerardosuarez.codetestgerardosuarez.data.entities.RContact;
import io.realm.Realm;
import io.realm.RealmResults;


public class ContactDataSourceImpl extends BaseDataSource<RContact> implements ContactDataSource {

    private static final int FIRST_ID = 1;

    public ContactDataSourceImpl(@NonNull Class<RContact> clazz) {
        super(clazz);
    }

    @Nullable
    @Override
    protected String getPrimaryKey() {
        return RContact.PRIMARY_KEY;
    }

    @Nullable
    @Override
    public RealmResults<RContact> getContacts(@NonNull Realm realm) {
        return realm.where(RContact.class)
                .findAll();
    }

    @Nullable
    @Override
    public RContact getContactById(@NonNull Realm realm, int id) {
        return realm.where(RContact.class).equalTo(RContact.PRIMARY_KEY, id).findFirst();
    }

    @Override
    public void createContact(@NonNull Realm realm, @NonNull RContact contact) {
        contact.setId(getId(realm));
        createOrUpdate(realm, contact);
    }

    @Override
    public void updateContact(@NonNull Realm realm, @NonNull RContact contact) {
        createOrUpdate(realm, contact);
    }

    @Override
    public void deleteContactById(@NonNull Realm realm, int contactId) {
        deleteById(realm, contactId);
    }

    @Override
    public RealmResults<RContact> getContactsByName(@NonNull Realm realm, @NonNull String contactName) {
        return realm.where(RContact.class).beginsWith(RContact.NAME, contactName).or().beginsWith(RContact.LASTNAME, contactName).findAll();
    }

    private int getId(Realm realm) {
        Number currentIdNum = realm.where(RContact.class).max(getPrimaryKey());
        int nextId;
        if (currentIdNum == null) {
            nextId = FIRST_ID;
        } else {
            nextId = currentIdNum.intValue() + FIRST_ID;
        }
        return nextId;
    }
}
