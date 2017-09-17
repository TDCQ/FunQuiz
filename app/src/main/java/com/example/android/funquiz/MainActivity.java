package com.example.android.funquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void calculateScore(View view) {
        int score = 0;
        String errorMessage = "";

        // 问题1：答对得10分
        RadioButton singleSelectButton = (RadioButton) findViewById(R.id.question_1_answer);
        Boolean isChecked = singleSelectButton.isChecked();
        if(isChecked){
            score += 10;
        }else {
            errorMessage += "1、 咫：周制八寸,合今制市尺六寸二分二厘，尺：一尺等于10寸，所以尺比咫大。\n";
        }

        // 问题2：答对得10分
        singleSelectButton = (RadioButton) findViewById(R.id.question_2_answer);
        isChecked = singleSelectButton.isChecked();
        if(isChecked){
            score += 10;
        } else {
            errorMessage += "\n2、 司马迁写作《史记》以“本纪”叙帝王，以“世家”载诸侯，以“列传”记人物，以“书”述典章制度，以“表”排列大事。\n";
        }

        // 问题3：答对得10分
        singleSelectButton = (RadioButton) findViewById(R.id.question_3_answer);
        isChecked = singleSelectButton.isChecked();
        if(isChecked){
            score += 10;
        } else {
            errorMessage += "\n3、 这个成语来自刘禹锡的诗《赠李司空妓》，“司空见惯浑闲事，断尽江南刺史肠。”司空指当时的诗人李绅，职位为司空，相当于明清两代的尚书。\n";
        }

        // 问题4：答对得10分
        singleSelectButton = (RadioButton) findViewById(R.id.question_4_answer);
        isChecked = singleSelectButton.isChecked();
        if(isChecked){
            score += 10;
        } else {
            errorMessage += "\n4、 李白《梦游天姥吟留别》中有“安能摧眉折腰事权贵，使我不得开心颜”。\n";
        }

        // 问题5：答对一个选项得5分，答错不得分
        CheckBox checkBox = (CheckBox) findViewById(R.id.question_5_answer_1);
        Boolean isChecked_5_1 = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.question_5_answer_2);
        Boolean isChecked_5_2 = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.question_5_answer_3);
        Boolean isChecked_5_3 = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.question_5_answer_4);
        Boolean isChecked_5_4 = checkBox.isChecked();
        if(!isChecked_5_4){
            if(isChecked_5_1){
                score += 5;
            }
            if(isChecked_5_2){
                score += 5;
            }
            if(isChecked_5_3){
                score += 5;
            }
            if(isChecked_5_1 && isChecked_5_2 && isChecked_5_3){
                score += 5;
            } else {
                errorMessage += "\n5、 五湖指洞庭湖、鄱阳湖、太湖、巢湖、洪泽湖。\n";
            }
        } else {
            errorMessage += "\n5、 五湖指洞庭湖、鄱阳湖、太湖、巢湖、洪泽湖。\n";
        }

        // 问题6: 答对一个选项得5分，答错不得分
        checkBox = (CheckBox) findViewById(R.id.question_6_answer_1);
        Boolean isCheckedOfQuizSixOptionsOne = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.question_6_answer_2);
        Boolean isCheckedOfQuizSixOptionsTwo = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.question_6_answer_3);
        Boolean isCheckedOfQuizSixOptionsThree = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.question_6_answer_4);
        Boolean isCheckedOfQuizSixOptionsFour = checkBox.isChecked();
        if(!isCheckedOfQuizSixOptionsThree) {
            if (isCheckedOfQuizSixOptionsOne && isCheckedOfQuizSixOptionsTwo && isCheckedOfQuizSixOptionsFour) {
                score += 20;
            } else {
                errorMessage += "\n6、";
                if (isCheckedOfQuizSixOptionsOne) {
                    score += 5;
                } else {
                    errorMessage += "清代作家吴敬梓笔下的《儒林外史》严监生\n";
                }
                if (isCheckedOfQuizSixOptionsTwo) {
                    score += 5;
                } else {
                    errorMessage += "法国作家巴尔扎克笔下的《欧也妮·葛朗台》葛朗台\n";
                }
                if (isCheckedOfQuizSixOptionsFour) {
                    score += 5;
                } else {
                    errorMessage += "法国作家莫里哀笔下的《吝啬鬼》阿巴贡\n";
                }
            }
        } else {
            errorMessage += "\n6、 清代作家吴敬梓笔下的《儒林外史》严监生\n法国作家巴尔扎克笔下的《欧也妮·葛朗台》葛朗台\n法国作家莫里哀笔下的《吝啬鬼》阿巴贡\n";
        }

        //  问题7
        EditText editText = (EditText) findViewById(R.id.question_7_answer);
        String inputAnswer = editText.getText().toString().toLowerCase();
        inputAnswer = inputAnswer.replace(" ", "").replace(",", "").replace(":", "").replace("，","");
        if(inputAnswer.equals("cd") || inputAnswer.equals("dc")){
            score += 20;
        } else {
            errorMessage += "\n7、 “CD”最多人听！\n";
        }

        // 打印输出总分与错误原因
        TextView scoreView = (TextView) findViewById(R.id.score);
        scoreView.setText("总分是：" + score );
        TextView resultMessageView = (TextView) findViewById(R.id.resultMessage);
        resultMessageView.setText(errorMessage);
        TextView resultMessageTip = (TextView) findViewById(R.id.resultMessageTip);
        if(errorMessage.length() == 0) {
            resultMessageTip.setVisibility(View.INVISIBLE);
        } else {
            resultMessageTip.setVisibility(View.VISIBLE);
        }
    }
}
