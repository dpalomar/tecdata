import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {
    public int[] solution(int K, int M, int[] A){

        int n = A.length;

        List<Integer> lista = new ArrayList<>();
        List<Integer> numbers = arrayToList(A);

        for (int i=0; i < A.length; i += K){
            lista.addAll(getRange(K, numbers, i));
        }
//        final Set<Integer> plainOcurrences = getOcurrences(n, numbers);
        final List<Integer> rangeOcurrences = getOcurrences(n, lista);

        SortedSet<Integer> sortedSet = new TreeSet<>();
//        sortedSet.addAll(plainOcurrences);
        sortedSet.addAll(rangeOcurrences);
        return sortedSet.stream().mapToInt(i -> i).toArray();

    }

    private List<Integer> getOcurrences(int size, List<Integer> lista) {
        final List<Integer> collect = lista.stream()
                .filter(i -> Collections.frequency(lista, i) >= size / 2)
                .collect(Collectors.toList());
        collect.replaceAll(value -> value - 1);
        return collect;

//        final Set<Integer> collect1 = collect.stream()
//                .filter(i -> Collections.frequency(collect, i) > 1)
//                .collect(Collectors.toSet());
//        return collect1;
    }

    private List<Integer> arrayToList(int[] A) {
        return IntStream.of(A)
                .boxed()
                .collect(Collectors.toList());
    }

    private List<Integer> getRange(int K, List<Integer> numbers, int i) {
        return Stream.of(
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
    }

    public int[] solution2(final int K, final int M, final int[] A) {
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
