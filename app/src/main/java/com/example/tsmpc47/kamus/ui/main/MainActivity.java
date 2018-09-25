package com.example.tsmpc47.kamus.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.tsmpc47.kamus.BR;
import com.example.tsmpc47.kamus.R;
import com.example.tsmpc47.kamus.data.model.Word;
import com.example.tsmpc47.kamus.databinding.ActivityMainBinding;
import com.example.tsmpc47.kamus.ui.base.BaseActivity;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.functions.Predicate;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator, MainAdapter.ItemResultClickListener {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    MainAdapter mMainAdapter;

    private MainViewModel mMainViewModel;

    private ActivityMainBinding mActivityMainBinding;
    private static final String TAG = "MainActivity";

    public static Intent getStartMain(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityMainBinding = getViewDataBinding();
        mMainViewModel.setNavigator(this);

        setUp();
        setUpRx();
        subscribeToLiveData();

        mMainAdapter.setItemResultClickListener(this);
    }

    @Override
    public void subscribeToLiveData() {
        mMainViewModel.getKamusListLiveData().observe(this, words -> mMainViewModel.addlistItemsToList(words));
    }

    private void setUpRx() {
        mMainViewModel.setEdt(mActivityMainBinding.edtSearch);
    }

    @Override
    public void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mActivityMainBinding.rc.setLayoutManager(mLayoutManager);
        mActivityMainBinding.rc.setItemAnimator(new DefaultItemAnimator());
        mActivityMainBinding.rc.setAdapter(mMainAdapter);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        mMainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        return mMainViewModel;
    }

    @Override
    public void onSearchClicked() {
        String word = mActivityMainBinding.edtSearch.getText().toString().trim();
        Log.i(TAG, "onSearchClicked: "+word);
        if (!word.equals("")) {
            Log.i(TAG, "masuk if: "+word);
            mMainViewModel.setSingleSearch(word);
            mActivityMainBinding.edtSearch.setText("");
            mMainAdapter.clearItems();
            hideKeyboard();
        }else{
            Log.i(TAG, "masuk else: "+word);
        }
    }

    @Override
    public void wipeText() {
        mActivityMainBinding.edtSearch.setText("");
        mActivityMainBinding.resultEdt.setText("");
        mMainAdapter.clearItems();
    }

    @Override
    public void onItemResultEngIndClicked(List<Word> words, int post) {
        String search = words.get(post).getWords();
        String result = words.get(post).getTranslation();

        mActivityMainBinding.resultEdt.setText(search + " : \n" + result);
    }
}
