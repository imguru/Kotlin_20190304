package ex4;

/*
class User {
    private String name;
    private String address;
    private int age;
    private int level;
    private int money;

    public User(String name, String address,
                int age, int level, int money) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.level = level;
        this.money = money;
    }

    // 선택적인 필드에 대해서는 오버로딩을 제공하자.
    public User(String name, String address, int age, int level) {
        this(name, address, age, level, 0);
    }

    public User(String name, String address, int age) {
        this(name, address, age, 0, 0);
    }
}


public class Program {
    public static void main(String[] args) {
        String a = "Tom";
        String b = "Suwon";
        int c = 10;
        int d = 20;
        int e = 30;

        // 가독성에 문제가 있고, 인자의 순서가 바뀌었을 때,
        // 문제를 컴파일 타임에 알 수 없다.
        User user = new User(b, a, c, d, e);
    }
}
*/

// 생성자의 인자가 많다면, 빌더 패턴을 고려해라. - Effective Java
//  => AOP(관점 지향 프로그래밍)
//  => 'Annotation'을 통해 코드를 선언적으로 작성해야 한다.
//    : Lombok
/*
class User {
    private String name;
    private String address;
    private int age;
    private int level;
    private int money;

    private User(Builder b) {
        name = b.name;
        address = b.address;
        age = b.age;
        level = b.level;
        money = b.money;
    }

    // 중첩 클래스(Nested Class)
    public static class Builder {
        private final String name;
        private final String address;
        private int age;
        private int level;
        private int money;

        public Builder(String name, String address) {
            this.name = name;
            this.address = address;
        }

        public Builder age(int value) {
            age = value;
            return this;
        }

        public Builder level(int value) {
            level = value;
            return this;
        }

        public Builder money(int value) {
            money = value;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
*/


import kex4.User;

public class Program {
    public static void main(String[] args) {
        String a = "Tom";
        String b = "Suwon";
        int c = 10;
        int d = 20;
        int e = 30;

        // 가독성에 문제가 있고, 인자의 순서가 바뀌었을 때,
        // 문제를 컴파일 타임에 알 수 없다.
        // User user = new User(b, a, c, d, e);

//        User user = new User.Builder("Tom", "Suwon")
//                .age(c)
//                .level(d)
//                .money(e)
//                .build();


        User user = new User("Tom", "Suwon");
        user = new User("Tom", "Suwon", 19);
    }
}








