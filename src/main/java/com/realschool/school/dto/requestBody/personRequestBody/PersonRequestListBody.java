package com.realschool.school.dto.requestBody.personRequestBody;

import lombok.Data;

import java.util.List;

@Data
public class PersonRequestListBody {
    private List<PersonRequestBody> personRequestBodyList;
}
