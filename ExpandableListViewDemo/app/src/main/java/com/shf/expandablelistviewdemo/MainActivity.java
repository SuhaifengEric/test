package com.shf.expandablelistviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView exListView;
    private ArrayList<Group> gData = null;
    private ArrayList<ArrayList<Hero>> iData = null;
    private ArrayList<Hero> lData = null;
    private MyExpandAdapter myExpandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exListView = (ExpandableListView) findViewById(R.id.exlist_lol);
        gData = new ArrayList<Group>();
        iData = new ArrayList<ArrayList<Hero>>();
        lData = new ArrayList<Hero>();
        gData.add(new Group("AD"));
        gData.add(new Group("AP"));
        gData.add(new Group("TANK"));
        //AD组
        lData.add(new Hero(R.mipmap.iv_lol_icon3,"剑圣"));
        lData.add(new Hero(R.mipmap.iv_lol_icon4,"德莱文"));
        lData.add(new Hero(R.mipmap.iv_lol_icon13,"男枪"));
        lData.add(new Hero(R.mipmap.iv_lol_icon14,"韦鲁斯"));
        iData.add(lData);
        //AP组
        lData = new ArrayList<Hero>();
        lData.add(new Hero(R.mipmap.iv_lol_icon1, "提莫"));
        lData.add(new Hero(R.mipmap.iv_lol_icon7, "安妮"));
        lData.add(new Hero(R.mipmap.iv_lol_icon8, "天使"));
        lData.add(new Hero(R.mipmap.iv_lol_icon9, "泽拉斯"));
        lData.add(new Hero(R.mipmap.iv_lol_icon11, "狐狸"));
        iData.add(lData);
        //TANK组
        lData = new ArrayList<Hero>();
        lData.add(new Hero(R.mipmap.iv_lol_icon2, "诺手"));
        lData.add(new Hero(R.mipmap.iv_lol_icon5, "德邦"));
        lData.add(new Hero(R.mipmap.iv_lol_icon6, "奥拉夫"));
        lData.add(new Hero(R.mipmap.iv_lol_icon10, "龙女"));
        lData.add(new Hero(R.mipmap.iv_lol_icon12, "狗熊"));
        iData.add(lData);
        myExpandAdapter = new MyExpandAdapter(gData,iData,this);
        exListView.setAdapter(myExpandAdapter);
        exListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(MainActivity.this,"点击了：" + iData.get(i).get(i1).getName(),Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    class MyExpandAdapter extends BaseExpandableListAdapter{
        private ArrayList<Group> gData;
        private ArrayList<ArrayList<Hero>> iData;
        private Context mContext;

        public MyExpandAdapter(ArrayList<Group> gData,ArrayList<ArrayList<Hero>> iData,Context mContext){
            this.gData = gData;
            this.iData = iData;
            this.mContext = mContext;
        }

        @Override
        public int getGroupCount() {
            return gData.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return iData.size();
        }

        @Override
        public Group getGroup(int i) {
            return gData.get(i);
        }

        @Override
        public Hero getChild(int i, int i1) {
            return iData.get(i).get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
        //取得用于显示给定分组的视图. 这个方法仅返回分组的视图对象
        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            ViewHolder groupHolder = null;
            if (view == null){
                groupHolder = new ViewHolder();
                view = View.inflate(mContext,R.layout.item_exlist_group,null);
                groupHolder.tvGroup = (TextView) view.findViewById(R.id.tv_group_name);
                view.setTag(groupHolder);
            }else{
                groupHolder = (ViewHolder) view.getTag();
            }
            groupHolder.tvGroup.setText(getGroup(i).getName());
            return view;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            ViewHolder heroHolder = null;
            if (view == null){
                heroHolder = new ViewHolder();
                view = View.inflate(mContext,R.layout.item_exlist_item,null);
                heroHolder.ivIcon = (ImageView) view.findViewById(R.id.img_icon);
                heroHolder.tvName = (TextView) view.findViewById(R.id.tv_name);
                view.setTag(heroHolder);
            }else{
                heroHolder = (ViewHolder) view.getTag();
            }
            heroHolder.ivIcon.setImageResource(getChild(i, i1).getIcon());
            heroHolder.tvName.setText(getChild(i, i1).getName());
            return view;
        }
        //返回设为true，否则item不响应
        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }

    class ViewHolder{
        TextView tvGroup;
        ImageView ivIcon;
        TextView tvName;
    }
}
