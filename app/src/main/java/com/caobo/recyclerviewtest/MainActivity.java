package com.caobo.recyclerviewtest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView的使用
 *  你想要控制其显示的方式，请通过布局管理器LayoutManager
    你想要控制Item间的间隔（可绘制），请通过ItemDecoration
    你想要控制Item增删的动画，请通过ItemAnimator
    你想要控制点击、长按事件，请自己写（擦，这点尼玛。）
 *
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private List<String> mDatas;

    private HomeAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecyclerView的设置
        initData();
        mAdapter = new HomeAdapter(this,mDatas);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //设置布局管理器,还可以设置其他的属性，如
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //适配器
        mRecyclerView.setAdapter(mAdapter);



        //initData();
        //mAdapter = new HomeAdapter(this,mDatas);
        //mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //GridView效果
        // mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        //适配器
        //mRecyclerView.setAdapter(mAdapter);




        //initData();
        //mAdapter = new HomeAdapter(this,mDatas);
        //mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //StaggeredGridLayoutManager的效果，StaggeredGridLayoutManager.VERTICAL代表有多少列；那么传入的如果是StaggeredGridLayoutManager.HORIZONTAL就代表有多少行
        //mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        //当时横向时，item的宽度需要注意去设置，毕竟横向的宽度没有约束了
        //mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL));
        //适配器
        //mRecyclerView.setAdapter(mAdapter);




        //设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        //GridView效果,要瀑布时用这个
        //mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));

        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //RecyclerView点击事件
        mAdapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, position + "click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                //这里长按定义的是删除
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("是否删除");
                dialog.setMessage("确定吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    mAdapter.removeData(position);
                    }
                } );

                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();

                Toast.makeText(MainActivity.this, position + "Long click", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //加载菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.action_bar_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //菜单点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.add_item:
                mAdapter.addData(1);
                break;
            case R.id.delete_item:
                mAdapter.removeData(1);
                break;
            default:
                break;

        }
        return true;
    }

    //初始化数据
    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }




}
