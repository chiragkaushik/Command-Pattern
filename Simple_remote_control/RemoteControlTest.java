public class RemoteControlTest{
    public static void main(String[] args){
        //  Create a object of SimpleRemoteControl
        SimpleRemoteControl remote = new SimpleRemoteControl();
        //  Create a light object which is to turned on
        Light light = new Light();
        //  Command to turn on the light
        LightOnCommand lightOn = new LightOnCommand(light);
        //  Set the command to the remote
        remote.setCommand(lightOn);
        //  Now when the remote button is pressed the Light gets on
        remote.buttonWasPressed();
    }
}
