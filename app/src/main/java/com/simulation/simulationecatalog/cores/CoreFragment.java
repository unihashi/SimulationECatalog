package com.simulation.simulationecatalog.cores;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.simulation.simulationecatalog.R;
import com.simulation.simulationecatalog.classes.MainNavigation;
import com.simulation.simulationecatalog.classes.Singleton;
import com.simulation.simulationecatalog.data.components.InformationDialog;

public class CoreFragment extends Fragment {

    protected String DEBUG_TAG;
    public Dialog loading;
    protected MainNavigation.MainNavigationListener listener;
    public FragmentManager fragmentManager;
    public Context context;
    public Activity activity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DEBUG_TAG = this.getClass().getSimpleName();
        Singleton.instance().restoreInstanceState(savedInstanceState);
        fragmentManager = getFragmentManager();
        context = requireContext();
        activity = getActivity();
        createDialog();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void createDialog() {
        loading = new Dialog(requireContext());
        loading.requestWindowFeature(Window.FEATURE_NO_TITLE);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        loading.setContentView(R.layout.component_loading);
        loading.setCancelable(false);
    }

    protected void hideSoftKeyboard() {
        if (this.getActivity().getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(this.getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void onDestroyView() {
        if (loading != null) {
            loading.dismiss();
        }
        destroyAllDialog();

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (loading != null) {
            loading.dismiss();
        }

        destroyAllDialog();

        super.onDestroy();
    }

    @Override
    public void onDetach() {
        if (loading != null) {
            loading.dismiss();
        }

        destroyAllDialog();

        super.onDetach();
    }

    private void destroyAllDialog() {
        InformationDialog.dismiss();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState = Singleton.instance().saveInstanceState(outState);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {

        Singleton.instance().restoreInstanceState(savedInstanceState);


        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        try {
            Animation animation = AnimationUtils.loadAnimation(requireContext(), nextAnim);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (enter) onPostResume();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            return animation;
        } catch (Resources.NotFoundException e) {
            return null;
        }
    }

    protected void showDialogUnknownError(Context context) {
        if (context != null) {
            InformationDialog.show(context, context.getString(R.string.hd_dialog_title_error),
                    context.getString(R.string.txt_unknown_error), null);
        }
    }

    public void onPostResume() {

    }
}