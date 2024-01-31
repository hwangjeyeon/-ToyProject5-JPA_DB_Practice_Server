package cafedb.practice.repository;

import cafedb.practice.entity.info.CafeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeInfoRepository extends JpaRepository<CafeInfo, Long> {

    
}
