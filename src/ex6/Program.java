package ex6;

class Person {
    // package 안에서 접근이 가능합니다.
    protected int money = 42;
}

public class Program {
    public static void main(String[] args) {
        Person person = new Person();
        person.money = 100;
    }
}
