package first.cc.meishitianxia;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 44223 on 2018/7/10.
 */

public class PageUser extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.vp_user_wo,container, false);
        TextView tv_name=(TextView)view.findViewById(R.id.tv_username);

        MyApplication app=(MyApplication)getActivity().getApplication();
        if(app.getStudent()!=null)
        {
            String username=app.getStudent().name;
            tv_name.setText("欢迎您，"+username);
        }
        else
        {
            loginorregister();
        }
        return view;
    }

    private Button btngologin;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btngologin=(Button)view.findViewById(R.id.btn_gologin);
        btngologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginorregister();
            }
        });
    }


    private void loginorregister(){
        Intent intent=new Intent(getContext(),LoginActivity.class);
        startActivity(intent);
    }
}
