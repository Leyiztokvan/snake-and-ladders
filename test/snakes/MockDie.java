package snakes;

public class MockDie implements IDie{

    private int rollValue;
    public MockDie(int rollValue){
        this.rollValue = rollValue;
    }

    @Override  //We need to override the roll method:

    public int roll(){
        return rollValue;
    }

    //We create a setter for rollValue:
    public void setRollValue(int newRollValue){
        this.rollValue = newRollValue;
    }
}
