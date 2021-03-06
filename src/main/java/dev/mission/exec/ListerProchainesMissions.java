package dev.mission.exec;

import java.time.LocalDate;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import dev.mission.entite.Mission;
import dev.mission.repository.MissionRepository;

@Controller
@Profile("selectProchainesMissions")
public class ListerProchainesMissions implements Runnable {
	private MissionRepository missionRepository;

	private static final Logger LOGGER = Logger.getLogger(ListerProchainesMissions.class.getName());

	public ListerProchainesMissions(MissionRepository missionRepository) {
		super();
		this.missionRepository = missionRepository;
	}

	@Override
	public void run() {
		LocalDate aujourdhui = LocalDate.now();
		List<Mission> missions = missionRepository.findFuturMissions(aujourdhui);
		LOGGER.info("Les missions a partir d'aujourd'hui : ");
		for (Mission mission : missions)
			LOGGER.info(mission.toString());
	}
}
