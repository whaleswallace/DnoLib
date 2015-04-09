package com.dnocode.lib.appsample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dnocode.lib.business.utility.Teacher;

public class MainActivity extends ActionBarActivity  implements View.OnClickListener{

    private final int sLessonCardId=32323;
    private LinearLayout mMainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mMainContainer=new LinearLayout(this);
        mMainContainer.setOrientation(LinearLayout.VERTICAL);
        addLink(HolderPatternActivity.class);
        addLink(CarouselActivity.class);
        addLink(BrowseCardActivity.class);
        addLink(SingleFadeImageActivity.class);
        setContentView(mMainContainer);

                Teacher.instance(this)
                .addLessonCard("prova", "descrizione", -1, "red")
                .showOnActivityStart(SingleFadeImageActivity.class)
                .addDependency(CarouselActivity.class);

                Teacher.instance(this)
                .addLessonCard("seconda lezione", "descrizione", -1, "black").executeFor(3)
                .showOnActivityStart(CarouselActivity.class);


   }

    private void addLink(Class target){

        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Button btn=new Button(this);
        btn.setText(target.getSimpleName().toLowerCase());
        btn.setOnClickListener(this);
        btn.setTag(target);
        btn.setLayoutParams(lp);
        mMainContainer.addView(btn);

    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(this, (Class<?>) v.getTag());
        this.startActivity(i);
    }
}
