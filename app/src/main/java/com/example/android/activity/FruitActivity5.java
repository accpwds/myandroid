package com.example.android.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.R;

public class FruitActivity5 extends AppCompatActivity {

    public static final String FRUIT_NAME="fruit_name";

    public static final String FRUIT_IMAGE_ID="fruit_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit5);
        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID,0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView = (ImageView) findViewById(R.id.fruit_image_view);
        TextView fruitContextText = (TextView) findViewById(R.id.fruit_context_text);
        //启动导航栏
        setSupportActionBar(toolbar);
        //设置返回键头显示
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //设置标题
        collapsingToolbar.setTitle(fruitName);
        //Glide加载图片到图片视图中
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        String fruitContext = generateFruitContext();
        fruitContextText.setText(fruitContext);
    }

    /**
     * 键头点击触发事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String generateFruitContext(){
        StringBuffer sb = new StringBuffer();
        sb.append("\t\t\t\t出门观光不再是“千景一面”\n\n");
        sb.append("\t\t\t\t这份意见要求，注重产品、设施与项目的特色，不搞一个模式，防止千城一面、" +
                "千村一面、千景一面，推行各具特色、差异化推进的全域旅游发展新方式。推动旅游" +
                "与城镇化、工业化和商贸业融合发展。依托风景名胜区、历史文化名城名镇名村、特" +
                "色景观旅游名镇、传统村落，探索名胜名城名镇名村“四名一体”全域旅游发展模式。\n\n");
        sb.append("\t\t\t\t南开大学教授石培华表示，“旅游+”是全域旅游背景下满足人民幸福生活的一大核心" +
                "路径。选择“旅游+”，使旅游与农业、林业、工业、文化、医药等相关产业深度融合、" +
                "共融共生，带来各种旅游产品的丰富多彩，较好满足了游客知识获得、文化感知、休闲" +
                "娱乐等个性化、多样化的旅游需求。\n\n");
        sb.append("\t\t\t\t涉旅场所免费WiFi将全覆盖\n\n");
        sb.append("\t\t\t\t意见提出，推进服务智能化。涉旅场所实现免费WiFi、通信信号、视频监控" +
                "全覆盖，主要旅游消费场所实现在线预订、网上支付，主要旅游区实现智能导游、电子" +
                "讲解、实时信息推送，开发建设咨询、导览、导游、导购、导航和分享评价等智能化旅" +
                "游服务系统。\n\n");
        sb.append("\t\t\t\t外出旅游，随时随地自拍晒照是一大需求，但网络和流量问题成为很多的人" +
                "顾虑。有了免费WiFi，就可以随时在朋友圈晒照片了，着实给力。不过，希望到时WiFi" +
                "的速度也能一样给力！\n\n");
        return sb.toString();
    }
}
