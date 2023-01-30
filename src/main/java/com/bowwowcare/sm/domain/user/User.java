package com.bowwowcare.sm.domain.user;

import com.bowwowcare.sm.domain.enums.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 15)
    private String nickname;

    @Column(nullable = false,unique = true)
    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    private String refreshToken;

    /*@Column
    private String profileImgUrl;*/

    @Builder
    public User(String email, String nickname, String password, List<Role>roles) {
        this.email = email;
        this.password = password;
        this.roles = Collections.singletonList(Role.ROLE_USER);
        this.nickname = nickname;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    /*public User update(String name, String profileImgUrl) {
        this.name = name;
        this.profileImgUrl = profileImgUrl;

        return this;
    }*/
}
