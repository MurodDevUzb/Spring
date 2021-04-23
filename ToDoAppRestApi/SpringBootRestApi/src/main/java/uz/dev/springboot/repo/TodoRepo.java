package uz.dev.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.dev.springboot.models.TodoItem;

public interface TodoRepo extends JpaRepository<TodoItem,Long> {
}
