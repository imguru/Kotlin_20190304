package ex13;

// 자바의 클로져 - 무명 객체를 만들 때 사용 가능합니다.
//  - Java 8 이전에서는 클로져를 사용하기 위해서는, 반드시 캡쳐되는 변수가
//    final 이어야 했습니다.
interface OnClickListener {
    void onClick();
}

class MyListener implements OnClickListener {
    private String name;
    public MyListener(String name) {
        this.name = name;
    }

    @Override
    public void onClick() {

    }
}

public class Program {
    public static void main(String[] args) {

        String name = "Tom";

//        OnClickListener listener = new OnClickListener() {
//            @Override
//            public void onClick() {
//                System.out.println(name);
//            }
//        };

        // listener.onClick();
    }
}
