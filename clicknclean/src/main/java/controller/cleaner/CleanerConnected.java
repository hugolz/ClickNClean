package controller.cleaner;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.scene.layout.VBox;
import model.Cleaner;
import model.Mission;
import model.MissionStatus;
import model.Owner;
import tools.Db;
import tools.MissionDisplay;
import view.Window;
import view.cleaner.CleanerMain;

public class CleanerConnected {
    public CleanerConnected(VBox container, Window window, Cleaner cleaner, int missionProposedId) throws Exception {
        Db db = new Db();
        ArrayList<Mission> missionList = db.DAOReadMissions();
        ArrayList<MissionDisplay> displayList = new ArrayList<>();
        // remove from list if mission status different of published OR date is passed
        missionList.removeIf(m -> (m.getState() != MissionStatus.PUBLISHED || m.getMissionDateTime().isBefore(LocalDateTime.now())));
        if (!missionList.isEmpty()) {
            for (Mission currentMission : missionList) {
                double range = currentMission.getProperty().getPropertyAddress().calculateDistance(cleaner.getDepartureAddress());
                // remove from list if cleaner range is smaller than mission distance
                if (cleaner.getKmRange() < Math.abs(range)) missionList.removeIf(m -> (m.getCleanerId() == cleaner.getCleanerId()));

                Owner owner = db.DAOReadOwner(currentMission.getOwnerId());
                displayList.add(new MissionDisplay(currentMission.getMissionId(), currentMission.getMissionDateTime(), owner.getSurname(), Math.abs(range), currentMission.getDuration()));
            }
            if (missionProposedId != 0) {
                System.out.println("mission : " + missionProposedId + ", cleaner : " + cleaner.getCleanerId());
                Mission mission = db.DAOReadMission(missionProposedId);
                db.DAOCreateNewMissionProposal(missionProposedId, cleaner.getCleanerId(), mission.getMissionDateTime());
            }
        }
        window.setScene(new CleanerMain (new VBox(), window, cleaner, displayList));
    }
}

