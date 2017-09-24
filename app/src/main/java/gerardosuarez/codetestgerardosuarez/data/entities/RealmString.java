package gerardosuarez.codetestgerardosuarez.data.entities;

import io.realm.RealmObject;

public class RealmString extends RealmObject {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
