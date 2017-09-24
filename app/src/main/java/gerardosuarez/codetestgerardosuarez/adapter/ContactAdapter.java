package gerardosuarez.codetestgerardosuarez.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gerardosuarez.codetestgerardosuarez.R;
import gerardosuarez.codetestgerardosuarez.model.Contact;
import gerardosuarez.codetestgerardosuarez.utils.CollectionUtils;
import gerardosuarez.codetestgerardosuarez.utils.StringUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;


public class ContactAdapter extends BaseAdapter<Contact, Pair<Contact, Integer>, ContactAdapter.ContactViewHolder> {

    public static final int FIRST_PHONE_NUMBER = 0;

    public ContactAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_contact;
    }

    @NonNull
    @Override
    protected ContactViewHolder getViewHolder(View view) {
        return new ContactViewHolder(view);
    }

    @Override
    public List<Contact> getItems() {
        return items;
    }


    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.contact = items.get(position);

        String name = holder.contact.getName();
        if (!StringUtils.isEmpty(name)) {
            holder.textName.setText(name);
        }
        String lastname = holder.contact.getLastname();
        if (!StringUtils.isEmpty(lastname)) {
            holder.textLastname.setText(lastname);
        }
        if (!CollectionUtils.isEmpty(holder.contact.getPhoneNumbers())) {
            String phone = holder.contact.getPhoneNumbers().get(FIRST_PHONE_NUMBER);
            if (!StringUtils.isEmpty(phone)) {
                holder.textPhone.setText(phone);
            }
        }


    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_contact_name)
        TextView textName;
        @BindView(R.id.txt_contact_lastname)
        TextView textLastname;
        @BindView(R.id.txt_contact_phone_number)
        TextView textPhone;

        private Contact contact;
        private WeakReference<Observer<Pair<Contact, Integer>>> observerRef;

        ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.cue_card_container)
        void onItemClicked() {
            if (publishSubject != null) {
                publishSubject.onNext(contact);
            }
        }

        @OnClick(R.id.btn_copy_phone)
        void onCopyButtonClick() {
            if (textPhone != null && getContext() != null) {
                String currentPhone = textPhone.getText().toString();
                if (!StringUtils.isEmpty(currentPhone)) {
                    ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Phone", textPhone.getText());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getContext(), "Phone copied " + textPhone.getText(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "There are not phone number", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
