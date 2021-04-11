# Command-Pattern
The Command Pattern encapsulates a request as an object, thereby letting you parameterize other objects with different 
requests, queue or log requests, and support undoable operations

# The Command Object
![alt text](./images/Command-pattern-2.jpeg)

# Examples of Commands Created by using Command Object
![alt text](./images/Command-pattern-3.jpeg)

# Class Diagram
![alt text](./images/Command-pattern-1.jpeg)


# Problem Statement
So lets jump onto our problem statement, We need to design a remote which can operate on Seven devices simultaneously 
and it has 2 buttons reserved for each device. And also it has a undo button which provide us functionality to undo 
the last active performed by the Remote.

Lets Check out our Remote Control....
![alt text](./images/Command-pattern-4.jpeg)

Lets begin by making a Remote control with have only one button and it can turn on a Light when button is pressed.

1. Define a simple Remote control with have only one slot which can be configured as switch to perform a single
   operation on a single device.

    ```
    public class SimpleRemoteControl{
        Command slot;
        public SimpleRemoteControl() {}
    
        public void setCommand(Command command){
            slot = command;
        }
        public void buttonWasPressed(){
            slot.execute();
        }
    }
    ```
    
    # Method Usage
    - setCommand() method is used to assign a command to the Slot on Remote Control
    - buttonWasPressed() method is used to call execute function of the Command stored on the slot.

2. Define the device which is to be operated by Remote Control.
    ```
    public class Light{
        public void on(){
            System.out.println("Light is on");
        }
    }
    ```
    
    # Method Usage
    - on() method is used to turn on the light
    
3. Define a interface for Command. So that Remote Control knows what type commands can be assigned to its Slots.
    ```
    public interface Command{
        public void execute();
    }
    ```
   
4. Define Command which implements the Common Command interface.
    ```
   public class LightOnCommand implements Command{
     Light light;
     public LightOnCommand(Light light){
        this.light = light;
     }
     public void execute(){
        light.on();
     }
   }
    ```
   # Method Usage
   - execute() method in a command to stores how the particular task is executed on the device. For example: how to 
   turn on light in this case.  

5. Now we have to assign tasks to the Remote Control slots and use it to perform our desired tasks. 
    ```
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
    ```  
   
   Here we have created a lightOn command which turns on a Light. Then we have configured our Remote Control slot to 
   store lightOn Command. When we press the button present on the Remote Control then, our remote Control asks lightOn 
   command to complete its task. The lightOn Command knows what its task is and so it completes its task.
   
   
### Here Remote Control do not know how to turn On light. It only knows which Command he has to call when a particular button is pressed. So Remote is decoupled with the task which are to be performed. We can assign multiple Commands to the same button(One at a time) and perform muliple tasks by using the same button.

#### Note: This code is present in [simple_remote_control!](https://github.com/chiragkaushik/Command-Pattern/tree/main/Simple_remote_control) directory in this repository 



 ### Now lets define a Remote Control which can be configured to operate on Seven devices simultaneously with each device having 2 buttons reserved for its operations.
 
1. We need to follow the same steps which we followed while creating Simple Remote Control with only one Button. we need to provide a way to assign commands to slots. In this case we have seven slots, each with an "on" and "off" button. So we can assign commands to the remote like this:
    ```
    onCommands[0] = onCommand;
    offCommands[0] = offCommand;
    ```
2. Define device which is to be operated by Remote Control, as we defined in simple remote control.
    ```
    public class Light{
        public void on(){
            System.out.println("Light is on");
        }
        public void off(){
            System.out.println("Light is off");
        }
    }
    ```
3. Define a interface for Command. So that Remote Control knows what type commands can be assigned to its Slots.
    ```
    public interface Command{
        public void execute();
    }
    ```
4. Define Command which implements the Common Command interface.
    ```
   public class LightOnCommand implements Command{
     Light light;
     public LightOnCommand(Light light){
        this.light = light;
     }
     public void execute(){
        light.on();
     }
   }
    ```
   ```
   public class LightOffCommand implements Command{
     Light light;
     public LightOffCommand(Light light){
        this.light = light;
     }
     public void execute(){
        light.off();
     }
   }
    ```
   # Method Usage
   - execute() method in a command to stores how the particular task is executed on the device. For example: how to 
   turn on/off light in this case.  
5. Define a command that does nothing(i.e. noCommand) which we will assign to all the slots of the remote before assigning commands to the slots. So that we do not have to check if a command is loaded every time we referenced a slot.
   ```
    public class NoCommand implements Command {
        public void execute(){}
    }
    ```
6. Now we can define Remote Loader and assign tasks to the Remote Control slots and use it to perform our desired tasks as we did in case of Simple Remote control.
    ```
    public class RemoteLoader{
    public static void main(String args[]){
        //  Create a RemoteControl
        RemoteControl remoteControl = new RemoteControl();
   
        //Make Devices over which Remote Control can operate upon
        //  Create a living room Light object
        Light livingRoomLight = new Light("Living Room");
        //  Create a kitchen room Light object
        Light kitchenLight = new Light("Kitchen");
        //  Create a Ceiling Fan object
        CeilingFan ceilingFan = new CeilingFan("Living Room");
        //  Create a Stereo object
        Stereo stereo = new Stereo("Living Room");
        
        //  Crate Command to be loaded on Remote Control buttons
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);

        CeilingFanOnCommand ceilingFanOn = new CeilingFanOnCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);

        StereoOnWithCDCommand stereoOnWithCD = new StereoOnWithCDCommand(stereo);
        StereoOffCommand stereoOff = new StereoOffCommand(stereo);
        
        //  Load commands on Remote control buttons
        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remoteControl.setCommand(1, kitchenLightOn, kitchenLightOff);
        remoteControl.setCommand(2, ceilingFanOn, ceilingFanOff);
        remoteControl.setCommand(3, stereoOnWithCD, stereoOff);

        System.out.println(remoteControl);
        
        // Remote Control is ready to operate upon different devices now.
        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);
        remoteControl.onButtonWasPushed(2);
        remoteControl.offButtonWasPushed(2);
        remoteControl.onButtonWasPushed(3);
        remoteControl.offButtonWasPushed(3);
    }
}
    ```

