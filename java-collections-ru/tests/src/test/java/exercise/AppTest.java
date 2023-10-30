package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;

import static exercise.App.take;

class AppTest {

    @Test
    void testTake() {
        List<Integer> listRight = take(List.of(100, 200, 300, 400, 500),5);
        assertThat(listRight.size()).isEqualTo(5);
        assertThat(listRight).contains(100, 200, 300, 400, 500);

        List<Integer> listEmpty = take(List.of(),2);
        assertThat(listEmpty.size()).isEqualTo(0);

        List<Integer> list = take(List.of(1, 2, 3),0);
        assertThat(list.size()).isEqualTo(0);

        List<Integer> listWithThreeElements = take(List.of(10, 9, 8),5);
        assertThat(listWithThreeElements.size()).isEqualTo(3);

        List<Integer> listEmpty2 = take(List.of(10, 9, 8),-2);
        assertThat(listEmpty2.size()).isEqualTo(0);
    }
}
