package dev.mission.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import dev.mission.entite.Mission;

@DataJpaTest
class MissionRepositoryTests {
	@Autowired
	TestEntityManager entityManager;

	@Autowired
	MissionRepository missionRepository;

	@Test
	@Transactional
	void findByDateDebutGreaterThanEqual() {
		// TODO insérer des données avec `entityManager`
		Mission mission = new Mission();
		mission.setLibelle("Mission 1");
		mission.setTauxJournalier(new BigDecimal("100.90"));
		mission.setDateDebut(LocalDate.of(2019, 1, 1));
		mission.setDateFin(LocalDate.of(2019, 3, 4));
		this.entityManager.persist(mission);

		Mission mission2 = new Mission();
		mission2.setLibelle("Mission 2");
		mission2.setTauxJournalier(new BigDecimal("150.90"));
		mission2.setDateDebut(LocalDate.of(2020, 6, 2));
		mission2.setDateFin(LocalDate.of(2020, 6, 5));
		this.entityManager.persist(mission2);

		Mission mission3 = new Mission();
		mission3.setLibelle("Mission 3");
		mission3.setTauxJournalier(new BigDecimal("100.90"));
		mission3.setDateDebut(LocalDate.of(2020, 4, 16));
		mission3.setDateFin(LocalDate.of(2020, 4, 18));
		this.entityManager.persist(mission3);
		// TODO exécuter la requête

		LocalDate aujourdhui = LocalDate.now();
		List<Mission> missions = missionRepository.findFuturMissions(aujourdhui);

		// TODO vérifier le résultat
		assertThat(missions).containsExactlyInAnyOrder(mission2, mission3);
	}

	@Test
	void findByDateDebutGreaterThanEqualAndTauxJournalierGreaterThanEqual() {
		// TODO insérer des données avec `entityManager`
		Mission mission = new Mission();
		mission.setLibelle("Mission 1");
		mission.setTauxJournalier(new BigDecimal("100.90"));
		mission.setDateDebut(LocalDate.of(2019, 1, 1));
		mission.setDateFin(LocalDate.of(2019, 3, 4));
		this.entityManager.persist(mission);

		Mission mission2 = new Mission();
		mission2.setLibelle("Mission 2");
		mission2.setTauxJournalier(new BigDecimal("150.90"));
		mission2.setDateDebut(LocalDate.of(2020, 6, 2));
		mission2.setDateFin(LocalDate.of(2020, 6, 5));
		this.entityManager.persist(mission2);

		Mission mission3 = new Mission();
		mission3.setLibelle("Mission 3");
		mission3.setTauxJournalier(new BigDecimal("100.90"));
		mission3.setDateDebut(LocalDate.of(2020, 4, 16));
		mission3.setDateFin(LocalDate.of(2020, 4, 18));
		this.entityManager.persist(mission3);

		Mission mission4 = new Mission();
		mission4.setLibelle("Mission 4");
		mission4.setTauxJournalier(new BigDecimal("150.90"));
		mission4.setDateDebut(LocalDate.of(2019, 4, 16));
		mission4.setDateFin(LocalDate.of(2019, 4, 18));
		this.entityManager.persist(mission4);

		// TODO exécuter la requête
		LocalDate aujourdhui = LocalDate.now();
		BigDecimal TJM = new BigDecimal(120);
		List<Mission> missions = missionRepository.findFuturMissionsParTJM(aujourdhui, TJM);

		// TODO vérifier le résultat
		assertThat(missions).containsExactlyInAnyOrder(mission2);
	}
}
