package com.cxj.customviewstudy;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.cxj.customviewstudy.dialogfragment.BaseDialogFragment;

public class FirstDialogFragment extends BaseDialogFragment {
    public static FirstDialogFragment newInstance() {

        Bundle args = new Bundle();

        FirstDialogFragment fragment = new FirstDialogFragment();
        fragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.baseDialogTheme);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_fragment_first;
    }
}
