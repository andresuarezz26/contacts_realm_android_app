package gerardosuarez.codetestgerardosuarez.utils;

import android.support.annotation.Nullable;

import java.util.Collection;
import java.util.Collections;

public final class CollectionUtils {

    public static boolean isEmpty(@Nullable Collection collections) {
        return collections == null || collections.isEmpty();
    }
}
