package cafedb.practice.service;

import cafedb.practice.entity.dto.RegisterCommentDto;
import cafedb.practice.entity.notice.Comment;
import cafedb.practice.entity.user.CafeUser;
import cafedb.practice.repository.CafeUserRepository;
import cafedb.practice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final CafeUserRepository cafeUserRepository;

    /**
     * - Transactional을 붙이지 않으면 준영속상태가 되어서 지연 로딩 시, 해당 객체를 참조할 수 없게 된다. (LazyInitializerException 발생)
     * - 따라서 Transactional을 붙여서 영속상태로 만들어 지연 로딩 시에도 해당 객체를 참조할 수 있게하자
     * - 만약 준영속상태에서도 조회하고 싶다면... -> Fetch조인과 OSIV를 공부하고 적용해보자 -> 이후 연습해볼 예정
     */
    @Transactional
    public void registerComment(RegisterCommentDto registerCommentDto){
        Comment comment = new Comment();
        comment.setCafeUser(cafeUserRepository.findByNickName(registerCommentDto.getNickname()).get(0));
        comment.setContent(registerCommentDto.getContent());
        commentRepository.save(comment);
    }



}
