package cafedb.practice.repository;

import cafedb.practice.entity.notice.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepository extends JpaRepository<Posting, Long> {
}
