public class Stereo{
    String place;
    public Stereo(String place){
        this.place = place;
    }
    public void on(){
        System.out.println(this.place + "Stereo is on.");
    }
    public void off(){
        System.out.println(this.place + "Stereo is off.");
    }
    public void setCd(){
        System.out.println(this.place + "Stereo is set for CD input.");
    }
    public void setDvd(){
        System.out.println(this.place + "Stereo is set for DVD input.");
    }
    public void setRadio(){
        System.out.println(this.place + "Stereo is set for Radio input.");
    }
    public void setVolume(int volume){
        System.out.println(this.place + "Stereo volume set to" + volume);
    }
}
