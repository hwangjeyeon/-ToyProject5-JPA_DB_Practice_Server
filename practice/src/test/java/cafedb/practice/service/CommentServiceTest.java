package cafedb.practice.service;

import cafedb.practice.entity.dto.RegisterCommentDto;
import cafedb.practice.entity.notice.Comment;
import cafedb.practice.entity.user.CafeUser;
import cafedb.practice.repository.CafeUserRepository;
import cafedb.practice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CommentServiceTest {

    @Autowired
    CafeUserRepository cafeUserRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;

    @BeforeEach
    void before(){
        CafeUser cafeUser = new CafeUser();
        cafeUser.setId(1L);
        cafeUser.setNickName("kingpele");
        cafeUser.setUserId("king");
        cafeUser.setName("황제연");
        cafeUser.setPassword("1234");
        cafeUser.setMemberRank("매니저");
        cafeUserRepository.save(cafeUser);
    }


    @Test
    @DisplayName("사용자가 등록한 댓글이 잘 저장되는가 테스트")
    void registerCommentTest(){
        //given
        RegisterCommentDto registerCommentDto = new RegisterCommentDto();
        registerCommentDto.setNickname("kingpele");
        registerCommentDto.setContent("첫 카페 게시판 테스트입니다.");

        //when
        commentService.registerComment(registerCommentDto);
        List<Comment> commentList = commentRepository.findAll();

        //then
        assertThat(commentList.get(0).getCafeUser().getNickName()).isEqualTo("kingpele");
        assertThat(commentList.get(0).getContent()).isEqualTo("첫 카페 게시판 테스트입니다.");
    }


}