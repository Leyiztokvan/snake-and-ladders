package snakes;

public class MockDie implements IDie {

    private int rollValue;

    public MockDie(int rollValue) {
        this.rollValue = rollValue;
    }

    @Override
    public int roll() {
        return rollValue;
    }

    public void setRollValue(int newRollValue) {
        this.rollValue = newRollValue;
    }
}
