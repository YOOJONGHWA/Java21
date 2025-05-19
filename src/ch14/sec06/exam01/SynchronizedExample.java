package ch14.sec06.exam01;

public class SynchronizedExample {

    public static void main(String[] args) {

        Calculator calc = new Calculator();

        User1Thread user1Thread = new User1Thread();
        user1Thread.setCalculator(calc);
        user1Thread.start();

        User2Thread user2Thread = new User2Thread();
        user2Thread.setCalculator(calc);
        user2Thread.start();
    }
}
