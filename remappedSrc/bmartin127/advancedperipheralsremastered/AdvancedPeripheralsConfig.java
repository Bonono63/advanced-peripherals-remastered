package bmartin127.advancedperipheralsremastered;

import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;

public class AdvancedPeripheralsConfig {
    public static final Client CLIENT = new Client();
    public static final Server SERVER = new Server();

    public static class Client {}

    public static class Server {

        public final COMPUTERCRAFT ComputerCraft = new COMPUTERCRAFT();

        public class COMPUTERCRAFT {
            @JsonSchema(description = "Disable ComputerCraft Integration")
            public boolean disableComputerCraft = false;

            @JsonSchema(description = "Disable Eureka Integration")
            public boolean disableEurekaIntegration = false;

            @JsonSchema(description = "Disable Radars")
            public boolean disableRadars = false;

            @JsonSchema(description = "Disable Ship Readers")
            public boolean disableShipReaders = false;

            public final RADARSETTINGS RadarSettings = new RADARSETTINGS();

            public class RADARSETTINGS {
                @JsonSchema(description = "Maximum Range of Radars")
                public int maxRadarRadius = 256;

                public boolean radarGetsName = false;
                public boolean radarGetsId = true;
                public boolean radarGetsPosition = true;
                public boolean radarGetsMass = true;
                public boolean radarGetsRotation = false;
                public boolean radarGetsVelocity = false;
                public boolean radarGetsDistance = false;
                public boolean radarGetsSize = false;
            }

            @JsonSchema(description = "Disable Gyroscopic Sensors")
            public boolean disableGyros = false;

            @JsonSchema(description = "Disable Accelerometers")
            public boolean disableAccels = false;

            @JsonSchema(description = "Disable Ultrasonic Sensors")
            public boolean disableSonic = false;

            @JsonSchema(description = "Default Alarm Distance for CC Events")
            public int defaultAlarmDistance = 3;

            @JsonSchema(description = "Max Alarm Distance for CC Events")
            public int maxAlarmDistance = 3;

            @JsonSchema(description = "Is alarm distance changeable>")
            public boolean alarmDistanceChangable = true;

            @JsonSchema(description = "Max Clip Distance for Ultrasonic getClip() method")
            public int maxClipDistance = 12;
        }


    }
}
