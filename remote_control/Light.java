public class Light{
    String place;
    public Light(String place){
        this.place = place;
    }
    public void on(){
        System.out.println(this.place + " light is on.");
    }
    public void off(){
        System.out.println(this.place + " light is off.");
    }
}
