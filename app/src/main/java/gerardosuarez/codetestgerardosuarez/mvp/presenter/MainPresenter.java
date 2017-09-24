package gerardosuarez.codetestgerardosuarez.mvp.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import gerardosuarez.codetestgerardosuarez.data.repository.ContactRepository;
import gerardosuarez.codetestgerardosuarez.model.Contact;
import gerardosuarez.codetestgerardosuarez.mvp.view.MainView;
import gerardosuarez.codetestgerardosuarez.utils.CollectionUtils;
import io.reactivex.observers.DisposableObserver;

public class MainPresenter {

    private ContactRepository repository;
    private MainView view;

    public MainPresenter(ContactRepository repository, MainView view) {
        this.repository = repository;
        this.view = view;
    }

    public void init() {
        view.init(new OnItemClickObserver());
    }

    private void goToDetailPresenter(int contactdId) {
        view.goToDetailPresenter(contactdId);
    }

    public void addContact() {
        view.goToAddContanctActivity();
    }

    public void reloadAdapter() {
        if (!CollectionUtils.isEmpty(repository.getContacts())) {
            view.addAll(repository.getContacts());
        } else {
            view.removeAll();
        }
    }

    public void searchContactsOcurriencies(@NonNull CharSequence charSequence) {
        List<Contact> contactsFromSearch = repository.getContactsByName(charSequence.toString());
        if (!CollectionUtils.isEmpty(contactsFromSearch)) {
            view.addAll(contactsFromSearch);
        } else {
            view.removeAll();
        }
    }

    private class OnItemClickObserver extends DisposableObserver<Contact> {

        @Override
        public void onNext(@Nullable Contact contact) {
            if (contact != null) {
                goToDetailPresenter(contact.getId());
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {
        }
    }

}
