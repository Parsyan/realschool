package com.realschool.school.dto.requestBody.personRequestBody;

import lombok.Data;

@Data
public class PersonRequestBody {
    private Long person_id;
    private String email;
    private String name;
    private String surname;
    private int age;
}
