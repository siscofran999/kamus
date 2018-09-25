package com.example.tsmpc47.kamus.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.tsmpc47.kamus.data.model.Word;
import com.example.tsmpc47.kamus.ui.main.MainAdapter;

import java.util.ArrayList;

public final class BindingUtils {

    private static final String TAG = "BindingUtils";

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter("progress")
    public static void setProgressBar(ProgressBar view, int progress) {
        view.setProgress(progress);
    }

    @BindingAdapter({"Adapter"})
    public static void addMainAdapter(RecyclerView recyclerView, ArrayList<Word> models) {
        MainAdapter adapter = (MainAdapter) recyclerView.getAdapter();
        if(adapter != null) {
            adapter.clearItems();
            adapter.addItems(models);
        }
    }
}
