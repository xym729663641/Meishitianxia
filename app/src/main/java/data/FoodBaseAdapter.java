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

public class FoodBaseAdapter extends RecyclerView.Adapter<FoodBaseAdapter.ViewHolder>  {
    private List<food> list;
    Handler handler=new Handler();
    private OnItemClickListener onItemClickListener;
    public FoodBaseAdapter(List<food> list){
        this.list=list;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView img;
        TextView href;
        public ViewHolder(View View) {
            super(View);
            name=(TextView)itemView.findViewById(R.id.xym_food_name);
            img=(ImageView)itemView.findViewById(R.id.xym_food_img);
            href=(TextView)itemView.findViewById(R.id.xym_food_href);
        }
    }
    public interface OnItemClickListener{
        void onClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.xym_item_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final food food=list.get(position);
        holder.name.setText(food.getName());
        holder.href.setText(food.getHref());
        if(onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(v,position);
                }
            });
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap=getURLimage(food.getImg());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        holder.img.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();
    }

    @Override
    public int getItemCount() {
        return list.size();
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
