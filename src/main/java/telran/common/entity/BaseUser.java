package telran.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
