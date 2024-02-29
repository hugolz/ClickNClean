package view;

public enum SceneId {
    CONNECTION(1),
    OWNER_REGISTRATION(2),
    CLEANER_REGISTRATION(3),
    OWNER_WELCOME(4),
    CLEANER_WELCOME(5),
    // ADMIN_WELCOME(6),
    // ADMIN_WELCOME(6),
    // ADMIN_WELCOME(6),
    // ADMIN_WELCOME(6),
    // ADMIN_WELCOME(6),
    // ADMIN_WELCOME(6),
    // ADMIN_WELCOME(6),
    // ADMIN_WELCOME(6),
    // ADMIN_WELCOME(6),
    ADMIN_WELCOME(6);

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
        case 4:
            return SceneId.OWNER_WELCOME;
        case 5:
            return SceneId.CLEANER_WELCOME;
        case 6:
            return SceneId.ADMIN_WELCOME;
        default:
            throw new Exception("Given int status could not be converted into UserStatus: " + id);
        }
    }

    public int asInt() {
        return this.id;
    }
}