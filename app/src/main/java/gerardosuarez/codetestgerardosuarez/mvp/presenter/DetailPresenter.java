package gerardosuarez.codetestgerardosuarez.mvp.presenter;

import android.support.annotation.NonNull;

import gerardosuarez.codetestgerardosuarez.R;
import gerardosuarez.codetestgerardosuarez.data.repository.ContactRepository;
import gerardosuarez.codetestgerardosuarez.model.Contact;
import gerardosuarez.codetestgerardosuarez.mvp.view.DetailView;
import gerardosuarez.codetestgerardosuarez.utils.StringUtils;
import io.reactivex.observers.DisposableObserver;

public class DetailPresenter {

    private DetailView view;
    private ContactRepository repository;
    private int contactId;
    private Contact currentContact;
    private boolean isEditable = false;
    private boolean isFirstTime = true;

    public DetailPresenter(ContactRepository repository, DetailView view, int contactId) {
        this.repository = repository;
        this.view = view;
        this.contactId = contactId;
    }

    public void init() {
        currentContact = repository.getContactById(contactId);
        if (currentContact != null) {
            view.setContactData(currentContact);
        }
    }

    public void onEditOrSaveClick() {
        isFirstTime = false;
        isEditable = !isEditable;
        view.setEnabled(isEditable);
        view.setButtonText(isEditable ? R.string.save : R.string.edit);
        decideIfCreateContact();
    }

    private void decideIfCreateContact() {
        if (!isFirstTime && !isEditable) {
            currentContact = view.createContactFromViews();
            if (currentContact != null) {
                addContactAndShowSuccess();
            } else {
                view.showMessage(R.string.fields_not_null);
            }

        }
    }

    private void addContactAndShowSuccess() {
        currentContact.setId(contactId);
        repository.updateContact(currentContact);
        view.showMessage(R.string.created_contact_message);
    }

    public void deleteContact() {
        repository.deleteContactById(contactId);
        view.finishActivity();
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
