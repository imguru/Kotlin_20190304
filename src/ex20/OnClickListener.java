package ex20;

// SAM(Functional Interface)

@FunctionalInterface
public interface OnClickListener {
    void onClick();

    // void foo();

    public static void main(String[] args) {
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick() {
                System.out.println("익명 객체");
            }
        };

        // Functional Interface 에 대해서 람다로 변환해서 사용할 수 있다.
        listener = () -> {
            System.out.println("Lambda");
        };


    }
}

