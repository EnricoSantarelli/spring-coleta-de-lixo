package br.com.fiap.coletadelixo.repositories;

import br.com.fiap.coletadelixo.models.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
    @Query("SELECT s FROM Scheduling s WHERE s.id = :schedulingId AND s.user.id = :userId")
    Scheduling findByIdAndUserId(@Param("schedulingId") Long schedulingId, @Param("userId") Long userId);


}
