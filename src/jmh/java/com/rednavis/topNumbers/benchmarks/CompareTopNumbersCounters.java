package com.rednavis.topNumbers.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.rednavis.topNumbers.Runner.*;

/**
 * Compare performance for several kinds of top 10 numbers counters on huge stream with using JMH library.
 *
 * @author Kate Novik
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 2)
@Measurement(iterations = 4)
public class CompareTopNumbersCounters {

    @Param({"100000000"})
    private int N;

    private Supplier<Stream<Integer>> supplier;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(CompareTopNumbersCounters.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        supplier = () -> Stream.iterate(0, value -> value + 1).limit(N);
    }

    @Benchmark
    public List<Integer> getPriorityQueueScore(Blackhole bh) {
        return getTopNByPriorityQueue(supplier.get());
    }

    @Benchmark
    public List<Integer> getSortedStreamScore(Blackhole bh) {
        return getTopNBySortedStream(supplier.get());
    }

    @Benchmark
    public List<Integer> getHeapScore(Blackhole bh) {
        return getTopNByMinHeap(supplier.get());
    }

}
