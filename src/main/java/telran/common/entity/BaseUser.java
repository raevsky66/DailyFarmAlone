package telran.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

import org.springframework.context.annotation.Lazy;

@Data
@NoArgsConstructor
@MappedSuperclass
@Getter
public abstract class BaseUser {
    @Id
    private UUID id;

    private String email;
    private String phone;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "credential_id", referencedColumnName = "userId")
    private Credential credential;

    public void setCredential(Credential credential) {
        this.credential = credential;
        credential.setUserId(this.id); // Автоматическая установка userId из BaseUser
       
    }
}
