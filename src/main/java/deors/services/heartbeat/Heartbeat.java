package deors.services.heartbeat;

import java.time.LocalDateTime;

public class Heartbeat {

    private final Long id;
    private final String name;
    private final String description;
    private final LocalDateTime timestamp;

    public Heartbeat(Long id, String name, String description, LocalDateTime timestamp) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getTimestampString() {
        return timestamp.toString();
    }

    @Override
    public String toString() {

        return "Heartbeat [id=" + id
            + ", name=" + name
            + ", description=" + description
            + ", timestamp=" + timestamp + "]";
    }
}
