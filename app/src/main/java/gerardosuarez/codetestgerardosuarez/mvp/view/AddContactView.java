package gerardosuarez.codetestgerardosuarez.mvp.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gerardosuarez.codetestgerardosuarez.R;
import gerardosuarez.codetestgerardosuarez.activity.AddContactActivity;
import gerardosuarez.codetestgerardosuarez.dialogfragment.DatePickerFragment;
import gerardosuarez.codetestgerardosuarez.model.Contact;
import io.reactivex.observers.DisposableObserver;

public class AddContactView extends ActivityView<AddContactActivity> {

    private static final String DATE_PICKER = "datePicker";

    @BindView(R.id.edit_contact_lastname)
    EditText contactLastName;
    @BindView(R.id.edit_contact_name)
    EditText contactName;
    @BindView(R.id.edit_contact_address)
    EditText contactAddress;
    @BindView(R.id.edit_contact_phone)
    EditText contactPhone;
    @BindView(R.id.edit_contact_email)
    EditText contactEmail;
    @BindView(R.id.btn_edit_and_save)
    Button buttonSave;
    @BindView(R.id.btn_delete)
    Button buttonDelete;
    @BindView(R.id.txt_date_birth)
    TextView textBirthday;

    private DatePickerFragment datePickerFragment;


    public AddContactView(AddContactActivity addContactActivity) {
        super(addContactActivity);
        ButterKnife.bind(this, addContactActivity);
    }

    @NonNull
    public Contact createContactFromViews() {
        Contact contact = new Contact();
        contact.setName(contactName.getText().toString());
        contact.setLastname(contactLastName.getText().toString());
        contact.setBirthDate(textBirthday.getText().toString());

        List<String> email = new ArrayList<>();
        email.add(contactEmail.getText().toString());
        contact.setEmail(email);

        List<String> address = new ArrayList<>();
        address.add(contactAddress.getText().toString());
        contact.setAddresses(address);

        List<String> phone = new ArrayList<>();
        phone.add(contactPhone.getText().toString());
        contact.setPhoneNumbers(phone);

        return contact;
    }

    public void setBirthDay(String birthDay) {
        textBirthday.setText(birthDay);
    }

    public void setButtonName() {
        buttonSave.setText(R.string.create_contact);
    }

    public void hideButtonDelete() {
        buttonDelete.setVisibility(View.INVISIBLE);
    }

    public void showDatePickerFragment(DisposableObserver<String> observer) {
        if (getActivity() == null) return;
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.subscribeToDialogFragment(observer);
        datePickerFragment.show(getActivity().getFragmentManager(), DATE_PICKER);
    }

    public void unsubscribeListeners() {
        if (datePickerFragment != null) {
            datePickerFragment.unsubscribeToDialogFragment();
            datePickerFragment.dismiss();
        }
    }


    public void finishActivity() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

}
