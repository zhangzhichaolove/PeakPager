package pager.peakchao.com.peakpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = findViewById(R.id.pager);

        pager.setAdapter(new MovePagerAdapter());

        pager.setPageTransformer(true, new Transformer());
    }


    private class MovePagerAdapter extends PagerAdapter {
        private int[] images = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_pager, null);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

    private class Transformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(@NonNull View view, float position) {
            if (position < -1 || position > 1) {
                view.setAlpha(0);
            } else {
                if (position < 0) {//主屏幕从右到左边消失
                    view.setAlpha(1 - Math.abs(position));
                } else if (position > 0) {//右边到左边新进来的视图||左边到右边出去的视图
                    view.setAlpha(1 - Math.abs(position));
                    view.findViewById(R.id.iv).setTranslationX(view.getWidth() * position);
                }
            }
            Log.e("TAG", view + ":" + position);

        }
    }


}
