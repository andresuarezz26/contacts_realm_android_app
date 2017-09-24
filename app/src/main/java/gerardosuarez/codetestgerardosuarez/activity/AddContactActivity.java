package gerardosuarez.codetestgerardosuarez.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import gerardosuarez.codetestgerardosuarez.R;
import gerardosuarez.codetestgerardosuarez.data.entities.RContact;
import gerardosuarez.codetestgerardosuarez.data.mapper.RealmModelContactMapper;
import gerardosuarez.codetestgerardosuarez.data.repository.ContactRepository;
import gerardosuarez.codetestgerardosuarez.data.repository.ContactRepositoryImpl;
import gerardosuarez.codetestgerardosuarez.data.source.ContactDataSourceImpl;
import gerardosuarez.codetestgerardosuarez.mvp.presenter.AddContactPresenter;
import gerardosuarez.codetestgerardosuarez.mvp.view.AddContactView;


public class AddContactActivity extends AppCompatActivity {

    public static Intent getIntent(final AppCompatActivity activity) {
        return new Intent(activity, AddContactActivity.class);
    }

    private AddContactPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        ButterKnife.bind(this);
        ContactRepository repository = new ContactRepositoryImpl(new ContactDataSourceImpl(RContact.class), new RealmModelContactMapper());
        presenter = new AddContactPresenter(repository, new AddContactView(this));
        presenter.init();
    }

    @OnClick(R.id.btn_edit_and_save)
    void saveNewContact() {
        presenter.createNewContact();
    }

    @OnClick(R.id.btn_change_date)
    void changeDate() {
        presenter.showDatePicker();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribeListeners();
    }
}
