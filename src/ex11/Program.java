package ex11;

// Java's Singleton

import ex11_2.Point;

import java.lang.reflect.Constructor;

// 문제점
// 1. Reflection
// 2. Serialization
//   => 조슈아 블로크의 싱글톤

// 3. 프로그램 시작시에 여러개의 싱글톤이 생성 / 초기화되므로
//    프로그램 구동 속도에 악영향을 미친다.
//   => 지연 초기화 싱글톤
//      : 사용자가 처음 싱글톤에 접근하였을 때, 객체를 생성/초기화 하는 기법


/*
class Cursor {
    // 2. 오직 한개의 객체
    private static final Cursor INSTANCE = new Cursor();

    // 1. private 생성자
    private Cursor() {}

    // 3. 동일한 참조를 얻는 정적 메소드
    public static Cursor getInstance() {
        return INSTANCE;
    }
}

public class Program {
    public static void main(String[] args) throws Exception {
        Class clazz = Cursor.class;
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        Cursor c = (Cursor) constructor.newInstance();

        System.out.println(c);
        System.out.println(Cursor.getInstance());
    }
}

*/

/*
// 직렬화와 리플렉션에 안전한 싱글톤을 생성하는 방법 - 조슈아 블로크의 싱글톤
enum Cursor {
    INSTANCE;

    void move() {
        System.out.println("Cursor moved");
    }
}

public class Program {
    public static void main(String[] args) throws Exception {
        Cursor.INSTANCE.move();
    }
}
*/

// 3. 지연 초기화 싱글톤
//  문제점
//    => 멀티 스레드에 안전하지 않다.
/*
class Cursor {
    private static Cursor sInstance;
    private static final Object MUTEX = new Object();

    private Cursor() {}

    // DCLP(Double Checked Locking Pattern)
    //  => 코드가 '선언적'이지 않다.
    public static Cursor getInstance() {
        if (sInstance == null) {
            synchronized (MUTEX) {
                if (sInstance == null)
                    sInstance = new Cursor();
            }
        }

        return sInstance;
    }

    /*
    public static Cursor getInstance() {
        synchronized (MUTEX) {
            if (sInstance == null)
                sInstance = new Cursor();
        }

        return sInstance;
    }

}
*/

// IODH(Initialization-on-demand holder) Singleton
//  => 중첩 클래스의 static 필드는 처음 접근하는 시점에 초기화된다.
class Cursor {
    private Cursor() {}

    static class Singleton {
        private static final Cursor INSTANCE = new Cursor();
    }

    public static Cursor getInstance() {
        return Singleton.INSTANCE;
    }
}

//class Point {
//    static int n = 0;
//
//    // 정적 팩토리 메소드
//    static Point newPoint() {
//        ++n;
//        System.out.println(n + "번째 객체");
//        return new Point();
//    }
//}

interface OnClickListener {
    void onClick(Button button);
}

class Button {
    private OnClickListener listener;
    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void click() {
        if (listener != null) {
            listener.onClick(this);
        }
    }
}


public class Program {
    public static void main(String[] args) {
        Button button = new Button();
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(Button button) {
                System.out.println("Button Clicked");
            }
        });

        button.click();


//        Point p1 = Point.newPoint();
//        Point p2 = Point.newPoint();
//        Point p3 = Point.newPoint();

        // Point p1 = Point.Utils.newPoint();
        // Point p1 = Point.Companion.newPoint();
    }
}











