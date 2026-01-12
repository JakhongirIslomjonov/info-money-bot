package uz.dev.infomoneybot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.dev.infomoneybot.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}