import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SolutionTest {
    private Solution sut;

    @BeforeEach
    void setUp(){
        sut = new Solution();
    }
    @Test
    void given_an_non_valid_input_should_return_empty_array() {
        assertArrayEquals(new int[]{}, sut.solution(3, 3, new int[]{1, 2, 3}));
    }
    @Test
    void given_an_only_one_input_should_return_only_leader() {
        assertArrayEquals(new int[]{2}, sut.solution(1, 1, new int[]{1}));
    }
    @Test
    void given_an_serie_with_valid_segment_should_return_several_leaders() {
        assertArrayEquals(new int[]{2, 3}, sut.solution(3, 5, new int[]{2, 2, 3, 1, 2, 2, 3}));
    }
    @Test
    void given_an_serie_with_valid_long_segment_should_return_just_the_leader() {
          assertArrayEquals(new int[]{5}, sut.solution(5, 10, new int[]{4, 2, 4, 4, 1, 4, 5, 3, 5, 5, 3}));
    }
}