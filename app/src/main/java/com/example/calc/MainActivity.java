package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialButton plusminusb,clearb;
    MaterialButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0;
    MaterialButton addb,subb,mulb,divb,backb;
    MaterialButton dotb,allclearb,equalb;
    TextView topv,botv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topv=findViewById(R.id.TopText);
        botv=findViewById(R.id.DownText);

        assignID(b0,R.id.btn0);
        assignID(b1,R.id.btn1);
        assignID(b2,R.id.btn2);
        assignID(b3,R.id.btn3);
        assignID(b4,R.id.btn4);
        assignID(b5,R.id.btn5);
        assignID(b6,R.id.btn6);
        assignID(b7,R.id.btn7);
        assignID(b8,R.id.btn8);
        assignID(b9,R.id.btn9);
        assignID(addb,R.id.addbtn);
        assignID(subb,R.id.minusbtn);
        assignID(mulb,R.id.multibtn);
        assignID(divb,R.id.divbtn);
        assignID(backb,R.id.backspacebtn);
        assignID(plusminusb,R.id.plusminusbtn);
        assignID(allclearb,R.id.allclearbtn);
        assignID(clearb,R.id.clearbtn);
        assignID(dotb,R.id.dotbtn);
        assignID(equalb,R.id.ansbtn);



    }
    void assignID(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }


    public void onClick(View view){
        MaterialButton button=(MaterialButton) view;


        String btntext=button.getText().toString();
        String calculatedata=topv.getText().toString();

        if(btntext.equals("AC")){
            topv.setText("");
            botv.setText("0");
            return;
        }

        if((btntext.equals("C"))) {
            botv.setText("");
            return;
        }
        if(btntext.equals("DEL")){
            calculatedata=calculatedata.substring(0,calculatedata.length()-1);
        }else{
            calculatedata=calculatedata+btntext;
        }
        if(btntext.equals("=")){
            topv.setText(botv.getText());
            return;
        }
        topv.setText(calculatedata);

        String finalresult = getresult(calculatedata);
        if (!finalresult.equals("Error")) {
            botv.setText(finalresult);
        }

    }
    String getresult(String data){
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalresult= context.evaluateString(scriptable,data,"JavaScript",1,null).toString();
            if(finalresult.endsWith(".0")){
                finalresult=finalresult.replace(".0","");
            }
            return finalresult;

        }catch(Exception e){
            return "Error";
        }
    }

}