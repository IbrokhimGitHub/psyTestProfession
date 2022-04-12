package uz.psy.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.psy.demo.entity.Role;
import uz.psy.demo.entity.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(RoleName roleName);
}
