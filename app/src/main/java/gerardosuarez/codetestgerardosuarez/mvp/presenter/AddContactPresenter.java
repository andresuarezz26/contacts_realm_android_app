package gerardosuarez.codetestgerardosuarez.mvp.presenter;

import android.support.annotation.NonNull;

import gerardosuarez.codetestgerardosuarez.R;
import gerardosuarez.codetestgerardosuarez.data.repository.ContactRepository;
import gerardosuarez.codetestgerardosuarez.model.Contact;
import gerardosuarez.codetestgerardosuarez.mvp.view.AddContactView;
import gerardosuarez.codetestgerardosuarez.utils.StringUtils;
import io.reactivex.observers.DisposableObserver;

public class AddContactPresenter {

    private AddContactView view;
    private ContactRepository repository;

    public AddContactPresenter(ContactRepository repository, AddContactView view) {
        this.repository = repository;
        this.view = view;
    }

    public void createNewContact() {
        Contact contact = view.createContactFromViews();
        if (contact != null) {
            repository.createContact(contact);
            view.finishActivity();
        } else {
            view.showToast(R.string.fields_not_null);
        }
    }

    public void init() {
        view.setButtonName();
        view.hideButtonDelete();
    }

    public void showDatePicker() {
        view.showDatePickerFragment(new OnDateSelected());
    }

    private void setDateBirthText(@NonNull String date) {
        view.setBirthDay(StringUtils.formatDateWithTodayLogic(date));
    }

    public void unsubscribeListeners() {
        view.unsubscribeListeners();
    }

    private class OnDateSelected extends DisposableObserver<String> {


        @Override
        public void onNext(String date) {
            if (date != null) {
                setDateBirthText(date);
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
