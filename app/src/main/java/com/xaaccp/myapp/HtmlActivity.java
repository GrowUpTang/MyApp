package com.xaaccp.myapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class HtmlActivity extends AppCompatActivity implements OnBannerListener {


    private String url = "https://m.cniao5.com/";
    private WebView webView;
    private WebSettings webSettings;


    Banner banner;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);

        banner = findViewById(R.id.banner);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initView() {
        banner = (Banner) findViewById(R.id.banner);
        //放图片地址的集合
        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();

        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        list_title.add("好好学习");
        list_title.add("天天向上");
        list_title.add("热爱劳动");
        list_title.add("不搞对象");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(HtmlActivity.this)
                //必须最后调用的方法，启动轮播图。
                .start();
//        url = getIntent().getStringExtra("url");

        StautsBarUtil.setColor(this, ContextCompat.getColor(this, R.color.common));

        webView = findViewById(R.id.webView);
        webSettings = webView.getSettings();
        webSettings.setSaveFormData(false); // 是否保存产生的数据
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(false);
        webSettings.setAllowUniversalAccessFromFileURLs(false);
        webSettings.setJavaScriptEnabled(true); // 是否启用JAVA脚本
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true); // 是否支持缩放
        webSettings.setDisplayZoomControls(false);

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //webView.setWebChromeClient(new MyWebChromeClient());
        webView.requestFocusFromTouch();

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView v, String u) {
                v.loadUrl(u);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String u, Bitmap favicon) {
                super.onPageStarted(view, u, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String u) {
                super.onPageFinished(view, url);
                //拦截上海銀行回調url
                /*if(u.contains("returnurl/payreturn")){
                    //注入一段js代碼
                    String jsCode="javascript: (function(){ " +
                            "var btn=document.getElementById('regist_n_submit');" +
                            "btn.onclick=function(){window.app.toMyFragment();} " +
                            "})()";
                    view.loadUrl(jsCode);
                }*/

            }
        });
        if (url != null) {
            webView.loadUrl(url);
        } /*else if (postUrl != null) {
            // value要做url编码处理 URLEncoder.encode；
            webView.postUrl(postUrl, urlParam.getBytes());
        } else if (body != null) {
            webView.loadDataWithBaseURL(null, body, "text/html", "UTF-8", null);
        }
        loadingWindow = new LoadingWindow(this, webView);*/
    }


    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第"+position+"张轮播图");

    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
}
