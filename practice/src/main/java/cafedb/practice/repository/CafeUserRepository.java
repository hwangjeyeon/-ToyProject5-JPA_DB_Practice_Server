package cafedb.practice.repository;

import cafedb.practice.entity.user.CafeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeUserRepository extends JpaRepository<CafeUser, Long> {

    List<CafeUser> findByNickName(String nickname);
}
