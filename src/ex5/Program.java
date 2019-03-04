package ex5;

class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// Java's - split / replace
//  => 정규 표현식을 인자로 받는다.

public class Program {
    public static void main(String[] args) {
        String token = "\\.";
        String name = "Gildong.Hong";

        String[] arr = name.split(token);
        for (String e : arr) {
            System.out.println(e);
        }

//        User user = new User();
//        System.out.println(user.getName());
//        user.setName("Bob");
    }
}
