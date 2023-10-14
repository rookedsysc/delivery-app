package org.delivery.storeadmin.domain.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.storeuser.enums.StoreUserRole;
import org.delivery.db.storeuser.enums.StoreUserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSession implements UserDetails {

    // user
    private Long userId;

    private String email;

    private String password;

    private StoreUserStatus status;

    private StoreUserRole role;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;

    // store
    private Long storeId;
    private String storeName;


    @Override
    /// 인증된 사용자의 정보를 가져옴
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.toString()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    /// User가 만료되었는지
    public boolean isAccountNonExpired() {
        return this.status == StoreUserStatus.REGISTERED;
    }

    @Override
    /// User가 잠겨있는지
    public boolean isAccountNonLocked() {
        return this.status == StoreUserStatus.REGISTERED;
    }

    @Override
    /// User 패스워드가 만료되었는지
    public boolean isCredentialsNonExpired() {
        return this.status == StoreUserStatus.REGISTERED;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
