package gerardosuarez.codetestgerardosuarez.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import gerardosuarez.codetestgerardosuarez.R;
import gerardosuarez.codetestgerardosuarez.data.entities.RContact;
import gerardosuarez.codetestgerardosuarez.data.mapper.RealmModelContactMapper;
import gerardosuarez.codetestgerardosuarez.data.repository.ContactRepository;
import gerardosuarez.codetestgerardosuarez.data.repository.ContactRepositoryImpl;
import gerardosuarez.codetestgerardosuarez.data.source.ContactDataSourceImpl;
import gerardosuarez.codetestgerardosuarez.mvp.presenter.MainPresenter;
import gerardosuarez.codetestgerardosuarez.mvp.view.MainView;

public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter;

    @BindView(R.id.edit_search)
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ContactRepository repository = new ContactRepositoryImpl(new ContactDataSourceImpl(RContact.class), new RealmModelContactMapper());
        presenter = new MainPresenter(repository, new MainView(this));
        presenter.init();
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    presenter.reloadAdapter();
                } else {
                    presenter.searchContactsOcurriencies(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.reloadAdapter();
    }

    @OnClick(R.id.btn_add_contact)
    void addContact() {
        presenter.addContact();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.reloadAdapter();
    }


}
