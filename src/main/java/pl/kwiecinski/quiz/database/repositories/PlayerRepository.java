package pl.kwiecinski.quiz.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kwiecinski.quiz.database.entities.PlayerEntity;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

}
