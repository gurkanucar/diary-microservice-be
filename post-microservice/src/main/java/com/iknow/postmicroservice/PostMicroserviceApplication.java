package com.iknow.postmicroservice;

import com.iknow.postmicroservice.model.Comment;
import com.iknow.postmicroservice.model.EmojiType;
import com.iknow.postmicroservice.model.LikeModel;
import com.iknow.postmicroservice.model.User;
import com.iknow.postmicroservice.model.post.Post;
import com.iknow.postmicroservice.repository.PostRepository;
import com.iknow.postmicroservice.request.CommentRequest;
import com.iknow.postmicroservice.request.LikeRequest;
import com.iknow.postmicroservice.service.PostService;
import com.iknow.postmicroservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableMongoRepositories
@EnableFeignClients
@EnableCaching
@SpringBootApplication
public class PostMicroserviceApplication implements CommandLineRunner {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Autowired
    UserService userService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    public static void main(String[] args) {
        SpringApplication.run(PostMicroserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        postRepository.deleteAll();
        /* */
       /* User user = userRepository.save(User.builder().username("grkn").name("Gurkan").profileImageUrl("https://avatars.githubusercontent.com/u/25080366?v=4").build());
        User user2 = userRepository.save(User.builder().username("sezai").name("sezai").profileImageUrl("https://pbs.twimg.com/profile_images/1232374207729799170/IqtUTP4s_400x400.jpg").build());
        User user3 = userRepository.save(User.builder().username("serif").name("serif").profileImageUrl("https://i.ytimg.com/vi/0Z996ZDAdPg/maxresdefault.jpg").build());
*/
       /* User user = userService.getUserByUsername("gurkan");
        User user2 = userService.getUserByUsername("sezai");
        User user3 = userService.getUserByUsername("serif");

        Post post2 = postService.create(Post.builder().content("Post2 Lorem ipsum dolor sit amet, consectetur adipiscing elit.").user(user2).build());
        Post post1 = postService.create(Post.builder().imageUrl("image urlrllll!!!").content("first post, this is test!").user(user).build());
        Post post3 = postService.create(Post.builder().content("Post3 Lorem ipsum dolor sit amet, consectetur adipiscing elit.").user(user3).build());
        Post post4 = postService.create(Post.builder().content("Post4 Lorem ipsum dolor sit amet, consectetur adipiscing elit.").user(user).build());
        Post post5 = postService.create(Post.builder().content("Post5 Lorem ipsum dolor sit amet, consectetur adipiscing elit.").user(user2).build());
        Post post6 = postService.create(Post.builder().content("Post6 Lorem ipsum dolor sit amet, consectetur adipiscing elit.").user(user2).build());


        Comment comment = Comment.builder().user(user).content("comment 1").build();
        Comment comment2 = Comment.builder().user(user3).content("comment 2").build();
        Comment comment3 = Comment.builder().user(user).content("comment 3").build();
        Comment comment4 = Comment.builder().user(user).content("comment 4 for post with image").build();

        postService.addComment(new CommentRequest(post2, comment));
        postService.addComment(new CommentRequest(post2, comment2));
        postService.addComment(new CommentRequest(post1, comment3));

        postService.addLike(new LikeRequest(post1, new LikeModel(user2, EmojiType.LIKE)));

        postService.addLike(new LikeRequest(post2, new LikeModel(user, EmojiType.HAPPY)));
        postService.addLike(new LikeRequest(post2, new LikeModel(user3, EmojiType.HAPPY)));*/


    }
}
