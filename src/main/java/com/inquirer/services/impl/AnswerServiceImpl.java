package com.inquirer.services.impl;

import com.inquirer.dao.AnswerRepository;
import com.inquirer.models.Answer;
import com.inquirer.models.Question;
import com.inquirer.services.AnswerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Resource
    private AnswerRepository answerRepository;
    private List<Answer> userAnswers;

    private static final Logger LOGER = Logger.getLogger(AnswerServiceImpl.class);

    public AnswerServiceImpl(){
        userAnswers = new ArrayList<>();
    }

    @Override
    public List<Answer> getAnswersForQuestion(Question question){
        try {
            return answerRepository.getAnswersForQuestion(question.getId());
        } catch (SQLException e) {
            return new ArrayList<Answer>();
        }
    }

    @Override
    public Answer getAnswerById(int id) throws SQLException {
        return answerRepository.findOne(id);
    }

    @Override
    public void setUserAnswer(int id) {
        try {
            userAnswers.add(getAnswerById(id));
        } catch (SQLException e) {
            LOGER.error(e);
        }
    }

    @Override
    public int getTestResult() {
        int correctAnswers = 0;
        for (Answer currentAnswer:userAnswers) {
            if (currentAnswer.isRight())
                correctAnswers++;
        }
        return correctAnswers;
    }

    @Override
    public int getUserAnswerIdByQuestionNumber(int number) {
        Answer userAnswer = new Answer();
        for (Answer tmpAnswer : userAnswers) {
            if (tmpAnswer.getQuestion() == number) {
                userAnswer = tmpAnswer;
            }
        }
            return userAnswer.getId();
    }

    @Override
    public void clearUserAnswers() {
        userAnswers = new ArrayList<>();
    }

    @Override
    public int getUserAnswersAmount() {
        return userAnswers.size();
    }
}
