package com.example.test.bean;


import java.util.List;

public class QuestionBean {

    /**
     * title : 下列表示Toast较长时间显示的是
     * rightAnswer :
     * answer : [" Toast.LENGTH_LONG","Toast.LONG","Toast.LENGTH_SHORT","Toast.SHORT"]
     */

    private String title;
    private String rightAnswer;
    private List<String> answer;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }
}
