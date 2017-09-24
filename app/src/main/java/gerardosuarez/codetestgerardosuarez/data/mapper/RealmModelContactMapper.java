package gerardosuarez.codetestgerardosuarez.data.mapper;

import android.support.annotation.Nullable;

import gerardosuarez.codetestgerardosuarez.data.entities.RContact;
import gerardosuarez.codetestgerardosuarez.model.Contact;
import gerardosuarez.codetestgerardosuarez.utils.StringUtils;

public class RealmModelContactMapper extends BaseRealmModelMapper {

    @Nullable
    public Contact rContactToContact(@Nullable RContact input) {
        Contact contact = null;
        if (input != null) {
            contact = new Contact();
            contact.setName(StringUtils.changeNullByEmptyString(input.getName()));
            contact.setLastname(StringUtils.changeNullByEmptyString(input.getLastname()));
            contact.setBirthDate(StringUtils.changeNullByEmptyString(input.getBirthDate()));
            contact.setId(input.getId());
            contact.setAddresses(realmListToList(input.getAddresses()));
            contact.setPhoneNumbers(realmListToList(input.getPhoneNumbers()));
            contact.setEmail(realmListToList(input.getEmail()));
        }
        return contact;
    }

    @Nullable
    public RContact contactToRContact(@Nullable Contact input) {
        RContact contact = null;
        if (input != null) {
            contact = new RContact();
            contact.setName(StringUtils.changeNullByEmptyString(input.getName()));
            contact.setLastname(StringUtils.changeNullByEmptyString(input.getLastname()));
            contact.setBirthDate(StringUtils.changeNullByEmptyString(input.getBirthDate()));
            contact.setId(input.getId());
            contact.setAddresses(listToRealmList(input.getAddresses()));
            contact.setPhoneNumbers(listToRealmList(input.getPhoneNumbers()));
            contact.setEmail(listToRealmList(input.getEmail()));
        }
        return contact;
    }

}
