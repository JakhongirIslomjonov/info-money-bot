package uz.dev.infomoneybot.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.dev.infomoneybot.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByChatId(Long chatId);


}