package cafedb.practice.service;

import cafedb.practice.entity.dto.comment.DeleteCommentDto;
import cafedb.practice.entity.dto.comment.EditCommentDto;
import cafedb.practice.entity.dto.comment.RegisterCommentDto;
import cafedb.practice.entity.notice.Comment;
import cafedb.practice.entity.notice.Posting;
import cafedb.practice.entity.user.CafeUser;
import cafedb.practice.repository.CafeUserRepository;
import cafedb.practice.repository.CommentRepository;
import cafedb.practice.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final CafeUserRepository cafeUserRepository;
    private final PostingRepository postingRepository;

    /**
     * - Transactional을 붙이지 않으면 준영속상태가 되어서 지연 로딩 시, 해당 객체를 참조할 수 없게 된다. (LazyInitializerException 발생)
     * - 따라서 Transactional을 붙여서 영속상태로 만들어 지연 로딩 시에도 해당 객체를 참조할 수 있게하자
     * - 만약 준영속상태에서도 조회하고 싶다면... -> Fetch조인과 OSIV를 공부하고 적용해보자 -> 이후 연습해볼 예정
     */
    @Transactional
    public void registerComment(RegisterCommentDto registerCommentDto){
        CafeUser cafeUser = cafeUserRepository.findByNickName(registerCommentDto.getNickname()).get(0);
        log.info("nickname={}", cafeUser.getNickName());
        Posting posting = postingRepository.findByCafeUserAndCreateAt(cafeUser,
                registerCommentDto.getPostingCreateAt()).get(0);
        log.info("postingCreateAt={}", posting.getCreateAt());

        Comment comment = new Comment();
        comment.setCafeUser(cafeUser);
        comment.setContent(registerCommentDto.getContent());
        comment.setPosting(posting);

        commentRepository.save(comment);
    }

    //TODO: 댓글 삭제/댓글 수정 기능 추가, 게시글도 똑같은 기능 구현 및 테스트


    /**
     * 댓글을 삭제하려면...
     * - 사용자 정보, 게시글 정보를 알아함
     * - 어떤 게시글에 어떤 사용자의 댓글을 대상으로 하는데 두조건 만족하는 댓글이 여러개 있을 수도 있으니..
     * - 추가로 createAt을 사용하자
     * - 따라서 사용자 정보, 게시글 정보를 알아야하고 추가로 createAt을 기준으로 댓글을 지정해서 삭제한다
     */
    @Transactional
    public void deleteComment(DeleteCommentDto deleteCommentDto){
        CafeUser cafeUser = cafeUserRepository.findByNickName(deleteCommentDto.getNickname()).get(0);
        Posting userPostings = postingRepository.findByCafeUserAndCreateAt(cafeUser,
                deleteCommentDto.getPostingCreateAt()).get(0);
        Comment comment = commentRepository.findByCafeUserAndPostingAndCreateAt(cafeUser,
                userPostings, deleteCommentDto.getCommentCreateAt()).get(0);

        commentRepository.delete(comment);
    }


    // 변경감지 활용하기
    @Transactional
    public void editComment(EditCommentDto editCommentDto){
        CafeUser cafeUser = cafeUserRepository.findByNickName(editCommentDto.getNickname()).get(0);
        Posting userPostings = postingRepository.findByCafeUserAndCreateAt(cafeUser, editCommentDto.getPostingCreateAt()).get(0);
        Comment comment = commentRepository.findByCafeUserAndPostingAndCreateAt(cafeUser,
                userPostings, editCommentDto.getCommentCreateAt()).get(0);
        comment.setContent(editCommentDto.getContent());
    }



}
