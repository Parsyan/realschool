package com.realschool.school.dto.requestBody.solverRequestBody;

import lombok.Data;

@Data
public class SolverRequestBody {
    private Long solver_task_relation_id;
    private Long solver_id;
    private Long task_id;
}
