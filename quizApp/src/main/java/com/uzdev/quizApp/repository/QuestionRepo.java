package com.uzdev.quizApp.repository;

import com.uzdev.quizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question,Long> {
}
