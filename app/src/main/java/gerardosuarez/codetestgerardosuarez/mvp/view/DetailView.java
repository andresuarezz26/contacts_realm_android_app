package gerardosuarez.codetestgerardosuarez.mvp.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gerardosuarez.codetestgerardosuarez.R;
import gerardosuarez.codetestgerardosuarez.activity.DetailActivity;
import gerardosuarez.codetestgerardosuarez.dialogfragment.DatePickerFragment;
import gerardosuarez.codetestgerardosuarez.model.Contact;
import gerardosuarez.codetestgerardosuarez.utils.CollectionUtils;
import gerardosuarez.codetestgerardosuarez.utils.StringUtils;
import io.reactivex.observers.DisposableObserver;

public class DetailView extends ActivityView<DetailActivity> {

    private static final int FIST_ITEM = 0;
    private static final String DATE_PICKER = "Date";
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
    Button buttonEditAndSave;
    @BindView(R.id.btn_delete)
    Button buttonDelete;
    @BindView(R.id.btn_change_date)
    Button buttonChangeDate;
    @BindView(R.id.txt_date_birth)
    TextView textBirthday;

    private DatePickerFragment datePickerFragment;

    public DetailView(DetailActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void setContactData(@NonNull Contact currentContact) {
        contactName.setText(StringUtils.changeNullByEmptyString(currentContact.getName()));
        contactLastName.setText(StringUtils.changeNullByEmptyString(currentContact.getLastname()));
        textBirthday.setText(StringUtils.changeNullByEmptyString(currentContact.getBirthDate()));
        contactAddress.setText(getCollectionFirstItemText(currentContact.getAddresses(), currentContact));
        contactPhone.setText(getCollectionFirstItemText(currentContact.getPhoneNumbers(), currentContact));
        contactEmail.setText(getCollectionFirstItemText(currentContact.getEmail(), currentContact));
        setEnabled(false);
    }

    public void setEnabled(boolean enabled) {
        contactName.setEnabled(enabled);
        contactLastName.setEnabled(enabled);
        textBirthday.setEnabled(enabled);
        contactAddress.setEnabled(enabled);
        contactPhone.setEnabled(enabled);
        contactEmail.setEnabled(enabled);
        buttonChangeDate.setEnabled(enabled);
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


    private String getCollectionFirstItemText(List list, Contact currentContact) {
        return !CollectionUtils.isEmpty(list) ?
                list.get(FIST_ITEM).toString() :
                StringUtils.EMPTY_STRING;
    }

    public void finishActivity() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    public void setButtonText(@StringRes int resId) {
        buttonEditAndSave.setText(resId);
    }

    public void showMessage(@StringRes int restId) {
        if (getContext() != null) {
            Toast.makeText(getContext(), restId, Toast.LENGTH_LONG).show();
        }
    }

    public void setBirthDay(@NonNull String birthDay) {
        textBirthday.setText(birthDay);
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
}
