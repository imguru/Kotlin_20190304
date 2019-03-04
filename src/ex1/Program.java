package ex1;

import java.util.Objects;

class User {
    private String name;
    private int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

public class Program {
    static void foo() {

    }

    public static void main(String[] args) {
        // System.out.println(foo());
    }
}
