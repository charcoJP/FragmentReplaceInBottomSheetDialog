package jp.co.lanches.cardapp.bottomsheetdialogsample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import jp.co.lanches.cardapp.bottomsheetdialogsample.databinding.LayoutBottomSheetBinding;


public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment implements MyBottomSheetListener {

    public static MyBottomSheetDialogFragment newInstance() {
        return new MyBottomSheetDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutBottomSheetBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_bottom_sheet, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showStubFragment();
    }

    @Override
    public void onClick() {
        showStubFragment();
    }

    private void showStubFragment() {
        Fragment fragment = new StubFragment();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public static class StubFragment extends Fragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Button button = new Button(getActivity());
            // replace確認用
            button.setText("next" + DateFormat.format("yyyy/MM/dd kk:mm:ss.SSS", System.currentTimeMillis()));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = getParentFragment();
                    if (fragment instanceof MyBottomSheetListener) {
                        ((MyBottomSheetListener) fragment).onClick();
                    }
                }
            });

            return button;
        }
    }
}
