package cafedb.practice.repository;

import cafedb.practice.entity.notice.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
