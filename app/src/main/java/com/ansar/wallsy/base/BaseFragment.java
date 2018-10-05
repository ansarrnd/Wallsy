package com.ansar.wallsy.base;

import android.view.View;
import android.view.ViewGroup;

import com.ansar.wallsy.R;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BaseFragment extends Fragment {

    protected ViewGroup errorLayout;
    protected RecyclerView gridView;
    protected View progressBar;
    private GridLayoutManager gridLayoutManager;

    protected void setupViews(View rootView){
        errorLayout = rootView.findViewById(R.id.error_layout);
        gridView = rootView.findViewById(R.id.gridView);
        progressBar = rootView.findViewById(R.id.loader);

        gridLayoutManager = new GridLayoutManager(rootView.getContext(), 2);
        gridView.setLayoutManager(gridLayoutManager);

        if (progressBar != null){
            progressBar.setAlpha(0.0f);
        }
    }

    protected void showLoader() {
        progressBar.animate().alpha(1.0f).setDuration(300).start();
    }

    protected void hideLoader() {
        progressBar.animate().alpha(0.0f).setDuration(300).start();
    }
}
