package com.realschool.school.dto.requestBody.taskRequestBody;

import lombok.Data;

import java.util.List;

@Data
public class TaskRequestListBody{
    private List<TaskRequestBody> taskRequestList;
}
