import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class DataVisualizer {
    private static final String STOP_INPUT = "-1";
    private static final String SEPARATOR = "-----------------------------------------";
    private static final int TITLE_WIDTH = 35;
    private static final int COLUMN_WIDTH = 20;
    private static final int VALUE_WIDTH = 23;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get headers from user
        String title = getInput(scanner, "Enter a title for the data: ");
        String columnOneHead = getInput(scanner, "Enter the column 1 header:");
        String columnTwoHead = getInput(scanner, "Enter the column 2 header:");

        // Collect data points
        List<String> dataNames = new ArrayList<>();
        List<Integer> dataValues = new ArrayList<>();
        collectDataPoints(scanner, dataNames, dataValues);

        // Display the results
        displayResults(title, columnOneHead, columnTwoHead, dataNames, dataValues);

        scanner.close();

    }
    // Gets input from user with a prompt
    private static String getInput(Scanner scanner, String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    // Collects data points from user until they enter -1
    private static void collectDataPoints(Scanner scanner, List<String> names, List<Integer> values) {
        System.out.println("\nEnter data points in format: name,value");
        System.out.println("Enter -1 to stop input\n");

        while (true) {
            System.out.println("Enter a data point: ");
            String dataPoint = scanner.nextLine();

            if (dataPoint.equals(STOP_INPUT)) {
                break;
            }

            if (processDataPoint(dataPoint, names, values)) {
                // show confirmation message
                System.out.println("✓ Added: " + names.get(names.size() - 1) + " -> " + values.get(values.size()
                - 1) + "\n");
            }
        }
    }

    /**
     * processes a single data point and adds it to the list if valid
     * returns true if valid, false if there was an error
     */
    private static boolean processDataPoint(String dataPoint, List<String> names, List<Integer> values) {
        // validating comma count
        long commaCount = dataPoint.chars().filter(ch -> ch == ',').count();

        if (commaCount == 0) {
            System.out.println("❌ Error: No commas found. Please try again.\n");
            return false;
        }

        if (commaCount > 1 ) {
            System.out.println("❌ Error: Too many commas found. Please try again.\n");
            return false;
        }

        String[] parts = dataPoint.split(",");
        if (parts.length != 2) {
            System.out.println("❌ Error: Invalid format. Please try again.\n");
            return false;
        }

        try {
            String name = parts[0].trim();
            int value = Integer.parseInt(parts[1].trim());

            if(name.isEmpty()) {
                System.out.println("❌ Error: Name cannot be empty.\n");
                return false;
            }

            if (value < 0) {
                System.out.println("❌ Error: Value cannot be negative.\n");
                return false;
            }

            names.add(name);
            values.add(value);

            return true;

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Value must be an integer.\n");
            return false;
        }

    }
    // Displays the formatted results in both table and chart format
    private static void displayResults(String title, String columnOneHead, String columnTwoHead, List<String> names, List<Integer> values) {
        if (names.isEmpty()) {
            System.out.println("No data points to display.");
            return;
        }

        System.out.println("\n" + "=".repeat(50));
        System.out.printf("%25s%n","RESULTS");
        System.out.println("=".repeat(50));

        // Display table format
        displayTable(title, columnOneHead, columnTwoHead, names, values);

        System.out.println();

        // Display chart format
        displayChart(names, values);

    }

    // Displays data in table format
    private static void displayTable(String title, String columnOneHead, String columnTwoHead, List<String> names, List<Integer> values) {
        System.out.printf("%" + TITLE_WIDTH + "s%n", title);
        System.out.printf("%-" + COLUMN_WIDTH + "s|%" + VALUE_WIDTH + "s%n", columnOneHead, columnTwoHead);
        System.out.println(SEPARATOR);

        for (int i = 0; i < names.size(); i++) {
            System.out.printf("%-" + COLUMN_WIDTH + "s|%" + VALUE_WIDTH + "s%n", names.get(i), values.get(i));
        }
    }

    // Displays data in horizontal bar chart format
    private static void displayChart(List<String> names, List<Integer> values) {
        System.out.println("VISUAL CHART:");
        System.out.println("-".repeat(40));

        for (int i = 0; i < names.size(); i++) {
            String stars = "★".repeat(values.get(i));
            System.out.printf("%-" + COLUMN_WIDTH + "s %s (%d)%n", names.get(i), stars, values.get(i));

        }
    }
}
