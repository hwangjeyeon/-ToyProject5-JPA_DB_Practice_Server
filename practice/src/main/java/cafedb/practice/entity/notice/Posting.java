package cafedb.practice.entity.notice;


import cafedb.practice.entity.BaseEntity;
import cafedb.practice.entity.user.CafeUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Posting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String Title;

    //작성자 FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAFE_USER_ID")
    private CafeUser cafeUser;

    public void setCafeUser(CafeUser cafeUser){
        this.cafeUser = cafeUser;

        // 무한 루프에 빠지지 않도록 체크
        if(!cafeUser.getPosting().contains(this)){
            cafeUser.getPosting().add(this);
        }
    }

    @Column(nullable = false)
    private int view;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // 댓글 FK
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "posting")
    private List<Comment> comment = new ArrayList<Comment>();

    public void addComment(Comment comment){
        this.comment.add(comment);
        //무한 루프 방지
        if(comment.getPosting() != this){
            comment.setPosting(this);
        }
    }

}
