package usmanali.mobileworld.Activity_Files;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import usmanali.mobileworld.Activity_Files.MainActivity;
import usmanali.mobileworld.R;

public class splash_screen extends AppCompatActivity {
ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        image=(ImageView) findViewById(R.id.splash_screen_image);
        Animation splash_screen_animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_screen_animation);
        image.setAnimation(splash_screen_animation);
        splash_screen_animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
               finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
