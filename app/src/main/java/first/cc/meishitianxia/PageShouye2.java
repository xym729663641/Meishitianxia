package first.cc.meishitianxia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import data.xym_ItemAdapter;
import first.cc.meishitianxia.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import data.xym_item;

public class PageShouye2 extends Activity {
    Handler handler=new Handler();
    List<xym_item> list=new ArrayList<xym_item>();
    TextView title;
    TextView jianjie;
    TextView zuofa;
    ImageView image;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_shouye2);
        title=(TextView)findViewById(R.id.title2);
        Button button=(Button)findViewById(R.id.back2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        jianjie=(TextView)findViewById(R.id.jianjie);
        zuofa=(TextView)findViewById(R.id.zuofa);
        image=(ImageView)findViewById(R.id.img2);
        recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        final String web=bundle.getString("web");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document document= null;
                try {
                    document = Jsoup.connect(web).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final Elements elements=document.select("a#recipe_title");
                final Elements img=document.select("#recipe_De_imgBox").select("a").select("img");
                final Elements sc=document.select("div#block_txt1");
                final Elements elements1=document.select("div.space_box_home").select("div").select("div:nth-child(10)").select("h3");
                final Elements zf=document.select("div.wrap").select("div").select("div.space_left").select("div.space_box_home").select("div").select("div.recipeStep");
                final Elements tp=zf.select("ul").select("li").select("div.recipeStep_img").select("img");
                final Elements wz=zf.select("ul").select("li").select("div.recipeStep_word");
                final Bitmap bitmap=getURLimage(img.attr("src"));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        title.setText(elements.text());
                        jianjie.setText(sc.text());
                        zuofa.setText(elements1.text());
                        image.setImageBitmap(bitmap);
                        for(int i=0;i<tp.size();i++){
                            list.add(new xym_item(tp.get(i).attr("src"),wz.get(i).text()));
                        }
                        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(manager);
                        xym_ItemAdapter adapter=new xym_ItemAdapter(list);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }).start();
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

