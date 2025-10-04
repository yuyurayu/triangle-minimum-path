import java.util.*;

public class TriangleMinimumPath {
    
    /**
     * Находит минимальную сумму пути от вершины до основания треугольника
     * @param triangle треугольник в виде списка списков
     * @return минимальная сумма пути
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty()) {
            return 0;
        }
        
        int n = triangle.size();
        
        // Создаем DP таблицу такого же размера как треугольник
        int[][] dp = new int[n][n];
        
        // Шаг 1: Инициализируем последнюю строку (основание)
        // Для основания минимальный путь - само число
        List<Integer> lastRow = triangle.get(n - 1);
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = lastRow.get(j);
        }
        
        // Шаг 2: Заполняем таблицу снизу вверх
        for (int i = n - 2; i >= 0; i--) {
            List<Integer> currentRow = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                // Минимальный путь для текущей ячейки = текущее значение + 
                // минимум из двух возможных путей в следующей строке
                dp[i][j] = currentRow.get(j) + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }
        
        // Шаг 3: Результат находится в вершине треугольника
        return dp[0][0];
    }
    
    /**
     * Восстанавливает сам путь (дополнительная функция)
     */
    public static List<Integer> findMinimumPath(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty()) {
            return new ArrayList<>();
        }
        
        int n = triangle.size();
        int[][] dp = new int[n][n];
        
        // Заполняем DP таблицу
        List<Integer> lastRow = triangle.get(n - 1);
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = lastRow.get(j);
        }
        
        for (int i = n - 2; i >= 0; i--) {
            List<Integer> currentRow = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                dp[i][j] = currentRow.get(j) + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }
        
        // Восстанавливаем путь
        List<Integer> path = new ArrayList<>();
        path.add(triangle.get(0).get(0));
        
        int j = 0; // текущий индекс в строке
        for (int i = 1; i < n; i++) {
            // Выбираем тот путь, который дает минимальную сумму
            if (dp[i][j] < dp[i][j + 1]) {
                path.add(triangle.get(i).get(j));
                // j остается тем же
            } else {
                path.add(triangle.get(i).get(j + 1));
                j = j + 1;
            }
        }
        
        return path;
    }
    
    public static void main(String[] args) {
        // Тест 1
        List<List<Integer>> triangle1 = Arrays.asList(
            Arrays.asList(2),
            Arrays.asList(3, 4),
            Arrays.asList(6, 5, 7),
            Arrays.asList(4, 1, 3, 8)
        );
        
        System.out.println("Тест 1:");
        System.out.println("Треугольник: " + triangle1);
        System.out.println("Минимальная сумма: " + minimumTotal(triangle1));
        System.out.println("Минимальный путь: " + findMinimumPath(triangle1));
        System.out.println();
        
        // Тест 2
        List<List<Integer>> triangle2 = Arrays.asList(
            Arrays.asList(-1),
            Arrays.asList(2, 3),
            Arrays.asList(1, -1, -3),
            Arrays.asList(4, 2, 1, 3)
        );
        
        System.out.println("Тест 2:");
        System.out.println("Треугольник: " + triangle2);
        System.out.println("Минимальная сумма: " + minimumTotal(triangle2));
        System.out.println("Минимальный путь: " + findMinimumPath(triangle2));
    }
}
