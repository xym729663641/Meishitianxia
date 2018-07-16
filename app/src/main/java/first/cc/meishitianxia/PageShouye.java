package first.cc.meishitianxia;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.FoodBaseAdapter;
import data.GlideImageLoader;
import data.food;
import data.xym_food_fragment;
import data.xym_health_fragment;
import data.xym_tuijian_fragment;

/**
 * Created by 44223 on 2018/7/10.
 */

public class PageShouye extends Fragment {
    TextView left_tv1,left_tv2,left_tv3;
    Banner banner;
    List mlist;
    List<String> mlist1;
    List<food> foodList=new ArrayList<food>();
    RecyclerView recyclerView;
    FragmentManager manager;
    Fragment fragment;
    FragmentTransaction transaction;
    Handler handler=new Handler();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.vp_shouye,container, false);
        manager=getFragmentManager();
        mlist=new ArrayList<>();
        mlist.add(R.drawable.aa);
        mlist.add(R.drawable.bb);
        mlist.add(R.drawable.cc);
        mlist.add(R.drawable.dd);
        mlist.add(R.drawable.ee);
        mlist.add(R.drawable.ff);
        mlist1=new ArrayList<String>();
        mlist1.add("https://www.meishichina.com/mofang/dikajianfeican/#hmsr=www&hmpl=index&hmcu=magic&hmkw=D4&hmci=28538");
        mlist1.add("https://www.meishichina.com/mofang/biyemeishigonglue/#hmsr=www&hmpl=index&hmcu=magic&hmkw=D5&hmci=28275");
        mlist1.add("https://www.meishichina.com/mofang/xiandanhuangdezuofa/#hmsr=www&hmpl=index&hmcu=magic&hmkw=D6&hmci=28123");
        mlist1.add("https://www.meishichina.com/mofang/shuqilingshi/#hmsr=www&hmpl=index&hmcu=magic&hmkw=D1&hmci=28922");
        mlist1.add("https://www.meishichina.com/mofang/dandanmochaxiang/#hmsr=www&hmpl=index&hmcu=magic&hmkw=D2&hmci=28781");
        mlist1.add("https://www.meishichina.com/mofang/zibuyangshengzhou/#hmsr=www&hmpl=index&hmcu=magic&hmkw=D3&hmci=28650");
        banner=view.findViewById(R.id.xym_banner);
        recyclerView=view.findViewById(R.id.xym_recycler_view);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImageLoader(new GlideImageLoader());   //设置图片加载器
        banner.setImages(mlist);//设置图片源
        banner.setBannerTitles(mlist1);//设置标题源
        banner.setDelayTime(1500);//设置轮播事件，单位毫秒
        banner.setBannerAnimation(Transformer.Stack);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(mlist1.get(position).toString()));
                startActivity(intent);
            }
        });
        banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器的位置
        banner.start();
        left_tv1=view.findViewById(R.id.left_tv1);
        left_tv2=view.findViewById(R.id.left_tv2);
        left_tv3=view.findViewById(R.id.left_tv3);
        left_tv1.setTextColor(Color.RED);
        left_tv2.setTextColor(Color.BLACK);
        left_tv3.setTextColor(Color.BLACK);
        transaction=manager.beginTransaction();
        fragment=new xym_health_fragment();
        transaction.replace(R.id.xym_frame,fragment);
        transaction.commit();
        left_tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left_tv1.setTextColor(Color.RED);
                left_tv2.setTextColor(Color.BLACK);
                left_tv3.setTextColor(Color.BLACK);
                transaction=manager.beginTransaction();
                fragment=new xym_health_fragment();
                transaction.replace(R.id.xym_frame,fragment);
                transaction.commit();
            }
        });
        left_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left_tv2.setTextColor(Color.RED);
                left_tv1.setTextColor(Color.BLACK);
                left_tv3.setTextColor(Color.BLACK);
                transaction=manager.beginTransaction();
                fragment=new xym_food_fragment();
                transaction.replace(R.id.xym_frame,fragment);
                transaction.commit();
            }
        });
        left_tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left_tv3.setTextColor(Color.RED);
                left_tv1.setTextColor(Color.BLACK);
                left_tv2.setTextColor(Color.BLACK);
                transaction=manager.beginTransaction();
                fragment=new xym_tuijian_fragment();
                transaction.replace(R.id.xym_frame,fragment);
                transaction.commit();
            }
        });
        init();

        return view;
    }
    public void init(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document= Jsoup.connect("https://www.meishichina.com/").get();
                    Elements elements=document.select("ul.on").select("li").select("a:not(.u)");
                    for(int i=0;i<elements.size();i++){
                        food food=new food(elements.get(i).text(),elements.select("img").get(i).attr("data-src"),elements.get(i).attr("href"));
                        foodList.add(food);
                        Log.d("test",i+food.getHref()+"\n");
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(layoutManager);
                            FoodBaseAdapter adapter=new FoodBaseAdapter(foodList);
                            adapter.setOnItemClickListener(new FoodBaseAdapter.OnItemClickListener() {
                                @Override
                                public void onClick(View view, int position) {
                                    TextView textView=(TextView)view.findViewById(R.id.xym_food_href);
                                    Bundle bundle=new Bundle();
                                    bundle.clear();
                                    bundle.putString("web",textView.getText().toString());
                                    Intent intent=new Intent(getActivity(),PageShouye2.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                            recyclerView.setAdapter(adapter);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
