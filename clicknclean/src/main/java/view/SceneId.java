package view;

public enum SceneId {
    CONNECTION(1),

    OWNER_REGISTRATION(2),
    CLEANER_REGISTRATION(3),


	OWNER_PROFILE(7);


    private final int id;

    private SceneId(int id) {
        this.id = id;
    }

    public static SceneId fromInt(int id) throws Exception {
        switch (id) {
        case 1:
            return SceneId.CONNECTION;
        case 2:
            return SceneId.OWNER_REGISTRATION;
        case 3:
            return SceneId.CLEANER_REGISTRATION;
        case 6:

        case 7:
        	return SceneId.OWNER_PROFILE;

        default:
            throw new Exception("Given int status could not be converted into UserStatus: " + id);
        }
    }

    public int asInt() {
        return this.id;
    }
}