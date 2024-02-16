package model;

public enum MissionStatus {
    TODO(0);
    private final int status;

    private MissionStatus(int status) {
        this.status = status;
    }

    public static MissionStatus fromInt(int status) throws Exception{
        switch (status) {
        case 0:
            return MissionStatus.TODO;
        default:
            throw new Exception("Given UserStatus status could not be converted into MissionStatus: " + status);
        }
    }

    public int asInt() {
        return this.status;
    }
}

