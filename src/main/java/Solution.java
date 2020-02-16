import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {
    public int[] solution2(int K, int M, int[] A){

        int n = A.length;

        List<Integer> lista = new ArrayList<>();
        List<Integer> numbers = IntStream.of(A)
                .boxed()
                .collect(Collectors.toList());

        for (int i=0; i < A.length; i += K){
            List<Integer> collect = Stream.of(
                    Arrays.stream(Arrays.copyOfRange(numbers.toArray(), i, K + i))
                            .filter(Objects::nonNull)
                            .map(Integer.class::cast)
                            .collect(Collectors.toList())

            ).map((rango -> {
                if (rango.size() == K) {
                    rango.replaceAll(value -> value + 1);
                }
                return rango;
            })).flatMap(x -> x.stream())
                    .collect(Collectors.toList());
            lista.addAll(collect);

        }

        return lista.stream()
                .filter(i -> Collections.frequency(lista, i) >= A.length / 2)
                .collect(Collectors.toSet())
                .stream()
                .mapToInt(i -> i).toArray();


    }

    public int[] solution(final int K, final int M, final int[] A) {
        final int size = A.length;
        final int enough = size/2 + 1;

        int leader1 = -1;
        int leader2 = -1;

        final int[] occurrences = new int[M + 2];
        for (int i = 0; i < size; i++) {
            occurrences[A[i]]++;
        }

        // incrementing first segment of K elements
        for (int i = 0; i < K; i++) {
            occurrences[A[i]]--;
            occurrences[++A[i]]++;
        }

        // checking if a leader exists already
        for (int i = 0; i < M + 2; i++) {
            if (occurrences[i] >= enough) {
                leader1 = i;
                break;
            }
        }

        // traversing one by one until reaching the end of
        // the array or until two unique leaders are found
        for (int i = 0; i < size - K; i++) {
            occurrences[A[i]]--;
            occurrences[--A[i]]++;
            occurrences[A[i + K]]--;
            occurrences[++A[i + K]]++;

            final int candidateFirst = A[i];
            final int candidateLast = A[i + K];

            if (occurrences[candidateLast] >= enough) {
                if (leader1 == -1) {
                    leader1 = candidateLast;
                } else if (candidateLast != leader1) {
                    leader2 = candidateLast;
                }

                if (leader2 != -1) {
                    break;
                }
            } else if (occurrences[candidateFirst] >= enough) {
                if (leader1 == -1) {
                    leader1 = candidateFirst;
                } else if (candidateFirst != leader1) {
                    leader2 = candidateFirst;
                }

                if (leader2 != -1) {
                    break;
                }
            }
        }
        return buildResultArray(leader1, leader2);
    }

    private int[] buildResultArray(final int leader1, final int leader2) {
        if (leader1 == -1 && leader2 == -1) {
            return new int[0];
        } else if (leader2 == -1) {
            return new int[]{leader1};
        } else if (leader1 < leader2) {
            return new int[]{leader1, leader2};
        } else {
            return new int[]{leader2, leader1};
        }
    }



}
