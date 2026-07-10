import java.util.Scanner;

public class DS {
    //50 states of the USA
    static String[] states = {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia","Hawaii",
            "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland","Massachusetts", "Michigan", "Minnesota", 
            "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada","New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", 
            "North Dakota","Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina","South Dakota", "Tennessee", "Texas", 
            "Utah","Vermont", "Virginia", "Washington","West Virginia", "Wisconsin", "Wyoming"
    };
    //Display the states
    public static void displayStates() {
        System.out.println("The states names are:");
        for (String state : states) {
            System.out.print(state + " ");
        }
    }

    //Search the state
    public static void searcghState(String pattern) {
        boolean found = false;
        for (String state : states) {
            int result = search(state.toLowerCase().toCharArray(), pattern.toLowerCase().toCharArray());
            if(result > 0) {
                System.out.println("- " + state);
                found = true;
            }
        }
        if (!found) {
            System.out.println("State not found.");
        }
    }

    // Total number of possible characters (ASCII set size)
    static int NO_OF_CHARS = 256;


    // Boyer-Moore search algorithm using bad character heuristic
    // Returns how many times the pattern appears in the text
    static int search(char txt[], char pat[])
    {
        int m = pat.length;
        int n = txt.length;
        int[] badchar = new int[NO_OF_CHARS];

        // Preprocess pattern to get last seen index of each character
        badCharHeuristic(pat, m, badchar);

        int s = 0; // s is shift of the pattern with respect to text
        int foundCount = 0;

        while (s <= (n - m)) {
            int j = m - 1;
            while (j >= 0 && pat[j] == txt[s + j])
                j--;
            if (j < 0) {
                foundCount++; // Match found
                s += (s + m < n) ? m - badchar[txt[s + m]] : 1;
            } else {
                // Calculate shift based on mismatch
                int shift = j - badchar[txt[s + j]];
                if (shift < 1) shift = 1;
                s += shift;
            }
        }
        return foundCount;
    }

    static void badCharHeuristic(char[] str, int size,int badchar[])
    {
        for (int i = 0; i < NO_OF_CHARS; i++)
            badchar[i] = -1;
        for (int i = 0; i < size; i++)
            badchar[(int) str[i]] = i;
    }

    public static void main(String[] args) {
        System.out.println("---------Menu---------");
        System.out.println("1. Display the text");
        System.out.println("2. Search");
        System.out.println("3. Exit program");
        System.out.println("----------------------");

        Scanner scanner = new Scanner(System.in);
        int choice = 0;


        while(choice != 3){
            System.out.println("Please enter your choice:");
            choice = scanner.nextInt();
            if (choice < 1 || choice > 3) {
                System.out.println("Invalid choice. Please try again.");
                System.out.println("\n");
                continue;
            }
            switch (choice) {
                case 1:
                    // Display the states
                    displayStates();
                    System.out.println("\n");
                    break;
                case 2:
                   // Search
                    System.out.println("Type a state name or keyword to search:");
                    scanner.nextLine();
                    String pattern = scanner.nextLine();
                    System.out.println("\nStates that contain \"" + pattern + "\":");
                    searcghState(pattern);
                    System.out.println("\n");
                    break;
                case 3:
                    // Exit program
                    System.out.println("Exiting the program...");
                    scanner.close();
                    break;
                default:
                    break;
            }
        }
    }
}