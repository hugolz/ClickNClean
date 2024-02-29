package model;

public enum CleanerExperience {
    NONE(1),
    LESS_1_YEARS(2),
    BETWEEN_1_3(3),
	MORE_3_YEARS(4);

    private int experience;

    CleanerExperience (int experience) {
        this.experience = experience;
    }

    public static CleanerExperience fromInt(int experience) throws Exception {
        switch (experience) {
        case 1:
            return CleanerExperience.NONE;
        case 2:
            return CleanerExperience.LESS_1_YEARS;
        case 3:
            return CleanerExperience.BETWEEN_1_3;
        case 4:
        	return CleanerExperience.MORE_3_YEARS;
        default: throw new Exception("Given experience doesn't match with cleaner experience" + experience);
        }
    }
    public int asInt() {
        return this.experience;
    }
}
