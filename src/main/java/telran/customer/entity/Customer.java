package telran.customer.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import telran.common.entity.BaseUser;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseUser {
    private String firstName;
    private String lastName;
}