package cafedb.practice.entity.user;

import cafedb.practice.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "CAFE_USER")
public class CafeUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(name="MEMBER_RANK", nullable = false)
    private String memberRank;

    // 게시글 FK

    // 댓글 FK

    @Column(name="NICK_NAME", nullable = false)
    private String nickName;

}
