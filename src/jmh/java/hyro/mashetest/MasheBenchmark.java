package hyro.mashetest;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
@Measurement(iterations = 5, time = 1)
@Fork(1)
@Warmup(iterations = 2, time = 1)
public class MasheBenchmark {
    private Mashe mashe;

    @Setup
    public void setup() {
        this.mashe = new Mashe();

        this.mashe.register(TestEvent.class, (e) -> {
            // nothing to do
        });
    }

    @Benchmark
    public void benchmark() {
        for (int i = 0; i < 1_000_000; i++) {
            this.mashe.fire(new TestEvent());
        }
    }
}
