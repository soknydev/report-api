    package dev.reportapi.security;


    import dev.reportapi.model.User;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import lombok.ToString;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.List;
    import java.util.stream.Collectors;
    import java.util.stream.Stream;

    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public class CustomUserDetails implements UserDetails {

        private User user;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            if (user.getRole() != null) {
                // Add the role
                authorities.add(new SimpleGrantedAuthority(user.getRole().getAuthority()));

                // Add the authorities of that role
                user.getRole().getAuthorities().forEach(authority ->
                        authorities.add(new SimpleGrantedAuthority(authority.getName()))
                );
            }

            return authorities;
        }


        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return user.isAccountNonExpired();
        }

        @Override
        public boolean isAccountNonLocked() {
            return user.isAccountNonLocked();
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return user.isCredentialsNonExpired();
        }

        @Override
        public boolean isEnabled() {
            return user.isEnabled();
        }

    }
