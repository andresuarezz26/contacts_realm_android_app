package gerardosuarez.codetestgerardosuarez;

import android.app.Application;
import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by gerardosuarez on 23/09/17.
 */

public class CodeTestApp extends Application {

    private static final int REALM_SCHEMA_VERSION = 1;
    private static CodeTestApp instance;

    @NonNull
    public static CodeTestApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Realm.init(instance);
        setRealmConfiguration();
    }

    private void setRealmConfiguration() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .schemaVersion(REALM_SCHEMA_VERSION)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
