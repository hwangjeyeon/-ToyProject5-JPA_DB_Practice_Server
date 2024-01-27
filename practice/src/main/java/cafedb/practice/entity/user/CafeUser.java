package cafedb.practice.entity.user;

import cafedb.practice.entity.BaseEntity;
import cafedb.practice.entity.notice.Comment;
import cafedb.practice.entity.notice.Posting;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "cafeUser")
    private List<Posting> posting = new ArrayList<>();

    //무한 루프 방지
    public void addPosting(Posting posting){
        this.posting.add(posting);
        if(posting.getCafeUser() != this){
            posting.setCafeUser(this);
        }
    }

    // 댓글 FK
    @OneToMany(mappedBy = "cafeUser")
    private List<Comment> comment = new ArrayList<Comment>();
    
    //무한 루프 방지
    public void addComment(Comment comment){
        this.comment.add(comment);
        if(comment.getCafeUser() != this){
            comment.setCafeUser(this);
        }
    }

    @Column(name="NICK_NAME", nullable = false)
    private String nickName;

}
