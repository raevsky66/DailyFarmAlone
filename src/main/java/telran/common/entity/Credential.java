package telran.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;

@Entity
@Table(name = "credentials")
@Data
public class Credential {
    @Id
    private UUID userId;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
    private Boolean isVerified;
}
