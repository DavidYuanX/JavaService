package com.example.crud;

import com.example.crud.model.User;
import com.example.crud.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthAndAdminSecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRepository.save(createUser("admin", "admin123", User.Role.ADMIN, "13800000000"));
        userRepository.save(createUser("alice", "user123", User.Role.USER, "13900000000"));
    }

    @Test
    void loginReturnsRoleAndToken() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "username": "admin",
                                  "password": "admin123"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.username").value("admin"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    void normalUserCannotAccessAdminEndpoint() throws Exception {
        String userToken = loginAndGetToken("alice", "user123");

        mockMvc.perform(get("/api/users")
                        .header("Authorization", "Bearer " + userToken))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.status").value(403))
                .andExpect(jsonPath("$.message").value("没有权限访问该资源"))
                .andExpect(jsonPath("$.path").value("/api/users"));
    }

    @Test
    void adminCanAccessAdminEndpoint() throws Exception {
        String adminToken = loginAndGetToken("admin", "admin123");

        mockMvc.perform(get("/api/users")
                        .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isOk());
    }

    @Test
    void anonymousUserGetsJsonUnauthorizedResponse() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value(401))
                .andExpect(jsonPath("$.message").value("未登录或登录已过期"))
                .andExpect(jsonPath("$.path").value("/api/users"));
    }

    private User createUser(String username, String rawPassword, User.Role role, String phone) {
        User user = new User(username, passwordEncoder.encode(rawPassword), role);
        user.setPhone(phone);
        return user;
    }

    private String loginAndGetToken(String username, String password) throws Exception {
        String response = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "username": "%s",
                                  "password": "%s"
                                }
                                """.formatted(username, password)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        int start = response.indexOf("\"token\":\"");
        int tokenStart = start + 9;
        int tokenEnd = response.indexOf('"', tokenStart);
        return response.substring(tokenStart, tokenEnd);
    }
}
