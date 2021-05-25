package uz.dev.insta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.dev.insta.model.ImageModel;
import uz.dev.insta.payload.response.MessageResponse;
import uz.dev.insta.service.ImageUploadService;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("api/image")
@CrossOrigin
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadImageToUser(@RequestParam("file")MultipartFile file,
                                                             Principal principal) throws IOException {

        imageUploadService.uploadImageToUser(file,principal);
        return new ResponseEntity<>(new MessageResponse("Image upload Successfully"), HttpStatus.OK);
    }

    @PostMapping("/{postId}/upload")
    public ResponseEntity<MessageResponse> uploadImageToPost(@PathVariable("postId") String postId,
                                                             @RequestParam("file") MultipartFile file,
                                                             Principal principal) throws IOException{

        imageUploadService.uploadImageToPost(file,principal,Long.parseLong(postId));
        return new ResponseEntity<>(new MessageResponse("Image upload Successfully"),HttpStatus.OK);
    }

    @GetMapping("/profileImage")
    public ResponseEntity<ImageModel> getImageForUser(Principal principal){
        ImageModel imageModel = imageUploadService.getImageToUser(principal);
        return new ResponseEntity<>(imageModel,HttpStatus.OK);
    }

    @GetMapping("/{postId}/image")
    public ResponseEntity<ImageModel> getImageToPost(@PathVariable("postId") String postId){
        ImageModel imageModel = imageUploadService.getImageToPost(Long.parseLong(postId));
        return new ResponseEntity<>(imageModel,HttpStatus.OK);
    }
}
