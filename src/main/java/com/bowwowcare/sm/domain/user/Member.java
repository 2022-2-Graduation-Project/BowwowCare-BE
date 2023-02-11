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
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 15)
    private String username;

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
    public Member(String email, String username, String password, List<Role>roles) {
        this.email = email;
        this.password = password;
        this.roles = Collections.singletonList(Role.ROLE_USER);
        this.username = username;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    /*public User update(String name, String profileImgUrl) {
        this.name = name;
        this.profileImgUrl = profileImgUrl;

        return this;
    }*/
}
