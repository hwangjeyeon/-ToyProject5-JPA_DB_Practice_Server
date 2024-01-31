package cafedb.practice.repository;

import cafedb.practice.entity.user.CafeUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeUserRepository extends JpaRepository<CafeUser, Long> {
}
