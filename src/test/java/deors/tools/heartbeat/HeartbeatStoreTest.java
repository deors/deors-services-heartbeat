package deors.tools.heartbeat;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

public class HeartbeatStoreTest {

    @Test
    public void testPurge() {

        HeartbeatStore.purge();
        HeartbeatStore.addHeartbeat(new Heartbeat(1L, "name", "desc", LocalDateTime.now().minusDays(2)));
        HeartbeatStore.addHeartbeat(new Heartbeat(2L, "name", "desc", LocalDateTime.now().minusDays(1).minusHours(10)));
        HeartbeatStore.addHeartbeat(new Heartbeat(3L, "name", "desc", LocalDateTime.now().minusDays(1).minusHours(6)));
        HeartbeatStore.addHeartbeat(new Heartbeat(4L, "name", "desc", LocalDateTime.now().minusDays(1).minusHours(2)));
        HeartbeatStore.addHeartbeat(new Heartbeat(5L, "name", "desc", LocalDateTime.now().minusDays(1)));
        HeartbeatStore.addHeartbeat(new Heartbeat(6L, "name", "desc", LocalDateTime.now().minusHours(20)));
        HeartbeatStore.addHeartbeat(new Heartbeat(7L, "name", "desc", LocalDateTime.now().minusHours(12)));
        HeartbeatStore.addHeartbeat(new Heartbeat(8L, "name", "desc", LocalDateTime.now().minusHours(2)));
        HeartbeatStore.purgeOldHearbeats();

        LocalDateTime cutDate = LocalDateTime.now().minusDays(1).minusMinutes(1);
        System.out.println(cutDate);

        for (Heartbeat hb : HeartbeatStore.getHeartbeats("name")) {
            System.out.println(hb);
            assertTrue("old date not purged", hb.getTimestamp().isAfter(cutDate));
        }
    }
}
