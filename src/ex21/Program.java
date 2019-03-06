package ex21;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        ArrayList list1 = new ArrayList();
        list1.add("hello");
        list1.add(42);

        // 자바의 제네릭은 아래 코드를 컴파일러가 타입을 체크하는 용도로만
        // 사용합니다.
        //  : 실제 바이트 코드는 위의 넌 제네릭과 동일하게 생성됩니다.
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("hello");
        // list2.add(42);
    }
}
