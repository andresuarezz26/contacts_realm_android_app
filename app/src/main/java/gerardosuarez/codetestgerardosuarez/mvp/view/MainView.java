package gerardosuarez.codetestgerardosuarez.mvp.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gerardosuarez.codetestgerardosuarez.R;
import gerardosuarez.codetestgerardosuarez.activity.AddContactActivity;
import gerardosuarez.codetestgerardosuarez.activity.DetailActivity;
import gerardosuarez.codetestgerardosuarez.activity.MainActivity;
import gerardosuarez.codetestgerardosuarez.adapter.ContactAdapter;
import gerardosuarez.codetestgerardosuarez.model.Contact;
import io.reactivex.observers.DisposableObserver;

public class MainView extends ActivityView<MainActivity> {

    private ContactAdapter adapter;

    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;

    public MainView(MainActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init(@NonNull DisposableObserver<Contact> observer) {
        if (getActivity() != null) {
            adapter = new ContactAdapter(getContext());
            adapter.suscribeToAdapter(observer);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
        }
    }

    public void unsuscribeToAdapter() {
        adapter.unsuscribeToAdapter();
    }

    public void addContact(Contact contact) {
        adapter.add(contact);
    }


    public void goToDetailPresenter(int contactId) {
        if (getActivity() != null) {
            getActivity().startActivity(DetailActivity.getIntent(getActivity(), contactId));
        }
    }

    public void goToAddContanctActivity() {
        if (getActivity() != null) {
            getActivity().startActivity(AddContactActivity.getIntent(getActivity()));
        }
    }

    public void addAll(List<Contact> list) {
        adapter.addAll(list);
    }

    public void removeAll() {
        adapter.removeAll();
    }
}
