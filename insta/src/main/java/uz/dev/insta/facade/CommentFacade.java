package uz.dev.insta.facade;

import org.springframework.stereotype.Component;
import uz.dev.insta.dto.CommentDTO;
import uz.dev.insta.model.Comment;

@Component
public class CommentFacade {

    public CommentDTO commentToCommentDTO(Comment comment){

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setMessage(comment.getMessage());
        commentDTO.setUsername(comment.getUsername());

        return commentDTO;
    }

}
