import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author david palomar delgado
 */
public class Solution {
    /**
     * Checks for array leaders
     * @param K range size
     * @param M Maximum integer
     * @param A Initial array
     * @return the array with leaders without repetition
     */
    public int[] solution(int K, int M, int[] A){

        int n = A.length;
        SortedSet<Integer> sortedSet = new TreeSet<>();

        for (int i=0; i < n; i += K){
            int[] B = new int[n];
            if (n < i+K) {
                K = n-i;
            }
            System.arraycopy(A,i,B,i,K);

            int[] rango = Arrays.stream(B).map(c -> c!=0 ? c+ 1: c).toArray();

            for (int j = 0; j < n; j++) {
                B[j] = A[j];
                if (rango[j] != 0) {
                    B[j] = rango[j];
                }
            }
            sortedSet.addAll(getOcurrences(n, arrayToList(B)));
        }
        return sortedSet.stream().mapToInt(i -> i).toArray();

    }

    /**
     * Find the numbers that are repeated more than half of array size
     * @param size initial array size
     * @param lista the array to looks for the numbers
     * @return the numbers
     */
    private List<Integer> getOcurrences(int size, List<Integer> lista) {
        return  lista.stream()
                .filter(i -> Collections.frequency(lista, i) > size / 2)
                .collect(Collectors.toList());
    }

    /**
     * Utility method to convert an array to a List of wrappers
     * @param A the array to convert
     * @return the Wrapper List
     */
    private List<Integer> arrayToList(int[] A) {
        return IntStream.of(A)
                .boxed()
                .collect(Collectors.toList());
    }

}
