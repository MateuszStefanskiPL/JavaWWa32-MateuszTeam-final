package mateuszteam.final_project.domain.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users")
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private Long userId;

    @Column(name = "email", unique = true)
    private String email;

    private String password;

    private BigDecimal moneySpent;

    @Enumerated
    @Column(name = "status")
    private UserStatus userStatus;

    @Getter(AccessLevel.NONE)   //Lombok: do not generate getter for this field (getAuthorities ma okreslony kontrakt nizej)
    @ElementCollection(fetch = FetchType.EAGER)  //w przeciwnym wypadku mapping exception -> https://thorben-janssen.com/hibernate-tips-elementcollection/
    private List<String> authorities = new ArrayList<>();

    public List<String> getAuthoritiesList() {
        if(this.authorities == null) {
            this.authorities = new ArrayList<>();
        }
        return this.authorities;
    }

    //ta metoda to czesc kontraktu -> https://www.baeldung.com/spring-security-granted-authority-vs-role#userdetailsservice
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(var authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }
        return grantedAuthorities;
    }

    //Security zakłada domyślny schemat z polem 'username', wskazujemy, co jest naszym username - tu email
    @Override
    public String getUsername() {
        return email;
    }
    //poniższe ustawione na 'true' z domyślnego 'false'
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


