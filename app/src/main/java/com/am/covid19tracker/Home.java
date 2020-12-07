package com.am.covid19tracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.w3c.dom.Text;

public class Home extends Fragment {
    View view;
    ViewPager viewPager;
    ExtendedFloatingActionButton callhelpline;
    NestedScrollView nestedScrollView;
    ImageView india_img,world_img,symp1,symp2,symp3,symp4,symp5,prev1,prev2,prev3,prev4;
    TextView india_info,world_info,symp1_info,Symp2_info,symp3_info,symp4_info,symp5_info,prev1_info,prev2_info,prev3_info,prev4_info,helplinenumber;
    Button buttonindia,buttonworld,cancel,call,ok;
    Spinner spinnerstatelist;
    Context mycontext;
    ImageButton go;
    YouTubePlayerView youTubePlayerView1,youTubePlayerView2,youTubePlayerView3;
    FloatingActionMenu floatingActionMenu;
    com.github.clans.fab.FloatingActionButton share,info,feedback;


    String statenames[]={"Andaman & Nicobar","Andhra Pradesh","Arunachal Pradesh","West Bengal"};
    int checkedItem = 1;

    public Home() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.home,container,false);
        nestedScrollView=(NestedScrollView)view.findViewById(R.id.homenested_scrolllview);
        callhelpline=(ExtendedFloatingActionButton)view.findViewById(R.id.call_helpline);
        viewPager=(ViewPager)getActivity().findViewById(R.id.view_pager);
        mycontext=container.getContext();
        go=view.findViewById(R.id.go);
        floatingActionMenu=view.findViewById(R.id.floating_menu);
        share=view.findViewById(R.id.floating_item1);
        info=view.findViewById(R.id.floating_item2);
        feedback=view.findViewById(R.id.floating_item3);


        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY>oldScrollY)
                {
                    callhelpline.shrink();
                    floatingActionMenu.hideMenu(true);
                }
                else
                {
                    callhelpline.extend();
                    floatingActionMenu.showMenu(true);
                }
            }
        });

        setTab();

        setVideos();

        setSymptoms();

        setPrevention();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mycontext,NewsFeed.class);
                startActivity(intent);
            }
        });

        callhelpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final AlertDialog.Builder builder=new AlertDialog.Builder(mycontext);
               View callview=getLayoutInflater().inflate(R.layout.alertbox_layout,null,false);
               cancel=(Button)callview.findViewById(R.id.cancel);
               call=(Button)callview.findViewById(R.id.call);
               spinnerstatelist=(Spinner)callview.findViewById(R.id.state_list);
               helplinenumber=(TextView)callview.findViewById(R.id.number);

               builder.setView(callview);
               final AlertDialog alertDialog=builder.create();
               cancel.setBackgroundColor(getResources().getColor(R.color.colorRed));
               call.setBackgroundColor(getResources().getColor(R.color.colorGreen));

               spinnerstatelist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                       String name=adapterView.getItemAtPosition(i).toString();
                       switch (name) {
                           case "Central Helpline":helplinenumber.setText("011-23978046"); break;
                           case "Andaman and Nicobar":helplinenumber.setText("03192-232102"); break;
                           case "Andhra Pradesh":helplinenumber.setText("0866-2410978"); break;
                           case "Arunachal Pradesh":helplinenumber.setText("9436055743"); break;
                           case "Assam":helplinenumber.setText("6913347770"); break;
                           case "Bihar":helplinenumber.setText("104"); break;
                           case "Chandigarh":helplinenumber.setText("9779558282"); break;
                           case "Chhattisgarh":helplinenumber.setText("104");break;
                           case "Dadra Nagar Haveli":helplinenumber.setText("104");break;
                           case "Delhi":helplinenumber.setText("011-22307145");break;
                           case "Goa":helplinenumber.setText("104");break;
                           case "Gujarat":helplinenumber.setText("104");break;
                           case "Haryana":helplinenumber.setText("8558893911");break;
                           case "Himachal Pradesh":helplinenumber.setText("104");break;
                           case "Jammu":helplinenumber.setText("01912520982");break;
                           case "Jharkhand":helplinenumber.setText("104");break;
                           case "Karnataka":helplinenumber.setText("104");break;
                           case "Kashmir":helplinenumber.setText("01942440283");break;
                           case "Kerala":helplinenumber.setText("0471-2552056");break;
                           case "Ladakh":helplinenumber.setText("01982256462");break;
                           case "Lakshadweep":helplinenumber.setText("104");break;
                           case "Madhya Pradesh":helplinenumber.setText("104");break;
                           case "Maharashtra":helplinenumber.setText("020-26127394");break;
                           case "Manipur":helplinenumber.setText("3852411668");break;
                           case "Meghalaya":helplinenumber.setText("108");break;
                           case "Mizoram":helplinenumber.setText("102");break;
                           case "Nagaland":helplinenumber.setText("7005539653");break;
                           case "Odisha":helplinenumber.setText("9439994859");break;
                           case "Puducherry":helplinenumber.setText("104");break;
                           case "Punjab":helplinenumber.setText("104");break;
                           case "Rajasthan":helplinenumber.setText("0141-2225624");break;
                           case "Sikkim":helplinenumber.setText("104");break;
                           case "Tamil Nadu":helplinenumber.setText("044-29510500");break;
                           case "Telangana":helplinenumber.setText("104");break;
                           case "Tripura":helplinenumber.setText("0381-2315879");break;
                           case "Uttarakhand":helplinenumber.setText("104");break;
                           case "Uttar Pradesh":helplinenumber.setText("18001805145");break;
                           case "West Bengal 1st No":helplinenumber.setText("1800313444222");break;
                           case "West Bengal 2nd No":helplinenumber.setText("03323412600");break;
                       }
                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> adapterView) {

                   }
               });

               cancel.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       alertDialog.dismiss();
                   }
               });
               call.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       String numbrer="tel:"+helplinenumber.getText().toString();
                       Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse(numbrer));
                       startActivity(intent);
                   }
               });
                alertDialog.getWindow().getAttributes().windowAnimations=R.style.alertbox_animation;
               alertDialog.show();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(mycontext);
                View infoview=getLayoutInflater().inflate(R.layout.appinfo,null,false);
                ok=infoview.findViewById(R.id.ok);
                builder.setView(infoview);
                final AlertDialog alertDialog=builder.create();
                ok.setBackgroundColor(getResources().getColor(R.color.colorGreen));

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.getWindow().getAttributes().windowAnimations=R.style.alertbox_animation;
                alertDialog.show();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share=new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT,"Covid-19 Tracker");
                share.putExtra(Intent.EXTRA_TEXT,"Download and install via https://drive.google.com/file/d/1fNtn1WSv3ql5AQRWAE_i60DvKpal9tJ3/view?usp=sharing");
                startActivity(Intent.createChooser(share,"Share Via"));
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedbackandreport=new Intent(Intent.ACTION_SEND);
                feedbackandreport.putExtra(Intent.EXTRA_EMAIL, new String[]{"alokmaity2k20@gmail.com"});
                feedbackandreport.putExtra(Intent.EXTRA_SUBJECT, "Feedback or Bug Report");
                feedbackandreport.putExtra(Intent.EXTRA_TEXT, "Please describe your feedback or bug report here\n");
                feedbackandreport.setType("message/rfc822");
                startActivity(Intent.createChooser(feedbackandreport, "Choose an Email client :"));




            }
        });

        return view;
    }

    private void setVideos() {
        View video1=view.findViewById(R.id.video1);
        youTubePlayerView1=video1.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView1);
        youTubePlayerView1.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "v-852f1PXBo";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });
        View video2=view.findViewById(R.id.video2);
        youTubePlayerView2=video2.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView2);
        youTubePlayerView2.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "lMr6TXWN5Pk";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });
        View video3=view.findViewById(R.id.video3);
        youTubePlayerView3=video3.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView3);
        youTubePlayerView3.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "W4HDj2CcPAM";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });


    }

    private void setTab() {
        View tabindia=view.findViewById(R.id.tab_india);
        india_img=(ImageView)tabindia.findViewById(R.id.tab_image);
        india_info=(TextView)tabindia.findViewById(R.id.tab_title);
        buttonindia=(Button)tabindia.findViewById(R.id.tab_button);

        View tabworld=view.findViewById(R.id.tab_world);
        world_img=(ImageView)tabworld.findViewById(R.id.tab_image);
        world_info=(TextView)tabworld.findViewById(R.id.tab_title);
        buttonworld=(Button)tabworld.findViewById(R.id.tab_button);

        india_img.setImageResource(R.drawable.india);
        world_img.setImageResource(R.drawable.earth);
        india_info.setText(getString(R.string.india_corona));
        world_info.setText(getString(R.string.world_corona));

        buttonindia.setBackgroundColor(Color.TRANSPARENT);
        buttonworld.setBackgroundColor(Color.TRANSPARENT);

        buttonindia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });
        buttonworld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });

    }


    private void setSymptoms() {
        View symptom1=view.findViewById(R.id.symptom1);
        symp1=(ImageView)symptom1.findViewById(R.id.info_image);
        symp1_info=(TextView)symptom1.findViewById(R.id.info_title);

        View symptom2=view.findViewById(R.id.symptom2);
        symp2=(ImageView)symptom2.findViewById(R.id.info_image);
        Symp2_info=(TextView)symptom2.findViewById(R.id.info_title);

        View symptom3=view.findViewById(R.id.symptom3);
        symp3=(ImageView)symptom3.findViewById(R.id.info_image);
        symp3_info=(TextView)symptom3.findViewById(R.id.info_title);

        View symptom4=view.findViewById(R.id.symptom4);
        symp4=(ImageView)symptom4.findViewById(R.id.info_image);
        symp4_info=(TextView)symptom4.findViewById(R.id.info_title);

        View symptom5=view.findViewById(R.id.symptom5);
        symp5=(ImageView)symptom5.findViewById(R.id.info_image);
        symp5_info=(TextView)symptom5.findViewById(R.id.info_title);

        symp1.setImageResource(R.drawable.symp1);
        symp2.setImageResource(R.drawable.symp2);
        symp3.setImageResource(R.drawable.symp3);
        symp4.setImageResource(R.drawable.symp4);
        symp5.setImageResource(R.drawable.symp5);
        symp1_info.setText(getString(R.string.symp1));
        Symp2_info.setText(getString(R.string.symp2));
        symp3_info.setText(getString(R.string.symp3));
        symp4_info.setText(getString(R.string.symp4));
        symp5_info.setText(getString(R.string.symp5));
    }
    private void setPrevention() {
        View prevention1=view.findViewById(R.id.prev1);
        prev1=(ImageView)prevention1.findViewById(R.id.info_image);
        prev1_info=(TextView)prevention1.findViewById(R.id.info_title);

        View prevention2=view.findViewById(R.id.prev2);
        prev2=(ImageView)prevention2.findViewById(R.id.info_image);
        prev2_info=(TextView)prevention2.findViewById(R.id.info_title);

        View prevention3=view.findViewById(R.id.prev3);
        prev3=(ImageView)prevention3.findViewById(R.id.info_image);
        prev3_info=(TextView)prevention3.findViewById(R.id.info_title);

        View prevention4=view.findViewById(R.id.prev4);
        prev4=(ImageView)prevention4.findViewById(R.id.info_image);
        prev4_info=(TextView)prevention4.findViewById(R.id.info_title);

        prev1.setImageResource(R.drawable.prev1);
        prev2.setImageResource(R.drawable.prev2);
        prev3.setImageResource(R.drawable.prev3);
        prev4.setImageResource(R.drawable.prev4);
        prev1_info.setText(getString(R.string.prev1));
        prev2_info.setText(getString(R.string.prev2));
        prev3_info.setText(getString(R.string.prev3));
        prev4_info.setText(getString(R.string.prev4));
    }
}
