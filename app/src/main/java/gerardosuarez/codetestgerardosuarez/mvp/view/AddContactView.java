package gerardosuarez.codetestgerardosuarez.mvp.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gerardosuarez.codetestgerardosuarez.R;
import gerardosuarez.codetestgerardosuarez.activity.AddContactActivity;
import gerardosuarez.codetestgerardosuarez.dialogfragment.DatePickerFragment;
import gerardosuarez.codetestgerardosuarez.model.Contact;
import gerardosuarez.codetestgerardosuarez.utils.StringUtils;
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


    public AddContactView(@NonNull AddContactActivity addContactActivity) {
        super(addContactActivity);
        ButterKnife.bind(this, addContactActivity);
    }

    @Nullable
    public Contact createContactFromViews() {
        Contact contact = null;
        String contactNameText = contactName.getText().toString();
        String contactLastNameText = contactLastName.getText().toString();
        String contactPhonenumber = contactPhone.getText().toString();
        if (!StringUtils.isEmpty(contactNameText) &&
                !StringUtils.isEmpty(contactLastNameText) &&
                !StringUtils.isEmpty(contactPhonenumber)
                ) {
            contact = new Contact();
            contact.setName(contactNameText);
            contact.setLastname(contactLastNameText);
            contact.setBirthDate(textBirthday.getText().toString());

            List<String> email = new ArrayList<>();
            email.add(contactEmail.getText().toString());
            contact.setEmail(email);

            List<String> address = new ArrayList<>();
            address.add(contactAddress.getText().toString());
            contact.setAddresses(address);

            List<String> phone = new ArrayList<>();
            phone.add(contactPhonenumber);
            contact.setPhoneNumbers(phone);
        }

        return contact;
    }

    public void setBirthDay(@NonNull String birthDay) {
        textBirthday.setText(birthDay);
    }

    public void setButtonName() {
        buttonSave.setText(R.string.create_contact);
    }

    public void hideButtonDelete() {
        buttonDelete.setVisibility(View.INVISIBLE);
    }

    public void showDatePickerFragment(@NonNull DisposableObserver<String> observer) {
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

    public void showToast(@StringRes int resId) {
        Toast.makeText(getContext(), resId, Toast.LENGTH_LONG).show();
    }
}
