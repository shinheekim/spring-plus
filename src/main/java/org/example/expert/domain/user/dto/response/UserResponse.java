package org.example.expert.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.example.expert.domain.user.entity.User;

@Getter
@Builder
public class UserResponse {

    private final Long id;
    private final String email;
    private final String nickname;

    public UserResponse(Long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }
}
