package pl.kwiecinski.quiz.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.kwiecinski.quiz.model.dao.CategoryDao;

import java.util.List;

@NoArgsConstructor
@Getter
@ToString
public class CategoriesDto {

    @JsonProperty("trivia_categories")
    private List<CategoryDao> categories;

}
