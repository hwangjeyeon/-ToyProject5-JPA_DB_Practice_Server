package cafedb.practice.entity.notice;

import cafedb.practice.entity.BaseEntity;
import cafedb.practice.entity.user.CafeUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //작성자 엔티티 만들기 FK
    @ManyToOne
    @JoinColumn(name = "CAFE_USER_ID")
    private CafeUser cafeUser;

    public void setCafeUser(CafeUser cafeUser){
        this.cafeUser = cafeUser;
        //무한 루프 방지
        if(!cafeUser.getComment().contains(this)){
            cafeUser.getComment().add(this);
        }
    }

    @Column(nullable = false)
    private String content;


}