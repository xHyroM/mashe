package hyro.mashe;

import hyro.mashe.annotations.Listen;
import hyro.mashe.events.TestEvent;

public class TestMashe {
    public TestMashe() {
        new Mashe().register(this);

        Mashe.getInstance().fire(new TestEvent());
    }

    @Listen
    public void on(TestEvent e) {
        System.out.println("It works!!!");
    }
}
