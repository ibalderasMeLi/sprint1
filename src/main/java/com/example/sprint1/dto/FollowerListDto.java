package com.example.sprint1.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowerListDto {
    Integer user_id;
    String user_name;
    List<FollowerUserDto> followers;
}
