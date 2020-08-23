package com.example.guess;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private EditText gues_fld;
    private Button gues_btn;
    private Button answer;
    private TextView answer_lb;
    private TextView info;
    private int gues;
    private int previos=0;
    private int count=1;
    private int theNumber;// = (int) (Math.random() * 100.0D + 1.0D);
    boolean won;
    String info_msg="";
    int color= Color.BLACK;


    public void new_game(){
        theNumber=(int) (Math.random() * 100.0D + 1.0D);
        //.setText(theNumber);
    }
    public void exit_app() {
        System.exit(0);
    }

    public void check_guess(){
        color=Color.BLACK;
        try {
            gues = Integer.parseInt(gues_fld.getText().toString());
            if ((gues>=0&gues<=100)&previos != gues) {
                if (theNumber == gues) {
                    //info.setText("Вы угадали! Попыток: " + count);
                    info_msg="Вы угадали! Попыток: " + count;
                    info.setText(info_msg);
                    //info.setIcon(new ImageIcon("congrats.png"));
                    gues_btn.setText("Выход");
                    won = true;
                } else if (gues < theNumber) {
                    //info.setText("Загаданное число больше! Попыток: " + count);
                    info_msg="Загаданное число больше! Попыток: " + count;
                    info.setText(info_msg);
                    //info.setIcon(new ImageIcon("up.png"));
                } else {
                    //info.setText("Загаданное число меньше! Попыток: " + count);
                    info_msg="Загаданное число меньше! Попыток: " + count;
                    info.setText(info_msg);
                    //info.setIcon(new ImageIcon("down.png"));
                }
                ++count;
            } else if (0>gues|gues>100) {
                //info.setText("Ответ должен быть числом из диапазона 0-100");
                info_msg="Ответ должен быть числом из диапазона 0-100";
                color=Color.RED;
                info.setText(info_msg);
                // info.setIcon(new ImageIcon("range.png"));
                //info.setForeground(Color.RED);
                //info.setTextColor(#3F51B5);
            }
            else {
                //info.setText("Повторный ответ!");
                info_msg="Повторный ответ!";
                color=Color.RED;
                info.setText(info_msg);
                // info.setIcon(new ImageIcon("wrong.png"));
                // info.setForeground(Color.RED);
            }
            previos = gues;
        } catch (NumberFormatException ex) {
            //info.setText("Ответ должен быть числом из диапазона 0-100");
            info_msg="Ответ должен быть числом из диапазона 0-100";
            color=Color.RED;
            info.setText(info_msg);
            // info.setIcon(new ImageIcon("no_let.png"));
            // info.setForeground(Color.RED);
        }
        gues_fld.requestFocus();
        gues_fld.selectAll();
        //info.setText(info_msg);
        info.setTextColor(color);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gues_fld = (EditText) findViewById(R.id.gues_fld);
        gues_btn = (Button) findViewById(R.id.gues_btn);
        answer=(Button)findViewById(R.id.answer);
        answer_lb=(TextView)findViewById(R.id.answer_lb);
        info = (TextView) findViewById(R.id.info);
        new_game();
        gues_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!won) {
                    check_guess();
                } else {
                    exit_app();
                }
            }
        });
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_lb.setText(theNumber+"");
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}