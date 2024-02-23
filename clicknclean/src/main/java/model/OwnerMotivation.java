package model;

public enum OwnerMotivation {
    MAIN_HOME(1),
    GUEST_ROOM(2),
    INVENTORY(3);

    private int motivation;

    OwnerMotivation (int motivation) {
        this.motivation = motivation;
    }

    public static OwnerMotivation fromInt(int motivation) throws Exception {
        switch (motivation) {
        case 1:
            return OwnerMotivation.MAIN_HOME;
        case 2:
            return OwnerMotivation.GUEST_ROOM;
        case 3:
            return OwnerMotivation.INVENTORY;
        default: throw new Exception("Given motivation doesn't match with owner motivation" + motivation);
        }
    }

    public int asInt() {
        return this.motivation;
    }
}
