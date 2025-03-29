package com.pucminas.conectabh_service.domain;

import com.pucminas.conectabh_service.utils.enums.RoleType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    Integer id;
    String fullName;
    String email;
    String password;
    RoleType role;
}
