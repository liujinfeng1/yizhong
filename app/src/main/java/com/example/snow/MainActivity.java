package com.example.snow;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private int imageIds[];
    private String[] titles;
    private ArrayList<ImageView> images;
    private ArrayList<View> dots;
    private TextView title;
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;
    private int[] ids = new int[]{R.id.bn1,R.id.bn2,R.id.bn3,R.id.bn4,R.id.bn5};
    private int oldPosition = 0;//记录上一次点的位置
    private int currentItem; //当前页面
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4=(Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);
        Button button5=(Button)findViewById(R.id.button5);
        button5.setOnClickListener(this);
        Button button6=(Button)findViewById(R.id.button6);
        button6.setOnClickListener(this);
        Button button7=(Button)findViewById(R.id.button7);
        button7.setOnClickListener(this);

        //图片ID
        imageIds = new int[]{
                R.drawable.school,
                R.drawable.school2,
                R.drawable.school3,
                R.drawable.school4,
                R.drawable.school5,



        };

        //图片标题
        titles = new String[]{
                "安丘两所学校被省里点名了",
                "喜讯！2017潍坊高考文理科“状元” 来自安丘一中",
                "今天，安丘一中“搬新家了”！新校区颜值爆表！",
                "安丘一中这几个老师，要在全潍坊出名了！",
                "一组安丘城市的新老照片对比!"
        };

        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i =0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setId(ids[i]);
            imageView.setOnClickListener(this);
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }

        //显示的点
        dots = new ArrayList<View>();
        dots.add(findViewById(R.id.dot_0));
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));
        dots.add(findViewById(R.id.dot_3));
        dots.add(findViewById(R.id.dot_4));

        title = (TextView) findViewById(R.id.title);
        title.setText(titles[0]);

        mViewPager = (ViewPager) findViewById(R.id.vp);

        adapter = new ViewPagerAdapter();
        mViewPager.setAdapter(adapter);

        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {



            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                title.setText(titles[position]);

                dots.get(oldPosition).setBackgroundResource(R.drawable.point_normal);
                dots.get(position).setBackgroundResource(R.drawable.point_focus);

                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return images.size();
        }

        //是否是同一张图片
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//            super.destroyItem(container, position, object);
//            view.removeViewAt(position);
            view.removeView(images.get(position));

        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));

            return images.get(position);
        }
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        //每隔2秒钟切换一张图片
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 2, 8, TimeUnit.SECONDS);
    }

    //切换图片
    private class ViewPagerTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            currentItem = (currentItem +1) % imageIds.length;
            //更新界面
            handler.sendEmptyMessage(0);
            //handler.obtainMessage().sendToTarget();
        }

    }

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            //设置当前页面
            mViewPager.setCurrentItem(currentItem);
        }

    };

    //添加点击事件
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bn1:
                Intent intent1 =new Intent(MainActivity.this,AdioneActivity.class);
                startActivity(intent1);
                break;
            case R.id.bn2:
                Intent intent2 =new Intent(MainActivity.this,AditwoActivity.class);
                startActivity(intent2);
                break;
            case R.id.bn3:
                Intent intent3 =new Intent(MainActivity.this,AdithreeActivity.class);
                startActivity(intent3);
                break;
            case R.id.bn4:
                Intent intent4 =new Intent(MainActivity.this,AdifourActivity.class);
                startActivity(intent4);
                break;
            case R.id.bn5:
                Intent intent5 =new Intent(MainActivity.this,AdifiveActivity.class);
                startActivity(intent5);
                break;
            case R.id.button2:
                Intent intent_button2 =new Intent(MainActivity.this,LocationActivity.class);
                startActivity(intent_button2);
                break;
            case R.id.button3:
                Intent intent_button3=new Intent(MainActivity.this,CharacteristicsActivity.class);
                startActivity(intent_button3);
                break;
            case R.id.button4:
                Intent intent_button4=new Intent(MainActivity.this,SchoolTeacherActivity.class);
                startActivity(intent_button4);
                break;
            case R.id.button5:
                Intent intent_button5=new Intent(MainActivity.this,CharacteristicsActivity.class);
                startActivity(intent_button5);
                break;
            case R.id.button6:
                Intent intent_button6=new Intent(MainActivity.this,MultimediaTeachingActivity.class);
                startActivity(intent_button6);
                break;
            case R.id.button7:
                Intent intent_button7=new Intent(MainActivity.this,AlumnusActivity.class);
                startActivity(intent_button7);
                break;
        }
    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }



}