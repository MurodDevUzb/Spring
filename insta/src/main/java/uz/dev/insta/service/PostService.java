package uz.dev.insta.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.dev.insta.dto.PostDTO;
import uz.dev.insta.exceptions.PostNotFoundException;
import uz.dev.insta.model.ImageModel;
import uz.dev.insta.model.Post;
import uz.dev.insta.model.User;
import uz.dev.insta.repository.ImageRepository;
import uz.dev.insta.repository.PostRepository;
import uz.dev.insta.repository.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private static final Logger LOG = LoggerFactory.getLogger(PostService.class);

    private PostRepository postRepository;
    private UserRepository userRepository;
    private ImageRepository imageRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, ImageRepository imageRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }


    public Post createPost(PostDTO postDTO,Principal principal){
        User user = getUserByPrincipal(principal);
        Post post = new Post();
        post.setUser(user);
        post.setCaption(postDTO.getCaption());
        post.setLocation(postDTO.getLocation());
        post.setTitle(postDTO.getTitle());
        post.setLikes(0);

        LOG.info("Saving Post for User: {}", user.getEmail());
        return postRepository.save(post);
    }

    public List<Post> getAllPost(){
        return postRepository.findAllByOrderByCreatedDateDesc();
    }

    public Post getPostById(Long postId, Principal principal){
        User user = getUserByPrincipal(principal);
        return postRepository.findPostByIdAndUser(postId,user)
                .orElseThrow(() -> new PostNotFoundException("Post cannot be found for username: " + user.getEmail()));
    }


    public List<Post> getAllPostForUser(Principal principal){
        User user = getUserByPrincipal(principal);
        return postRepository.findAllByUserOrderByCreatedDateDesc(user);
    }

    public Post likePost(Long postId, String username){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post cannot be found"));
        Optional<String> userLiked = post.getLikedUsers()
                .stream()
                .filter(u->u.equals(username)).findAny();

        if(userLiked.isPresent()){
            post.setLikes(post.getLikes()-1);
            post.getLikedUsers().remove(username);
        }else{
            post.setLikes(post.getLikes()+1);
            post.getLikedUsers().add(username);
        }

        return postRepository.save(post);
    }

    public void deletePost(Long postId,Principal principal){
        Post post = getPostById(postId,principal);
        Optional<ImageModel> imageModel = imageRepository.findByPostId(post.getId());
        postRepository.delete(post);
        imageModel.ifPresent(imageRepository::delete);
    }

    private User getUserByPrincipal(Principal principal){
        String username = principal.getName();

        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }
}
