package deors.services.heartbeat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeartbeatStore {

    private static final Map<String, List<Heartbeat>> HEARTBEATS = new ConcurrentHashMap<>();

    private static Logger logger = LoggerFactory.getLogger(HeartbeatStore.class);

    private HeartbeatStore() {

        super();
    }

    public static void addHeartbeat(Heartbeat heartbeat) {

        List<Heartbeat> heartbeatList = HEARTBEATS.getOrDefault(
            heartbeat.getName(), new ArrayList<Heartbeat>());
        HEARTBEATS.putIfAbsent(heartbeat.getName(), heartbeatList);
        heartbeatList.add(heartbeat);
        logger.info("received heartbeat: " + heartbeat);
    }

    public static List<Heartbeat> getHeartbeats(String name) {

        return HEARTBEATS.getOrDefault(name, new ArrayList<Heartbeat>());
    }

    public static List<Heartbeat> getLatestHeartbeats() {

        List<Heartbeat> latest = new ArrayList<>();
        HEARTBEATS.forEach((n, l) -> latest.add(l.get(l.size() - 1)));
        return latest;
    }

    public static void purge() {

        synchronized (HEARTBEATS) {
            HEARTBEATS.clear();
        }
        logger.info("purged all heartbeats");
    }

    public static void purgeOldHearbeats() {

        LocalDateTime cut = LocalDateTime.now().minusDays(1);
        synchronized (HEARTBEATS) {
            HEARTBEATS.values().forEach(l -> l.removeIf(h -> h.getTimestamp().isBefore(cut)));
            HEARTBEATS.forEach((n, l) -> { if (l.isEmpty()) HEARTBEATS.remove(n); });
        }
        logger.info("purged old heartbeats");
    }
}
