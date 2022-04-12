package uz.psy.demo.entity.enums;

public enum RoleName {
    ADMIN(1,"admin"),
    TESTER(2,"tester");

    private final int value;
    private final String reasonPhrase;
    RoleName(int value, String reasonPhrase) {
        this.value=value;
        this.reasonPhrase=reasonPhrase;

    }
    public int value() {
        return this.value;
    }


    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
