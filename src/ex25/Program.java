package ex25;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Decorator Pattern

// 자바(JVM) - 메모리 자원에 대한 해지는 GC에 책임진다.
//          - 비메모리 자원에 대한 해지는 개발자가 직접 해야한다.
//           : 비메모리 자원에 대한 명시적인 종료 메소드를 반드시 호출해야 한다.

// Try with resources를 지원하도록 하고 싶다.
//   : AutoCloseable
class MyResource implements AutoCloseable {
    void foo() {
        System.out.println("foo");
    }

    @Override
    public void close() throws Exception {
        System.out.println("MyResource() 비메모리 자원을 해지하는 코드");
    }
}


public class Program {
    /*
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("a.txt");
        DataOutputStream dos = new DataOutputStream(fos);

        // dos.writeBoolean(false);
        // dos.writeInt(42);
        dos.writeChar('A');
        dos.writeChar('C');

        fos.close();
        dos.close();
    }
    */

    // Java 6 + 종료 메소드
    /*
    public static void main(String[] args) {
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            fos = new FileOutputStream("a.txt");
            dos = new DataOutputStream(fos);

            dos.writeChar('A');
            dos.writeChar('C');
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    */

    // Java 7: Try with resources - close()를 예외 안전하게 호출하는 구문
    public static void main(String[] args) {
        try (MyResource resource = new MyResource()) {
            resource.foo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        try (FileOutputStream fos = new FileOutputStream("a.txt");
             BufferedOutputStream bos = new BufferedOutputStream(fos);
        DataOutputStream dos = new DataOutputStream(bos)) {

            dos.writeChar('A');
            dos.writeChar('C');
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        List<String> cities = Arrays.asList("A", "B", "C");

        List<Integer> result = cities.stream()
                .map(String::length)
                .collect(Collectors.toList());

    }
}

