package com.example.tsmpc47.kamus.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tsmpc47.kamus.data.model.Word;
import com.example.tsmpc47.kamus.databinding.ItemMainBinding;
import com.example.tsmpc47.kamus.ui.base.BaseViewHolder;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Word> wordList;
    private static final String TAG = "MainAdapter";

    public ItemResultClickListener mItemResultClickListener;

    public void setItemResultClickListener(ItemResultClickListener listener) {
        this.mItemResultClickListener = listener;
    }

    public MainAdapter(List<Word> wordList1) {
        Log.i(TAG, "MainAdapter: "+wordList1.size());
        this.wordList = wordList1;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMainBinding binding = ItemMainBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public void addItems(List<Word> indonesiaEnglishes) {
        wordList.addAll(indonesiaEnglishes);
        notifyDataSetChanged();
    }

    public void clearItems() {
        wordList.clear();
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends BaseViewHolder implements ItemViewModel.ItemResultViewModelListener {

        private ItemMainBinding mBinding;
        private ItemViewModel mViewModel;

        public ItemViewHolder(ItemMainBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            Word word = wordList.get(position);
            mViewModel = new ItemViewModel(position, word,this);
            mBinding.setViewModel(mViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(int post) {
            mItemResultClickListener.onItemResultEngIndClicked(wordList, post);
        }
    }

    public interface ItemResultClickListener {
        void onItemResultEngIndClicked(List<Word> words, int post);
    }
}
