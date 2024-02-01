package cafedb.practice.entity.dto.comment;

import cafedb.practice.entity.notice.Posting;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterCommentDto {

    private String nickname;
    private LocalDateTime postingCreateAt;
    private String content;

}