#### Note: This code is present in [remote_control](https://github.com/chiragkaushik/Command-Pattern/tree/main/remote_control) directory in this repository
 

 ## Add undo functionality to our Remote Control
 #### Undo button is used to reverse last action performed by a device on receiving command from our Remote Control
 
 1. Add undo method to our Command interface. undo() method mirrors the execute() method.
     ```
     public interface Command {
        public void execute();
        public void undo();
     }
    ```
 2. Define device which is to be operated by Remote Control, as we defined in our previous Remote Control.
 3. Define Command which implements the Common Command interface.
    ```
    public class LightOnCommand implements Command{
      Light light;
      public LightOnCommand(Light light){
         this.light = light;
      }
      public void execute(){
         light.on();
      }
      public void undo() {
         light.off();
      }
    }
    ```
    ```
    public class LightOffCommand implements Command{
          Light light;
          public LightOffCommand(Light light){
             this.light = light;
          }
          public void execute(){
             light.off();
          }
          public void undo() {
             light.on();
          }
        }
    ```
    # Method Usage
   - execute() method in a command to stores how the particular task is executed on the device. For example: how to 
   turn on/off light in this case.
   - undo() method will simply reverse the action defined in execute() method.
 4. Now to add Undo functionality to our Remote Control, we only have to do only few changes in our previous Remote Control.
    ```
    public class RemoteControlWithUndo{
        Command[] onCommands;
        Command[] offCommands;
    
        // Here is where we'll stash the last command executed for the undo button
        Command undoCommand;
    
        public RemoteControlWithUndo(){
            onCommands = new Command[7];
            offCommands = new Command[7];
    
            Command noCommand = new NoCommand();
            for(int i = 0 ; i < 7 ; i++){
                onCommands[i] = noCommand;
                offCommands[i] = noCommand;
            }
    
            //  Just like the other slots, undo starts off with a NoCommand, 
            //  so pressing undo before any other button won't do anything at all
            undoCommand = noCommand;
        }
    
        public void setCommand(int slot, Command onCommand, Command offCommand){
            onCommands[slot] = onCommand;
            offCommands[slot] = offCommand;
        }
    
        public void onButtonWasPushed(int slot){
            onCommands[slot].execute();
    
            //  When a button is pressed, we take the command and first execute it, then we save its 
            //  reference to it in the undoCommand instance variable. We do this for both "on" and "off"
            //  Commands.
            undoCommand = onCommands[slot];
        }
    
        public void offButtonWasPushed(int slot){
            offCommands[slot].execute();
            undoCommand = offCommands[slot];
        }
    
        public void undoButtonWasPushed(){
            //  When the undo button is pressed, we invoke the undo() method of the command stored in 
            //  undoCommand. This reverses the operation of the last Command executed.
            undoCommand.undo();
        }
    
        public String toString(){
            StringBuffer sb = new StringBuffer();
            sb.append("\n-----------------------Remote Control----------------------------\n");
            for(int i = 0 ; i < 7; i++){
                sb.append("[slot "+ i +"] "+ onCommands[i].getClass().getName() + "      "+ offCommands[i].getClass().getName() + "\n");
            }
            return sb.toString();
        }
    }
    ```
 5. Now we can define Remote Loader and assign tasks to the Remote Control slots and use it to perform our desired tasks as we did in our previous Remote controls.
    ```
    public class RemoteLoader{
        public static void main(String args[]){
            RemoteControlWithUndo remoteControl = new RemoteControlWithUndo();
            Light livingRoomLight = new Light("Living Room");
            CeilingFan ceilingFan = new CeilingFan("Living Room");
    
            LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
            LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
    
            CeilingFanMediumCommand ceilingFanMedium = new CeilingFanMediumCommand(ceilingFan);
            CeilingFanHighCommand ceilingFanHigh = new CeilingFanHighCommand(ceilingFan);
            CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);
    
            remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
            remoteControl.setCommand(1, ceilingFanMedium, ceilingFanOff);
            remoteControl.setCommand(2, ceilingFanHigh, ceilingFanOff);
    
            System.out.println(remoteControl);
    
            remoteControl.onButtonWasPushed(0);
            remoteControl.offButtonWasPushed(0);
            remoteControl.undoButtonWasPushed();
    
            remoteControl.onButtonWasPushed(1);
            remoteControl.offButtonWasPushed(1);
            remoteControl.undoButtonWasPushed();
    
            remoteControl.onButtonWasPushed(2);
            remoteControl.offButtonWasPushed(2);
            remoteControl.undoButtonWasPushed();
        }
    }
    ```


#### Note: This code is present in [remote_control_with_undo](https://github.com/chiragkaushik/Command-Pattern/tree/main/remote_control_with_undo) directory in this repository
