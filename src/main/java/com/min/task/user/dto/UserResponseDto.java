package com.min.task.user.dto;

import com.min.task.acccount.dto.AccountResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String id;
    private String name;
    private List<AccountResponseDto> accountResponseDtoList;

}
