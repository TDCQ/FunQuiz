package com.example.android.funquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 计算分数
     * @param view 传入触发事件的视图
     */
    public void calculateScore(View view) {
        int score = 0;
        String errorMessage = "";

        // 问题1：答对得10分
        RadioButton singleSelectButton = (RadioButton) findViewById(R.id.rb_question_1_answer);
        Boolean isChecked = singleSelectButton.isChecked();
        if (isChecked) {
            score += 10;
        } else {
            errorMessage += getString(R.string.first_quiz_error_explain);
        }

        // 问题2：答对得10分
        singleSelectButton = (RadioButton) findViewById(R.id.rb_question_2_answer);
        isChecked = singleSelectButton.isChecked();
        if (isChecked) {
            score += 10;
        } else {
            errorMessage += getString(R.string.second_quiz_error_explain);
        }

        // 问题3：答对得10分
        singleSelectButton = (RadioButton) findViewById(R.id.rb_question_3_answer);
        isChecked = singleSelectButton.isChecked();
        if (isChecked) {
            score += 10;
        } else {
            errorMessage += getString(R.string.third_quiz_error_explain);
        }

        // 问题4：答对得10分
        singleSelectButton = (RadioButton) findViewById(R.id.rb_question_4_answer);
        isChecked = singleSelectButton.isChecked();
        if (isChecked) {
            score += 10;
        } else {
            errorMessage += getString(R.string.fourth_quiz_error_explain);
        }

        // 问题5：多选题（本题20分），本题共有三个选项是正确选项，答对一个选项得5分，所有选项都答对额外加至满分20分;选择了错误选项不得分;不答不給分也不扣分。
        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_question_5_answer_1);
        Boolean isChecked_5_1 = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.cb_question_5_answer_2);
        Boolean isChecked_5_2 = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.cb_question_5_answer_3);
        Boolean isChecked_5_3 = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.cb_question_5_answer_4);
        Boolean isChecked_5_4 = checkBox.isChecked();
        // isChecked_5_4 是唯一的错误选项，如果选择了此项，就以0分记
        if (!isChecked_5_4) {
            if (isChecked_5_1) {
                score += 5;
            }
            if (isChecked_5_2) {
                score += 5;
            }
            if (isChecked_5_3) {
                score += 5;
            }
            if (isChecked_5_1 && isChecked_5_2 && isChecked_5_3) {
                score += 5;
            } else {
                errorMessage += getString(R.string.fifth_quiz_error_explain);
            }
        } else {
            errorMessage += R.string.fifth_quiz_error_explain;
        }

        // 问题6: 多选题（本题20分），本题共由三个选项是正确选项，答对一个选项得5分，所有选项都答对额外加至满分20分;选择了错误选项不得分; 不答不給分也不扣分。
        checkBox = (CheckBox) findViewById(R.id.cb_question_6_answer_1);
        Boolean isCheckedOfQuizSixOptionsOne = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.cb_question_6_answer_2);
        Boolean isCheckedOfQuizSixOptionsTwo = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.cb_question_6_answer_3);
        Boolean isCheckedOfQuizSixOptionsThree = checkBox.isChecked();
        checkBox = (CheckBox) findViewById(R.id.cb_question_6_answer_4);
        Boolean isCheckedOfQuizSixOptionsFour = checkBox.isChecked();
        // isCheckedOfQuizSixOptionsThree 是唯一的错误选项，如果选择了此项，就以0分记
        if (!isCheckedOfQuizSixOptionsThree) {
            if (isCheckedOfQuizSixOptionsOne && isCheckedOfQuizSixOptionsTwo && isCheckedOfQuizSixOptionsFour) {
                score += 20;
            } else {
                errorMessage += getString(R.string.sixth_quiz_error_exlain_sn);
                if (isCheckedOfQuizSixOptionsOne) {
                    score += 5;
                } else {
                    errorMessage += getString(R.string.sixth_quiz_error_explain_part_1);
                }
                if (isCheckedOfQuizSixOptionsTwo) {
                    score += 5;
                } else {
                    errorMessage += getString(R.string.sixth_quiz_error_explain_part_2);
                }
                if (isCheckedOfQuizSixOptionsFour) {
                    score += 5;
                } else {
                    errorMessage += getString(R.string.sixth_quiz_error_explain_part_3);
                }
            }
        } else {
            errorMessage += getString(R.string.sixth_quiz_error_explain_full);
        }

        //  问题7: 问答题（本题20分）， 过滤用户的输入，把替换可能的空格, 逗号，冒号分隔符后，忽略大小写，忽略两个字母的顺序比较是否等于"cd";
        EditText editText = (EditText) findViewById(R.id.et_question_7_answer);
        String inputAnswer = editText.getText().toString().toLowerCase();
        inputAnswer = inputAnswer.replace(" ", "").replace(",", "").replace(":", "").replace("，", "");
        if (inputAnswer.equals("cd") || inputAnswer.equals("dc")) {
            score += 20;
        } else {
            errorMessage += getString(R.string.seventh_quiz_error_explain);
        }

        // 调用自定义toast, 显示成绩单详细信息
        toast(score, errorMessage);
    }

    private void toast(int score, String errorMessage){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        // 设置输出总分
        TextView scoreView =  layout.findViewById(R.id.score);
        scoreView.setText(layout.getResources().getQuantityString(R.plurals.total_score_prefix, score, score));

        // 设置输出错误信息
        TextView resultMessageTip = layout.findViewById(R.id.resultMessageTip);
        if (errorMessage.length() == 0) {
            resultMessageTip.setVisibility(View.INVISIBLE);
        } else {
            resultMessageTip.setVisibility(View.VISIBLE);
        }

        // 更新评审结果
        TextView resultMessageView = layout.findViewById(R.id.resultMessage);
        resultMessageView.setText(errorMessage);

        // 设置自定义toast
        Toast toast = new Toast(getApplicationContext());
        toast.setMargin(0.7f, 0.7f);
        toast.setGravity(Gravity.FILL | Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }
}
