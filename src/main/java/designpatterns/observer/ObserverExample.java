package main.java.designpatterns.observer;

public class ObserverExample {
    public static void main(String[] args) {
        SimpleSubject subject = new SimpleSubject();
        SimpleObserver observer = new SimpleObserver(subject);

        subject.setValue(5);
    }
}
