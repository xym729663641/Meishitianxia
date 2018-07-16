package data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import first.cc.meishitianxia.R;

/**
 * Created by xieyumin on 2018/7/16.
 */

public class xym_ItemAdapter extends RecyclerView.Adapter<xym_ItemAdapter.ViewHolder> {
    private List<xym_item> list;
    private Handler handler=new Handler();
    public xym_ItemAdapter(List<xym_item> list){this.list=list;}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.xym_item_layout2,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final xym_item item=list.get(position);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap=getURLimage(item.getImg());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        holder.imageView.setImageBitmap(bitmap);
                        holder.textView.setText(item.getWord());
                    }
                });
            }
        }).start();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.image);
            textView=(TextView)itemView.findViewById(R.id.word);
        }
    }
    public Bitmap getURLimage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }
}
