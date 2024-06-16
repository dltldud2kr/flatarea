package com.example.flatarea.member.service;

import com.example.flatarea.member.entity.Member;
import com.example.flatarea.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        String email = (String) kakaoAccount.get("email");
        String id = attributes.get("id").toString();
        String nickname = (String) profile.get("nickname");

        Optional<Member> memberOptional = memberRepository.findById(email);
        Member member;
        if (memberOptional.isPresent()) {
            member = memberOptional.get();
        } else {
            // 멤버가 없으면 새로 생성하여 저장
            member = Member.builder()
                    .userId(email)
                    .userName(nickname)
                    .password(id) // 실제로는 OAuth2 공급자 ID를 암호로 사용하지 않는 것이 좋음
                    .build();
            memberRepository.save(member);
        }

        // 권한 부여 (ROLE_USER 설정)
        oAuth2User = new OAuth2User() {
            @Override
            public Map<String, Object> getAttributes() {
                return attributes;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
            }

            @Override
            public String getName() {
                return email;
            }
        };

        return oAuth2User;
    }
}
