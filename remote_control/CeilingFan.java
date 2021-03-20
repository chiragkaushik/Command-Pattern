public class CeilingFan{
    String place;
    public CeilingFan(String place){
        this.place = place;
    }

    public void on(){
        System.out.println(this.place + " ceiling fan is on");
    }

    public void off(){
        System.out.println(this.place + " ceiling fan is off");
    }
}
