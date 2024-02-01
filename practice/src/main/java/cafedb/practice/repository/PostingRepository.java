package cafedb.practice.repository;

import cafedb.practice.entity.notice.Posting;
import cafedb.practice.entity.user.CafeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {

    List<Posting> findByCafeUserAndCreateAt(CafeUser cafeUser, LocalDateTime createAt);
}
