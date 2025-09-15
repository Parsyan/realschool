package com.realschool.school.dto.requestBody.solverRequestBody;

import lombok.Data;

import java.util.List;

@Data
public class SolverRequestListBody {
    private List<SolverRequestBody> solverRequestBodyList;
}
