package com.ansar.wallsy.topList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ansar.wallsy.R;
import com.ansar.wallsy.base.BaseFragment;
import com.ansar.wallsy.base.BaseImageAdapter;
import com.ansar.wallsy.data.Image;
import com.ansar.wallsy.util.Parser;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.ansar.wallsy.constants.Constant.Options.CATEGORIES;
import static com.ansar.wallsy.constants.Constant.Options.ORDER;
import static com.ansar.wallsy.constants.Constant.Options.PAGE;
import static com.ansar.wallsy.constants.Constant.Options.PURITY;
import static com.ansar.wallsy.constants.Constant.Options.RATIOS;
import static com.ansar.wallsy.constants.Constant.Options.RESOLUTIONS;
import static com.ansar.wallsy.constants.Constant.Options.SORTING;

public class TopListFragment extends BaseFragment {

    private int currentPage = 1;
    private CompositeDisposable disposable;

    public static TopListFragment newInstance() {

        Bundle args = new Bundle();

        TopListFragment fragment = new TopListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @Override
    public void init() {
        disposable = new CompositeDisposable();
    }

    @Override
    public void loadImage() {
        showLoader();
        dataProvider.getData(getOptions())
                .flatMap(new Function<String, ObservableSource<List<Image>>>() {
                    @Override
                    public ObservableSource<List<Image>> apply(String s) throws Exception {
                        return Observable.just(Parser.parseImages(s));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Image>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(List<Image> images) {
                        hideLoader();
                        gridView.setAdapter(new BaseImageAdapter(images, images.size()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoader();
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Map<String, String> getOptions() {

        Map<String, String> options = new HashMap<>();

        options.put(CATEGORIES, "110");
        options.put(RESOLUTIONS, "");
        options.put(PURITY, "100");
        options.put(RATIOS, "");
        options.put(SORTING, "views");
        options.put(ORDER, "desc");
        options.put(PAGE, currentPage + "");

        return options;
    }
}
