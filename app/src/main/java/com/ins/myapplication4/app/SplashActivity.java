package com.ins.myapplication4.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.ins.myapplication4.app.model.CategoryTagMenu;
import com.ins.myapplication4.app.parsers.DataParser;
import com.ins.myapplication4.app.tasks.TaskCallback;
import com.ins.myapplication4.app.tasks.TaskResult;
import com.ins.myapplication4.app.tasks.impl.CategoryTagMenuTaks;
import com.ins.myapplication4.app.util.PackageUtil;
import org.json.JSONObject;

import java.util.List;


public class SplashActivity extends FragmentActivity implements TaskCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ;
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CategoryTagMenuTaks(this).execute("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTaskFinished(TaskResult result) {

        Object data = result.data;
        if (result != null) {
            int taskId = result.taskId;
            if(taskId==TaskConstants.TASK_CATEGORY_TAG_MENU){
                //TODO 获取category_tag_menu的数据

                if(data!=null){
                    if(data instanceof JSONObject){
                        JSONObject json = (JSONObject) data;
                        List<CategoryTagMenu> categoryTagMenuList= DataParser.parseCategoryTagMenuResult(json);

                        Log.d("xxx---", "xxxx" + categoryTagMenuList.get(1));

                        //TODO 存储CategoryTagMenu
                    }
                }

                //TODO 处理之后 判断教程的启动
                SharedPreferences sp = getSharedPreferences(TaskConstants.SP_NAME, MODE_PRIVATE);
                //获取上一次的版本号
                String lastver = sp.getString(TaskConstants.SP_KEY_GUIDE_FIRST_RUN, null);
                String versionName = PackageUtil.getPackageVersionName(this);
                Intent intent = null;
                if(!versionName.equals(lastver)){
                    //TODO 显示教程
                    intent = new Intent(this,GuideActivity.class);
                }else {
                    //TODO 显示主界面
                    intent = new Intent(this,MainActivity.class);
                }
                startActivity(intent);
                finish();
                //使用API 11以上
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
        }

    }
}
