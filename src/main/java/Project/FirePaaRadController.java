package project;

public class FirePaaRadController {
    private FirePaaRadEnv firePaaRad;

    public FirePaaRadController() {
        firePaaRad = new FirePaaRadEnv("Ludde", "Thomas");
    }

    public void buttonClicked() {
        firePaaRad = new FirePaaRadEnv("Ludde", "Thomas");
    }
}
