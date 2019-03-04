package ex7;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// Inner 클래스는 Outer 클래스에 대한 참조가 암묵적으로 포함되어 있다.
//  => 거의 사용하지 않는다.
//     '누수의 위험성이 있다.'


interface State extends Serializable {
}

interface View {
    State getCurrentState();

    void restoreState(State state);
}

// Memento Pattern
//  : 필요한 필드를 별도의 클래스로 캡슐화해서 저장하거나 복원한다.
class Button implements View {
    static class ButtonState implements State {
        int x;
        int y;

        ButtonState(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    @Override
    public State getCurrentState() {
        return new ButtonState(10, 32);
    }

    @Override
    public void restoreState(State state) {

    }
}


public class Program {
    // Try with Resources
    public static void main(String[] args) {
        Button button = new Button();

        try (FileOutputStream fos = new FileOutputStream("state.out");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(button.getCurrentState());  // state.out

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
public class Program {
    public static void main(String[] args) {
        Button button = new Button();
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("state.out");
            oos = new ObjectOutputStream(fos);

            oos.writeObject(button.getCurrentState());  // state.out
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
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
*/







