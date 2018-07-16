package data;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import first.cc.meishitianxia.R;

/**
 * Created by xieyumin on 2018/7/16.
 */

public class xym_health_fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.xym_health_fragment,container,false);
        return view;
    }
}
