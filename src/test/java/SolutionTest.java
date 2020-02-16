import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void si_el_5_se_repite_mas_devuelve_el_5() {
        int[] numbers = {1,5,5,3,2,2,5};
        int[] result = {5};
        Solution sut = new Solution();
        assertNotNull(sut.solution2(3,5,numbers));
        assertArrayEquals(sut.solution2(3,5,numbers),result );
        assertEquals(sut.solution2(3,5,numbers)[0],5);
    }

    @Test
    void test() {
        Solution sut = new Solution();
        assertArrayEquals(new int[]{}, sut.solution(3, 3, new int[]{1, 2, 3}));
        assertArrayEquals(new int[]{2}, sut.solution(1, 1, new int[]{1}));
        assertArrayEquals(new int[]{2, 3}, sut.solution2(3, 5, new int[]{2, 3, 3, 3, 2, 2, 3}));
        assertArrayEquals(new int[]{2, 3}, sut.solution(4, 2, new int[]{1, 2, 2, 1, 2}));
        assertArrayEquals(new int[]{}, sut.solution(3, 2, new int[]{1, 1, 1, 1, 1, 1}));
        assertArrayEquals(new int[]{1}, sut.solution(3, 2, new int[]{1, 1, 1, 1, 1, 1, 1}));
        assertArrayEquals(new int[]{1}, sut.solution(1, 2, new int[]{1, 1, 1, 1, 1, 1, 1}));
        assertArrayEquals(new int[]{2}, sut.solution(1, 2, new int[]{1}));
        assertArrayEquals(new int[]{8}, sut.solution(3, 10, new int[]{1, 6, 6, 7, 7, 8, 8, 8}));
        assertArrayEquals(new int[]{2}, sut.solution(5, 5, new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}));
        assertArrayEquals(new int[]{10, 11}, sut.solution(4, 20, new int[]{10, 10, 11, 10, 11, 11, 9, 11, 10}));
        assertArrayEquals(new int[]{4, 5}, sut.solution(5, 10, new int[]{4, 2, 4, 4, 1, 4, 5, 3, 5, 5, 3}));
    }
}