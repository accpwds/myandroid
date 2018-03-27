package com.example.android.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.R;
import com.example.android.adapter.FruitAdapter4;
import com.example.android.object.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MaterialDesignActivity extends BaseActivity {

    //左屏滑动布局
    private DrawerLayout mDrawerLayout;

    private Fruit[] fruits = {
            new Fruit("paopaobing3",R.drawable.paopaobing3_static),
            new Fruit("paopaobing4",R.drawable.paopaobing4_static),
            new Fruit("paopaobing5",R.drawable.paopaobing5_static),
            new Fruit("paopaobing6",R.drawable.paopaobing6_static),
            new Fruit("paopaobing7",R.drawable.paopaobing7_static),
            new Fruit("paopaobing8",R.drawable.paopaobing8_static),
            new Fruit("paopaobing9",R.drawable.paopaobing9_static),
            new Fruit("paopaobing10",R.drawable.paopaobing10_static),
            new Fruit("paopaobing11",R.drawable.paopaobing11_static),
            new Fruit("paopaobing12",R.drawable.paopaobing12_static),
            new Fruit("paopaobing13",R.drawable.paopaobing13_static),
            new Fruit("paopaobing14",R.drawable.paopaobing14_static),
            new Fruit("paopaobing15",R.drawable.paopaobing15_static)
    };

    private List<Fruit> fruitList = new ArrayList<>();

    //适配器
    private FruitAdapter4 adapter;

    //下拉刷新
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //使用Toolbar，使其与ActionBar功能一致
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //滑动菜单布局
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        //导航标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);  //显示导航按钮
            actionBar.setHomeAsUpIndicator(R.drawable.head1); //设置导航按钮图标

        }
        //滑动菜单布局
        navView.setCheckedItem(R.id.nav_call); //选中滑动菜单中的某一个选项菜单
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers(); //选中菜单则触发并闭滑动菜单
                return false;
            }
        });

        //获得浮动按钮
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //二次确认提醒
                Snackbar.make(v,"Data deleted",Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(MaterialDesignActivity.this,
                                                "Fab chicked",Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
            }
        });

        initFruit();
        //滚动视图
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //瀑布流网格显示
        GridLayoutManager gridLayoutManager;
        gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new FruitAdapter4(fruitList);
        recyclerView.setAdapter(adapter);

        //下拉刷新
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }

    public void refreshFruits(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //子线程切换为主线程
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruit();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    //加载toolbar.xml菜单
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                Intent intent = new Intent(MaterialDesignActivity.this,FirstActivity.class);
                startActivity(intent);
                break;
            case R.id.delete:
                Toast.makeText(this,"You clicked delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                //强制下线
                Intent loginOutIntent = new Intent("com.example.test1.FORCE_OFFLINE");
                sendBroadcast(loginOutIntent);
                break;
            //toolbar菜单中最左侧菜单项点击后出现滑动菜单
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initFruit(){
        fruitList.clear();
        for (int i = 0; i < 50; i++){
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }
}
