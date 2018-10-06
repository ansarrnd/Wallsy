package com.ansar.wallsy.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.ansar.wallsy.R;
import com.ansar.wallsy.data.network.APIClient;
import com.ansar.wallsy.data.network.DataProvider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseFragment extends Fragment {

    protected ViewGroup errorLayout;
    protected RecyclerView gridView;
    protected View progressBar;
    private GridLayoutManager gridLayoutManager;
    protected DataProvider dataProvider;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataProvider = APIClient.getInstance().create(DataProvider.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        errorLayout = view.findViewById(R.id.error_layout);
        gridView = view.findViewById(R.id.gridView);
        progressBar = view.findViewById(R.id.loader);

        gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        gridView.setLayoutManager(gridLayoutManager);

        if (progressBar != null){
            progressBar.setAlpha(0.0f);
        }

        init();
        loadImage();
    }

    protected void showLoader() {
        progressBar.animate().alpha(1.0f).setDuration(300).start();
    }

    protected void hideLoader() {
        progressBar.animate().alpha(0.0f).setDuration(300).start();
    }

    public abstract void loadImage();

    public abstract void init();
}
