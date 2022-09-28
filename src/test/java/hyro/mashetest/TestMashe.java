package hyro.mashetest;

import hyro.mashetest.annotations.Listen;
import hyro.mashetest.events.TestEvent;

public class TestMashe {
    public Mashe mashe;

    public TestMashe() {
        mashe = new Mashe();
        mashe.register(this);

        mashe.fire(new TestEvent());
    }

    @Listen
    public void on(TestEvent e) {
        System.out.println("It works!!!");
    }
}
