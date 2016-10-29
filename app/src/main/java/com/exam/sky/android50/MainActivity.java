package com.exam.sky.android50;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    //控件，位置随意，布局文件中书写
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        //设置导航图标(类似于ActionBar的箭头)
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_add);
        //设置logo图标
        toolbar.setLogo(R.mipmap.ic_launcher);

        toolbar.setTitle("主标题");
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setSubtitle("副标题");
        toolbar.setSubtitleTextColor(Color.WHITE);


        //设置ToolBar替代ActionBar,当作标题栏
        setSupportActionBar(toolbar);


        //设置菜单点击事件必须放在setSupportActionBar()之后
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.call:
                        Log.e("=====","==onMenuItemClick===");
                        break;
                }
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setIconEnable(menu,true);
        //加载菜单文件
        getMenuInflater().inflate(R.menu.main_menu,menu);
        searchData(menu);
        shareData(menu);

        return super.onCreateOptionsMenu(menu);
    }

    //enable为true时，菜单添加图标有效，enable为false时无效。4.0系统之后默认无效
    private void setIconEnable(Menu menu, boolean enable) {
        if (menu != null) {
            Log.e("====", "===ffffff===");
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                Log.e("====", "===Meeeeeenu===");
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, enable);
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Log.e("=====","==点击了导航图标===");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //分享
    public void shareData(Menu menu){
        //查找item项
       MenuItem item =  menu.findItem(R.id.share);
        //根据指定item得到Provider类
        ShareActionProvider   shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        Intent intent =new Intent(Intent.ACTION_SEND);
        intent.setDataAndType(Uri.parse(""),"text/*");
        //指定平台分享
        //intent.setPackage("");
        //设置分享意图目的动作
        shareActionProvider.setShareIntent(intent);
    }

    //搜索
    public void searchData(Menu menu){
        MenuItem item1 = menu.findItem(R.id.search);

        //根据指定item得到View视图
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item1);
        //设置文本变化的监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("=====","=搜索输入的关键字是==="+newText);
                //本地搜索：数据库查询：模糊匹配：like
                //网络搜索：post请求：post请求发送关键字给服务器，服务器接受在网络数据库中查找,查找到结果服务器通过json/xml(pull,sax,Dom)结构传递给客户端


                return false;
            }
        });
    }
}
