package info.masjidy.repositories;

import info.masjidy.models.Mosque;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MosqueRepository extends JpaRepository<Mosque, Long> {

}
