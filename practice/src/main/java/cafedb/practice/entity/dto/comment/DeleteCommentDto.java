package cafedb.practice.entity.dto.comment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeleteCommentDto {

    private String nickname;
    private LocalDateTime postingCreateAt;
    private LocalDateTime commentCreateAt;


}
