package cafedb.practice.entity.notice;

import cafedb.practice.entity.BaseEntity;
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

    @Column(nullable = false)
    private String content;


}
