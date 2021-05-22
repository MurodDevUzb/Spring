package uz.dev.insta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.dev.insta.model.Post;
import uz.dev.insta.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByUserOOrderByCreatedDateDesc(User user);
    List<Post> findAllByOOrderByCreatedDateDesc();
    Optional<Post> findPostByIdAAndUser(Long id, User user);
}
