package cafedb.practice.service;

import cafedb.practice.entity.dto.comment.DeleteCommentDto;
import cafedb.practice.entity.dto.comment.RegisterCommentDto;
import cafedb.practice.entity.notice.Comment;
import cafedb.practice.entity.notice.Posting;
import cafedb.practice.entity.user.CafeUser;
import cafedb.practice.repository.CafeUserRepository;
import cafedb.practice.repository.CommentRepository;
import cafedb.practice.repository.PostingRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class CommentServiceTest {

    @Autowired
    CafeUserRepository cafeUserRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostingRepository postingRepository;

    @Autowired
    CommentService commentService;


    @BeforeEach
    @Transactional
    void before(){
        CafeUser cafeUser = new CafeUser();
        cafeUser.setId(1L);
        cafeUser.setNickName("kingpele");
        cafeUser.setUserId("king");
        cafeUser.setName("황제연");
        cafeUser.setPassword("1234");
        cafeUser.setMemberRank("매니저");
        cafeUserRepository.save(cafeUser);

        Posting posting = new Posting();
        posting.setId(1L);
        posting.setCafeUser(cafeUser);
        posting.setView(1);
        posting.setTitle("첫글");
        posting.setContent("안녕하세요 매니저 황제연입니다. 첫 게시글입니다 잘 부탁드립니다.");
        postingRepository.save(posting);
    }


    @Test
    @DisplayName("사용자가 등록한 댓글이 잘 저장되는가 테스트")
    void registerCommentTest(){
        //given
        RegisterCommentDto registerCommentDto = new RegisterCommentDto();
        registerCommentDto.setNickname("kingpele");
        registerCommentDto.setContent("첫 카페 게시판 테스트입니다.");
        registerCommentDto.setPostingCreateAt(postingRepository.findById(1L).get().getCreateAt());

        //when
        commentService.registerComment(registerCommentDto);
        List<Comment> commentList = commentRepository.findAll();

        //then
        assertThat(commentList.get(0).getCafeUser()).isNotNull();
        assertThat(commentList.get(0).getContent()).isEqualTo("첫 카페 게시판 테스트입니다.");
        assertThat(commentList.get(0).getPosting()).isNotNull();
    }

    @Test
    @DisplayName("사용자가 등록한 댓글이 잘 삭제되는가 테스트")
    void deleteCommentTest(){
        //given
        DeleteCommentDto deleteCommentDto = new DeleteCommentDto();
        deleteCommentDto.setNickname("kingpele");
        deleteCommentDto.setPostingCreateAt(postingRepository.findById(1L).get().getCreateAt());
        deleteCommentDto.setCommentCreateAt(commentRepository.findById(1L).get().getCreateAt());

        //when
        commentService.deleteComment(deleteCommentDto);
        List<Comment> commentList = commentRepository.findAll();
        //then
        assertThat(commentList).isEmpty();
    }


}