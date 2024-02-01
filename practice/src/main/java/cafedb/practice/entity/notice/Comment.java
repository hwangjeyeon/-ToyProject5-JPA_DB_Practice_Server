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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAFE_USER_ID")
    private CafeUser cafeUser;

    public void setCafeUser(CafeUser cafeUser){
        this.cafeUser = cafeUser;
        //무한 루프 방지
        if(!cafeUser.getComment().contains(this)){
            cafeUser.getComment().add(this);
        }
    }


    //게시글 FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSTING_ID")
    private Posting posting;

    public void setPosting(Posting posting){
        this.posting = posting;
        //무한 루프 방지
        if(!posting.getComment().contains(this)){
            posting.getComment().add(this);
        }
    }


    @Column(nullable = false)
    private String content;

}
