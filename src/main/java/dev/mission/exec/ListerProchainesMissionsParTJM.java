package dev.mission.exec;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import dev.mission.entite.Mission;
import dev.mission.repository.MissionRepository;

@Controller
@Profile("selectProchainesMissionsParTJM")
public class ListerProchainesMissionsParTJM implements Runnable {

	private MissionRepository missionRepository;

	private static final Logger LOGGER = Logger.getLogger(ListerProchainesMissions.class.getName());

	public ListerProchainesMissionsParTJM(MissionRepository missionRepository) {
		super();
		this.missionRepository = missionRepository;
	}

	@Override
	public void run() {
		LocalDate aujourdhui = LocalDate.now();
		BigDecimal TJM = new BigDecimal(120);
		List<Mission> missions = missionRepository.findFuturMissionsParTJM(aujourdhui, TJM);
		LOGGER.info("Les missions a partir d'aujourd'hui avec un seuil de TJ de " + TJM.toString() + " : ");
		for (Mission mission : missions)
			LOGGER.info(mission.toString());
	}
}
