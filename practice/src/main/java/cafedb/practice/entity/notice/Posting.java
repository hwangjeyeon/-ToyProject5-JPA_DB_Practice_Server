package cafedb.practice.entity.notice;


import cafedb.practice.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Posting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String Title;

    //작성자 FK

    @Column(nullable = false)
    private int view;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    // 댓글 FK



}
