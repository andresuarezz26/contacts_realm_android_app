package gerardosuarez.codetestgerardosuarez.mvp.view;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

public class ActivityView<T> {

    private WeakReference<T> activityRef;

    protected ActivityView(T activity) {
        activityRef = new WeakReference<T>(activity);
    }

    @Nullable
    public T getActivity() {
        return activityRef.get();
    }

    @Nullable
    public Context getContext() {
        return (Context) getActivity();
    }

    public Resources getResources() {
        return getContext().getResources();
    }
}
