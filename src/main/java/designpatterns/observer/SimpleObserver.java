package main.java.designpatterns.observer;

public class SimpleObserver implements Observer {
    private int value;

    public SimpleObserver(Subject simpleSubject) {
        simpleSubject.registerObserver(this);
    }

    @Override
    public void update(int value) {
        this.value = value;
        display();
    }

    private void display() {
        System.out.println("Value : " + this.value);
    }


}
