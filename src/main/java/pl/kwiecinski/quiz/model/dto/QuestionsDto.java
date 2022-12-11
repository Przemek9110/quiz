package pl.kwiecinski.quiz.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.kwiecinski.quiz.model.dao.QuestionDao;

import java.util.List;

@NoArgsConstructor
@Getter
public class QuestionsDto {

    private int response_code;
    private List<QuestionDao> results;
}
