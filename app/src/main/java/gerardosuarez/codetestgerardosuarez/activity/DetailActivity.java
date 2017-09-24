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
import gerardosuarez.codetestgerardosuarez.mvp.presenter.DetailPresenter;
import gerardosuarez.codetestgerardosuarez.mvp.view.DetailView;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_CONTACT_ID = "EXTRA_CONTACT_ID";
    private DetailPresenter presenter;

    public static Intent getIntent(final AppCompatActivity activity, int contactId) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(EXTRA_CONTACT_ID, contactId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent == null || !intent.hasExtra(EXTRA_CONTACT_ID)) {
            return;
        }
        ContactRepository repository = new ContactRepositoryImpl(new ContactDataSourceImpl(RContact.class), new RealmModelContactMapper());
        presenter = new DetailPresenter(repository, new DetailView(this), intent.getIntExtra(EXTRA_CONTACT_ID, -1));
        presenter.init();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @OnClick(R.id.btn_delete)
    void deleteContact() {
        presenter.deleteContact();
    }

    @OnClick(R.id.btn_edit_and_save)
    void onEditOrSaveClick() {
        presenter.onEditOrSaveClick();
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
