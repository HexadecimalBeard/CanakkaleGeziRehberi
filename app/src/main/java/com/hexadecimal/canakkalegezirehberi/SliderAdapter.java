package com.hexadecimal.canakkalegezirehberi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.hexadecimal.canakkalegezirehberi.R;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;

    }

    public int[] slide_images = {

            R.mipmap.onboarding_login,
            R.mipmap.onboarding_myroutes,
            R.mipmap.onboarding_zafer
    };

    public String[] slide_headings = {

            "Giriş yap ya da kaydol",
            "Rotalarım",
            "Çanakkale Zaferi",
    };

    public String[] slide_descriptions = {

            "Kullanıcı girişi yaparak \n kendi oluşturduğun rotalara erişebilirsin.",
            "Rotalarım kısmından satın aldığın \n anıtlarla kendine özel rotalarını \n oluşturabilirsin.",
            "Savaş hakkında kısmından \n tarihi zafer hakkında yeni bilgiler \n öğrenebilirsin.",
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.onboarding_slide1_layout, container,false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.sliderImg_onboarding);
        TextView slideHeading = (TextView) view.findViewById(R.id.sliderHeading_onboarding);
        TextView slideDescription= (TextView) view.findViewById(R.id.sliderDesc_onboarding);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descriptions[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ConstraintLayout)object);
    }
}
