package com.example.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    ImageSwitcher is;
    Button btn;
    int img[] = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5};
    int count = img.length;
    int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        is = findViewById(R.id.simpleImageSwitcher);
        btn = findViewById(R.id.buttonNext);
        //set the viewfactory of the ImageSwitcher that will create ImageView Object when asked
        is.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());//base is a contxt parent class
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });
        //declare in and out animations and load them using AnimationUtil class
        Animation in= AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);

        //set the animation type to ImageSwitcher
        is.setInAnimation(in);
        is.setInAnimation(out);

        //clickListener for Next button
        //when clicked  on button ImageSwitcher will switch between image
        //the current image will be go OUT and next image will come in with specified animation

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex++;
                if (currentIndex == count)
                    currentIndex = 0;
                is.setImageResource(img[currentIndex]);
            }

        });
    }
}
