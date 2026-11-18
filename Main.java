import java.util.ArrayList;
import java.util.List;

public class Main {

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n не може бути від'ємним");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static long fib(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n має бути додатним");
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static long fibIter(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n має бути додатним");
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        long prev2 = 1;
        long prev1 = 1;
        long current = 0;

        for (int i = 3; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }

    public static void generatePermutations(char[] elements, boolean[] used, StringBuilder current) {
        if (current.length() == elements.length) {
            System.out.println(current.toString());
            return;
        }

        for (int i = 0; i < elements.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.append(elements[i]);

                generatePermutations(elements, used, current);

                current.deleteCharAt(current.length() - 1);
                used[i] = false;
            }
        }
    }

    public static long combinations(int n, int k) {
        if (k < 0 || k > n) {
            return 0;
        }
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    public static void printCombinations(char[] elements, int k, int start, List<Character> current) {
        if (current.size() == k) {
            System.out.println(current);
            return;
        }
        for (int i = start; i < elements.length; i++) {
            current.add(elements[i]);
            printCombinations(elements, k, i + 1, current);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        int n = 5;
        char[] set = {'A', 'B', 'C'};

        System.out.println("=== ВАРІАНТ 1 (n = " + n + ", множина = {A, B, C}) ===");

        System.out.println("factorial(" + n + ") = " + factorial(n));

        long recFib = fib(n);
        long iterFib = fibIter(n);
        System.out.println("fib(" + n + ") [рекурсія] = " + recFib);
        System.out.println("fib_iter(" + n + ") [цикл] = " + iterFib);
        System.out.println("Результати збігаються: " + (recFib == iterFib));

        System.out.println("\nУсі перестановки множини {A, B, C} (всього " + factorial(set.length) + "):");
        generatePermutations(set, new boolean[set.length], new StringBuilder());

        System.out.println("\nКількість комбінацій C(3, 2) для множини {A, B, C}: " + combinations(set.length, 2));
        System.out.println("Самі комбінації по 2 елементи з {A, B, C}:");
        printCombinations(set, 2, 0, new ArrayList<>());

        System.out.println("\nКількість комбінацій C(" + n + ", 2) для n = " + n + ": " + combinations(n, 2));
    }
}