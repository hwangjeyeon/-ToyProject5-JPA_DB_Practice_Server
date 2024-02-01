package cafedb.practice.entity.dto.comment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EditCommentDto {

    // 어떤 댓글인지 찾기 위함
    private String nickname;
    private LocalDateTime postingCreateAt;
    private LocalDateTime commentCreateAt;

    //변경 내용
    private String content;

}
