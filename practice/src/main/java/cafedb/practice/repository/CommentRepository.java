package cafedb.practice.repository;

import cafedb.practice.entity.notice.Comment;
import cafedb.practice.entity.notice.Posting;
import cafedb.practice.entity.user.CafeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByCafeUserAndPostingAndCreateAt(CafeUser cafeUser, Posting posting, LocalDateTime createAt);
}
