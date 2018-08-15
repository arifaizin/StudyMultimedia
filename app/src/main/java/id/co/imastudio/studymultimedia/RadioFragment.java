package id.co.imastudio.studymultimedia;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class RadioFragment extends Fragment {


    public RadioFragment() {
        // Required empty public constructor
    }


    Button play, stop;
    MediaPlayer player;
    Animation animfade;
    ProgressBar progressBar;

    //jnj

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_radio, container, false);
        play = (Button) fragmentView.findViewById(R.id.btnPlay);
        stop = (Button) fragmentView.findViewById(R.id.btnStop);

        animfade = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_anim);

        progressBar = (ProgressBar) fragmentView.findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);

        stop.setVisibility(View.GONE);
        progressBar.setVisibility(View.INVISIBLE);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Toast di Fragment", Toast.LENGTH_SHORT).show();
                try {
                    player = new MediaPlayer();
                    player.setDataSource("http://103.16.198.36:9160/stream");
                    //http://gajahmada.i.streaming.id:8000/live
                    player.prepareAsync();
                    player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            player.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e){
                    e.printStackTrace();
                }
                play.setVisibility(View.GONE);
                stop.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);

//                stop.startAnimation(animfade);
                YoYo.with(Techniques.Flash)
                        .duration(700)
                        .repeat(1)
                        .playOn(stop);
            }
        });


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (player.isPlaying()){
                        player.stop();
                    }
                } catch (IllegalStateException e){
                    e.printStackTrace();
                }

                play.setVisibility(View.VISIBLE);
                stop.setVisibility(View.GONE);
                progressBar.setVisibility(View.INVISIBLE);

//                play.startAnimation(animfade);
                YoYo.with(Techniques.Pulse)
                        .duration(700)
                        .repeat(1)
                        .playOn(play);
            }
        });

        return fragmentView;
    }


}
