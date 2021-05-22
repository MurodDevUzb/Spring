package uz.dev.insta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.dev.insta.model.Comment;
import uz.dev.insta.model.Post;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPost(Post post);
    Comment findByIdAndUserId(Long commentId, Long userId);
}
