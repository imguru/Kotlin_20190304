package ex10;

// 1. Object.clone() 오버라이드
// 2. protected -> public
// 3. 예외를 clone 메소드 내부에서 처리해야 합니다.
// 4. 부모 메소드의 반환 타입을 하위 타입으로 변경하는 것이 가능합니다.
//    => 공변 반환의 법칙
// 5. 반환 타입을 Point 타입으로 변경하고, 캐스팅을 내부에서 처리합니다.
// 6. clone을 제공한다면, 반드시 Cloneable 인터페이스 기반으로 클래스를 제공해야 합니다.
//    Cloneable: mark up interface
//      -> 메소드가 아무것도 없는, 타입 확인용 인터페이스


/*
class Car implements Cloneable {
    String name;
}

class Truck extends Car {

}
*/

// 한계
// - 부모 클래스가 Cloneable 하지 않다면, 자신도 복제할 수 없다.

// 대안
// - 복사 생성자를 사용하자.
//   복사 생성자: 자신과 동일 타입을 인자로 생성자를 제공하자.


class Point implements Cloneable {
    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Point(Point rhs) {
        this.x = rhs.x;
        this.y = rhs.y;
    }



//    @Override
//    public Point clone() {
//        try {
//            return (Point) super.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

//    @Override
//    public String toString() {
//        return "Point{" +
//                "x=" + x +
//                ", y=" + y +
//                '}';
//    }
}

public class Program {
    public static void main(String[] args) {
        Point p1 = new Point(10, 32);
        // Point p2 = p1.clone();
        Point p2 = new Point(p1);

        System.out.println(p1);
        System.out.println(p2);
    }
}









