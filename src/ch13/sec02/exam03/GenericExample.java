package ch13.sec02.exam03;

public class GenericExample {
    public static void main(String[] args) {

        Box<String> box1 = new Box<>();
        box1.content = "100";

        Box<String> box2 = new Box<>();
        box2.content = "100";

        System.out.println(box1.compare(box2));
    }
}
