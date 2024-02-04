package com.min.task.user.controller;

import com.min.task.user.dto.UserCreateRequest;
import com.min.task.user.entity.UserEntity;
import com.min.task.user.repository.UserRepository;
import com.min.task.test.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerIT {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mvc;

    @Test
    void findById_whenDoesNotExist_thenReturn_400() throws Exception {
        mvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void save() throws Exception {
        var userRequest = new UserCreateRequest("testName");
        mvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.objectToJson(userRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void findById_whenExists_thenReturn() throws Exception {
        var id = "8fb302e2-940d-49ae-a56a-1b17346a4507";
        userRepository.save(new UserEntity(id, null, "testName"));

        mvc.perform(get("/api/v1/users/" + id))
                .andExpect(status().isOk());
    }


}
