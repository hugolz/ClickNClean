package model;

public enum OwnerMotivation {
    MAIN_HOME(1),
    GUEST_ROOM(2),
    INVENTORY(3);

    private int motivation;

    OwnerMotivation (int motivation) {
        this.motivation = motivation;
    }

    public int fromInt(int motivation) throws Exception {
        switch (motivation){

            case 1: 
                return MAIN_HOME.motivation;
            case 2: 
                return GUEST_ROOM.motivation;
            case 3: 
                return INVENTORY.motivation;


            default: throw new Exception("Given motivation doesn't match with owner motivation" + motivation);
        }
    }
    
}
