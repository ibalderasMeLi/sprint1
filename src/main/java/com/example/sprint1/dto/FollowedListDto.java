package com.example.sprint1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowedListDto {
    Integer user_id;
    String user_name;
    List<FollowdUsersDto> followed;
}
