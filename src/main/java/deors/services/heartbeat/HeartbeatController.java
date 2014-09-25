package deors.services.heartbeat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HeartbeatController {

    private final AtomicLong counter = new AtomicLong();
    private final static String DEF_DESC = "unknown";

    @RequestMapping("/heartbeat")
    public @ResponseBody Heartbeat addHeartbeat(
        @RequestParam(value = "name", required = true) String name,
        @RequestParam(value = "description", required = false, defaultValue = DEF_DESC) String description) {

        Heartbeat heartbeat = new Heartbeat(counter.incrementAndGet(), name, description, LocalDateTime.now());
        HeartbeatStore.addHeartbeat(heartbeat);
        return heartbeat;
    }

    @RequestMapping("/heartbeats")
    public @ResponseBody List<Heartbeat> getHeartbeats(
        @RequestParam(value = "name", required = true) String name) {

        return HeartbeatStore.getHeartbeats(name);
    }

    @RequestMapping("/latest")
    public @ResponseBody List<Heartbeat> getLatestHeartbeats() {

        return HeartbeatStore.getLatestHeartbeats();
    }

    @RequestMapping("/purge")
    public String purgeData() {

        HeartbeatStore.purge();
        return "/";
    }
}
