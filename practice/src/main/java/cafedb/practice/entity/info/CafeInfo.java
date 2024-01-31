package cafedb.practice.entity.info;

import cafedb.practice.entity.BaseEntity;
import cafedb.practice.entity.notice.Posting;
import cafedb.practice.entity.user.CafeUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="CAFE_INFO")
public class CafeInfo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CAFE_NAME", nullable = false)
    private String cafeName;
    @Column(name = "MANAGER_NAME", nullable = false)
    private String managerName;
    @Column(name = "CAFE_TYPE", nullable = false)
    private String cafeType;
    @Column(name = "CAFE_CREATEAT", nullable = false)
    private LocalDateTime cafeCreateAT;

    // 카페 멤버 FK
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAFE_USER_ID")
    private List<CafeUser> cafeUser = new ArrayList<CafeUser>();

    // 카페 게시글 FK
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSTING_ID")
    private List<Posting> postings = new ArrayList<Posting>();

}
