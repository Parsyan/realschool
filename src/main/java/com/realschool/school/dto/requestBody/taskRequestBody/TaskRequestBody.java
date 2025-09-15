package com.realschool.school.dto.requestBody.taskRequestBody;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class TaskRequestBody {

    private Long task_id;
    private String title;
    private String query;
    private String solution;
    private Boolean solved;
    private Long creator_id;
    private Long confirmer_id;

    private Long solver_id;


}






