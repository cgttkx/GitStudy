package com.example.gitstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class SearchViewTest extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private SearchView sv;
    private ListView lv;
    private  final String[] mStrings={"aa","bb","cc"};//自动完成的列表
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchview);

        lv=findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mStrings));
        lv.setTextFilterEnabled(true);

        sv=findViewById(R.id.sv);
        sv.setIconifiedByDefault(false);//设置该SearchView默认是否自动缩小为图标
        sv.setOnQueryTextListener(this);//设置该SearchView组件设置事件监听器
        sv.setSubmitButtonEnabled(true);//设置该SearchView显示搜索按钮
    }

    // 当搜索内容改变时触发该方法
    @Override
    public boolean onQueryTextChange(String newText) {//此方法的作用是对搜索框里的文字实时监听。
        if (!TextUtils.isEmpty(newText)){
            lv.setFilterText(newText);//清除ListView的过滤
        }else{
            lv.clearTextFilter();//使用用户输入的内容对ListView的列表项进行过滤
        }
        return true;
    }

    // 当点击搜索按钮时触发该方法
    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(this,"您的选择是："+query,Toast.LENGTH_SHORT).show();
        return false;//这里return false不需要修改，false的作用是当你输入要搜索的文字点击搜索按钮后，手机键盘会自动消失，你把false改成true，键盘不会消失。
    }
}