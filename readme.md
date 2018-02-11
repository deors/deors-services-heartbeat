deors-services-heartbeat
========================

Heartbeat controller and dashboard application used to monitor
long-running services like daemons or unattended kiosks.

Endpoints
---------

/heartbeat?name=the-name&description=the-description

    Receives a heartbeat from a generic, abstract device, service or kiosk. The heartbeat is
    registered and might be read from /heartbeats or /latest or through the web dashboard.

/heartbeats?name=the-name

    Returns registered heartbeats for <the-name> device, service or kiosk.

/latest

    Returns the latest heartbeats for all known devices, services or kiosks.

/purge

    Purges the heartbeat database.
