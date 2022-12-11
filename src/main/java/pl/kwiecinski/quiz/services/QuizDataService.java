package pl.kwiecinski.quiz.services;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.kwiecinski.quiz.frontend.GameOptions;
import pl.kwiecinski.quiz.model.dao.CategoryDao;
import pl.kwiecinski.quiz.model.dao.QuestionDao;
import pl.kwiecinski.quiz.model.dto.CategoriesDto;
import pl.kwiecinski.quiz.model.dto.QuestionsDto;

import java.net.URI;
import java.util.List;

@Log
@Service
public class QuizDataService {

    public List<CategoryDao> getQuizCategories() {
        RestTemplate restTemplate = new RestTemplate();
        CategoriesDto result = restTemplate.getForObject("https://opentdb.com/api_category.php", CategoriesDto.class);
        log.info("Quiz categories: " + result.getCategories());
        return result.getCategories();
    }

    public List<QuestionDao> getQuizQuestions(GameOptions gameOptions) {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = UriComponentsBuilder.fromHttpUrl("https://opentdb.com/api.php")
                .queryParam("amount", gameOptions.getNumberOfQuestions())
                .queryParam("category", gameOptions.getCategoryId())
                .queryParam("difficulty", gameOptions.getDifficulty().name().toLowerCase())
                .build().toUri();
        log.info("Quiz question retrieve URL: " + uri);

        QuestionsDto result = restTemplate.getForObject(uri, QuestionsDto.class);
        log.info("Quiz questions: " + result.getResults());

        return result.getResults();
    }
}
