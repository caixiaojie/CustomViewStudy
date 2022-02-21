package com.cxj.customviewstudy.dialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cxj.customviewstudy.R;

import org.jetbrains.annotations.NotNull;

public abstract class BaseDialogFragment extends DialogFragment implements DialogInterface.OnKeyListener{


    protected abstract int getLayout();

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        if (getLayout() > 0) {
            View view = inflater.inflate(getLayout(), container, false);
            return view;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
//        int width = ScreenUtils.getScreenWidth(getContext());
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes(params);
        getDialog().setOnKeyListener(this::onKey);
    }
    boolean canFinish = false;
    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if (getActivity() != null && canFinish) {
                getActivity().onKeyUp(keyCode, event);
            }
            return true;
        }else if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
            canFinish = true;
            return false;
        }else {
            return false;
        }
    }



    protected int getDialogWidth() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    protected int getDialogHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    private void initWindowParams() {
        Dialog dialog = getDialog();
        if (dialog != null) {

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            Window window = dialog.getWindow();
            if (window != null) {
                window.getDecorView().setPadding(0, 0, 0, 0);
                window.setBackgroundDrawableResource(R.color.transparent);
                WindowManager.LayoutParams windowAttributes = window.getAttributes();
                windowAttributes.gravity = Gravity.CENTER;
                windowAttributes.width = getDialogWidth();
                windowAttributes.height = getDialogHeight();
                window.setAttributes(windowAttributes);
            }
        }
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (IllegalStateException e) {
            try {
                FragmentTransaction ft = manager.beginTransaction();
                ft.add(this, tag);
                ft.commitAllowingStateLoss();
            } catch (IllegalStateException e1) {
            }
        }
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        transaction.add(this, tag);
        return transaction.commitAllowingStateLoss();
    }

    @Override
    public void dismiss() {
        try {
            super.dismissAllowingStateLoss();
        } catch (IllegalStateException e) {
        }
    }
}
