

import com.rednavis.topNumbers.Runner;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;


/**
 * Tests for several kinds of getting top 10 numbers from stream.
 *
 * @author Kate Novik
 */
public class RunnerTest {

    @Test
    public void testTopNumbersCounterByPriorityQueue() {
        //get top 10 numbers for stream with n=100
        Stream<Integer> intStream = Stream.iterate(0, value -> value + 1).limit(100);
        List<Integer> expectedList = Arrays.asList(99, 98, 97, 96, 95, 94, 93, 92, 91, 90);
        List<Integer> actualList = Runner.getTopNByPriorityQueue(intStream);

        assertEquals(expectedList, actualList);
    }

    @Test
    public void testTopNumbersCounterBySortedStream() {
        //get top 10 numbers for stream with n=100
        Stream<Integer> intStream = Stream.iterate(0, value -> value + 1).limit(100_000_000);
        List<Integer> expectedList = Arrays.asList(99_999_999, 99_999_998, 99_999_997, 99_999_996, 99_999_995,
                99_999_994, 99_999_993, 99_999_992, 99_999_991, 99_999_990);
        long startTime1 = System.currentTimeMillis();
        List<Integer> actualList = Runner.getTopNBySortedStream(intStream);
        long diffTime1 = System.currentTimeMillis() - startTime1;
        System.out.printf("Score of getting top 10 numbers by Stream sorted (ms): %d\n", diffTime1 );
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testTopNumbersCounterByHeap() {
        //get top 10 numbers for stream with n=100
        Stream<Integer> intSmallStream = Stream.iterate(0, value -> value + 1).limit(100);
        List<Integer> expectedList = Arrays.asList(99, 98, 97, 96, 95, 94, 93, 92, 91, 90);
        List<Integer> actualList = Runner.getTopNByMinHeap(intSmallStream);

        assertEquals(actualList, expectedList);

        //get top 10 numbers for stream with n=100_000_000
        Stream<Integer> intHugeStream = Stream.iterate(0, value -> value + 1).limit(100_000_000);
        List<Integer> expectedResult = Arrays.asList(99_999_999, 99_999_998, 99_999_997, 99_999_996, 99_999_995,
                99_999_994, 99_999_993, 99_999_992, 99_999_991, 99_999_990);
        List<Integer> actualResult = Runner.getTopNByMinHeap(intHugeStream);
        assertEquals(expectedResult, actualResult);
    }
}
