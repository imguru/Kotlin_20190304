package ex17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Java's Collection
//   default: Mutable
//      컬렉션의 내용을 변경하거나 추가(또는 삭제)하는 것이 가능합니다.

public class Program {
    public static void main(String[] args) {
        // List<Integer> list = new ArrayList<>();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.add(10);
        list.add(20);

        // Collection을 실행 시간에 Immutable 상태로 변경합니다.
        list = Collections.unmodifiableList(list);

        // 다른 스레드에서 수정할 경우, UnsupportedOperationException 발생!!!
        list.add(30);

        // 아래의 코드가 실행되는 도중에, 컬렉션의 크기가 변경되면
        // 안됩니다.
        for (Integer e : list) {
            System.out.println(e);
        }

    }
}
