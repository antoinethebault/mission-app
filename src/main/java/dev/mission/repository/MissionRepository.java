package dev.mission.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.mission.entite.Mission;

public interface MissionRepository extends JpaRepository<Mission, Integer> {
	@Query("SELECT m FROM Mission m WHERE date_debut >= :aujourdhui")
	List<Mission> findFuturMissions(@Param("aujourdhui") LocalDate aujourdhui);

	@Query("SELECT m FROM Mission m WHERE date_debut >= :aujourdhui AND taux_journalier >= :TJM")
	List<Mission> findFuturMissionsParTJM(@Param("aujourdhui") LocalDate aujourdhui, @Param("TJM") BigDecimal tjm);
}
