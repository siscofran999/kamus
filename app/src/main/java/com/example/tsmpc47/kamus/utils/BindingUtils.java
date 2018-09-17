package com.example.tsmpc47.kamus.utils;

import android.databinding.BindingAdapter;
import android.widget.ProgressBar;

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter("progress")
    public static void setProgressBar(ProgressBar view, int progress) {
        view.setProgress(progress);
    }
}
