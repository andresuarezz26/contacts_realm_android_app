package gerardosuarez.codetestgerardosuarez.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmObject;

public abstract class BaseDataSource<T extends RealmObject> {
    private Class<T> clazz;

    BaseDataSource(@NonNull Class<T> clazz) {
        this.clazz = clazz;
    }

    public void createOrUpdate(@NonNull Realm realmInstance, @NonNull final T input) {
        realmInstance.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                long start = System.currentTimeMillis();
                realm.copyToRealmOrUpdate(input);
                Log.d("Realm insertion time", clazz.getSimpleName() + " Insertion time: " + (System.currentTimeMillis() - start) + " ms");
            }

        });
    }

    public void deleteById(@NonNull Realm realmInstance, final int id) {
        if (getPrimaryKey() == null) {
            throw new UnsupportedOperationException("Not supported for repositories with null primary key");
        } else {
            realmInstance.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    T object = realm
                            .where(clazz)
                            .equalTo(getPrimaryKey(), id)
                            .findFirst();
                    if (object != null) {
                        object.deleteFromRealm();
                    }
                }
            });
        }
    }

    @Nullable
    protected abstract String getPrimaryKey();
}

