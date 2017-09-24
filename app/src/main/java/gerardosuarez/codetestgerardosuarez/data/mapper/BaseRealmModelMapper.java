package gerardosuarez.codetestgerardosuarez.data.mapper;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import gerardosuarez.codetestgerardosuarez.data.entities.RealmString;
import io.realm.RealmList;

class BaseRealmModelMapper {

    @Nullable
    List<String> realmListToList(@Nullable RealmList<RealmString> input) {
        List<String> list = null;
        if (input != null) {
            list = new ArrayList<>();
            for (int i = 0; i < input.size(); i++) {
                String temp = (input.get(i)).getText();
                list.add(temp);
            }
        }
        return list;
    }

    @Nullable
    RealmList<RealmString> listToRealmList(@Nullable List<String> input) {
        RealmList<RealmString> list = null;
        if (input != null) {
            list = new RealmList<>();
            for (int i = 0; i < input.size(); i++) {
                if (input.get(i) != null) {
                    RealmString temp = new RealmString();
                    temp.setText(input.get(i));
                    input.get(i);
                    list.add(temp);
                }
            }
        }
        return list;
    }
}
