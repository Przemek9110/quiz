package pl.kwiecinski.quiz.services;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kwiecinski.quiz.frontend.Difficulty;
import pl.kwiecinski.quiz.frontend.GameOptions;
import pl.kwiecinski.quiz.model.dao.CategoryDao;
import pl.kwiecinski.quiz.model.dao.QuestionDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class OngoingGameService {

    private GameOptions gameOptions;
    private int currentQuestionIndex;
    @Getter
    private int points;
    private List<QuestionDao> questions;
    @Autowired
    private QuizDataService quizDataService;
    public Difficulty getDifficulty() {
        return gameOptions.getDifficulty();
    }

    public void init(GameOptions gameOptions) {
        this.gameOptions = gameOptions;
        this.currentQuestionIndex = 0;
        this.points =0;

        this.questions = quizDataService.getQuizQuestions(gameOptions);
    }
    public int getCurrentQuestionNumber() {
        return currentQuestionIndex+1;
    }

    public int getTotalQuestionNumber() {
        return questions.size();
    }

    public String getCurrentQuestion() {
        QuestionDao dao = questions.get(currentQuestionIndex);
        return dao.getQuestion();
    }

    public List<String> getCurrentQuestionAnswersInRandomOrder() {
        QuestionDao dao = questions.get(currentQuestionIndex);

        List<String> answers = new ArrayList<>();
        answers.add(dao.getCorrectAnswer());
        answers.addAll(dao.getIncorrectAnswers());

        Collections.shuffle(answers);
        return answers;
    }

    public boolean checkAnswerForCurrentQuestionAndUpdatePoints(String userAnswer) {
        QuestionDao dao = questions.get(currentQuestionIndex);
        boolean correct = dao.getCorrectAnswer().equals(userAnswer);
        if (correct) {
            points++;
        }
        return correct;
    }

    public boolean proceedToNextQuestion() {
        currentQuestionIndex++;
        return currentQuestionIndex < questions.size();
    }
    public String getCategoryName() {
        Optional<String> category = quizDataService.getQuizCategories().stream()
                .filter(categoryDao -> categoryDao.getId() == gameOptions.getCategoryId())
                .map(CategoryDao::getName)
                .findAny();
        return category.orElse(null);
    }
}
