package deors.tools.heartbeat;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class ScheduledTasks {

    @Scheduled(cron = "0 * * * * *")
    public void purgeHeartbeats() {

        HeartbeatStore.purgeOldHearbeats();
    }
}
