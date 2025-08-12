import java.util.Scanner;
import java.util.ArrayList; // a dynamic array that can grow and shrink at runtime
import java.util.List; // an interface that arrayList implements making the code more flexable if switch to different list implent
public class DataVisualizer {
    private static final String STOP_INPUT = "-1";
    private static final String SEPARATOR = "-----------------------------------------";
    private static final int TITLE_WIDTH = 35;
    private static final int COLUMN_WIDTH = 20;
    private static final int VALUE_WIDTH = 23;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get headers
        String title = getInput(scanner,"Enter a title for the dat:");
        String columnOneHead = getInput(scanner, "Enter the colum 1 header;");
        String columnTwoHead = getInput(scanner, "Enter the column 2 header");

        // Collect data points
        List<String> authorNames = new ArrayList<>();
        List<Integer> novelCounts = new ArrayList<>();
        collectDataPoints(scanner, dataName, dataValues);

        // Display the results















        String dataPoint; // holds user inputs
        while (true) { // infinite loop to keep asking for data points until user types "-1" then breaks
            System.out.println("Enter a data point (-1 to stop input):");
            dataPoint = scanner.nextLine();


            if (dataPoint.equals("-1")) {
                break;
            }
            // validating - counts how many commas in input
            /*
            dataPoint.chars() converts the string into a stream of characters
            .filter(ch -> ch == ',') filters the stream to only commas
            .count(): counts how many commas are in the string
            dataPoint.split(","): Splits the string at the comma into an array.
            */

            long commaCount = dataPoint.chars().filter(ch -> ch == ',').count();
            if ( commaCount == 0) { // if 0 commas display error
                System.out.println("Error: Too many commas in input." + "\n");
                continue;
            }

            String[] part = dataPoint.split(",");
            if (part.length != 2) {
                System.out.println("Error: No comma in string." + "\n");
                continue;
            }

            try {
                String name = part[0].trim(); // takes everything before the comma, remove extr spaces and stores it in "name"
                int number = Integer.parseInt(part[1].trim()); // same goes from above but stores it in "number"

                // stores data in ArrayList
                authorNames.add(name);
                novelCounts.add(number);

                // displays what the user typed
                System.out.println("Data string: " + name);
                System.out.println("Data integer: " + number + "\n");
            // it Integer.pareInt() fails (user types a non-integer) display error message.
            } catch (Exception ex) {
                System.out.println("Error: Comma not followed by an integer.\n");
            }

        }

        /*
        %33s right justify the title within 33 spaces and %n newLine
        %-20s: Left-justify the first column within 20 spaces.
        |: A literal vertical bar in the middle as a column separator.
        Right-justify the second column within 23 spaces.
         */
        System.out.println();
        System.out.printf("%33s%n", titleData);
        System.out.printf("%-20s|%23s%n", columnOneHead, columnTwoHead);
        System.out.println("--------------------------------------------");

        /*
        Loop through each author and their novel count.
        "*".repeat(novelCounts.get(i)): Create a string of * repeated novelCounts.get(i) times (the integer we stored).
         */

        for (int i = 0; i < authorNames.size(); i++) {
            System.out.printf("%-20s|%23s%n", authorNames.get(i), novelCounts.get(i));
        }
        System.out.println();
        for (int i = 0; i < authorNames.size(); i++) {
            System.out.printf("%20s %s%n", authorNames.get(i),"*".repeat(novelCounts.get(i)));

        }

    }
}
