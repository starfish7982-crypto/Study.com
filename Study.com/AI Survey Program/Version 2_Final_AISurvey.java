import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Main {

    public static void appendToCSV(String fileName, String data) {
        try {
            Path path = Paths.get(fileName);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            Files.write(path, data.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {

        DataSource dataSource = new DataSource("survey.arff");
        Instances data = dataSource.getDataSet();
        data.setClassIndex(data.numAttributes() - 1);

        Classifier classifier = new RandomForest();
        classifier.buildClassifier(data);

        String democratCsvFileName = "./csv/democrat.csv";
        String republicanCsvFileName = "./csv/republican.csv";
        String libertarianCsvFileName = "./csv/libertarian.csv";
        String greenCsvFileName = "./csv/green.csv";

        StringBuilder userAnswers = new StringBuilder();

        ArrayList<Attribute> attributes = new ArrayList<>();
        for (int i = 0; i < data.numAttributes() - 1; i++) {
            attributes.add(data.attribute(i));
        }

        ArrayList<String> politicalPartyValues = new ArrayList<>();
        Enumeration<Object> classValues = data.attribute(data.classIndex()).enumerateValues();
        while (classValues.hasMoreElements()) {
            politicalPartyValues.add((String) classValues.nextElement());
        }
        Attribute classAttribute = new Attribute("party", politicalPartyValues);
        attributes.add(classAttribute);

        Instances userInputInstances = new Instances("UserInput", attributes, 1);
        userInputInstances.setClassIndex(userInputInstances.numAttributes() - 1);
        Instance userInput = new DenseInstance(userInputInstances.numAttributes());
        userInput.setDataset(userInputInstances);

        String[] questions = {
                "Do you support LGBTQ+ rights, including same-sex marriage?", // Democratic
                "Do you believe the government should take strong action to combat climate change?",
                "Do you support stricter gun control laws?",
                "Should abortion be legal in most or all cases?",
                "Should immigration laws be more strictly enforced?", // Republican
                "Should the U.S. increase military spending?",
                "Should government-run welfare programs be cut or limited?",
                "Do you support building a wall along the southern border?",
                "Should the U.S. significantly reduce its military budget and focus on peaceful foreign relations?", // Green
                "Should the U.S. legalize marijuana and expunge criminal records for those convicted of marijuana-related offenses?",
                "Should the minimum wage be raised to a living wage, ensuring that all workers can meet their basic needs?",
                "Should the U.S. adopt policies that promote sustainable agriculture and reduce factory farming?",
                "Should the government reduce taxes and limit its spending to protect individual rights and freedoms?", // Libertarian
                "Should the U.S. end foreign military interventions and focus on protecting its own borders?",
                "Should the U.S. government eliminate the Federal Reserve and allow the free market to control currency?",
                "Should individuals have the right to keep and use their property as they see fit, without government interference?",
        };
        String[] answerKey = {
                "[\"yes\", \"no\"]",
                "[\"yes\", \"no\"]",
                "[\"yes\", \"no\"]",
                "[\"yes\", \"no\"]",
                "[\"yes\", \"no\"]",
                "[\"yes\", \"no\"]",
                "[\"yes\", \"no\"]",
                "[\"yes\", \"no\"]",
                "[\"yes\", \"no\"]",
                "[\"yes\", \"no\"]",
                "[\"yes\", \"no\"]",
                "[\"yes\", \"no\"]",
                "[\"yes\", \"no\"]",
                "[\"yes\", \"no\"]",
                "[\"yes\", \"no\"]",
                "[\"yes\", \"no\"]",
                "[\"Republican\", \"Democrat\", \"Green\", \"Libertarian\"]"
        };

        System.out.println("Party Prediction");

        Scanner scanner = new Scanner(System.in);
        String predictedParty = null;
        double[] probabilities = new double[0];
        int prediction = 0;

        for (int i = 0; i < questions.length; i++) {
            System.out.println("\n Question " + (i + 1) + " of " + questions.length);
            System.out.println("\n " + questions[i]);
            System.out.println("1. yes");
            System.out.println("2. no");
            System.out.print("[Answer] (type 1 or 2): ");

            String answer = "";
            boolean isValidInput = false;
            while (!isValidInput) {
                String choice = scanner.next().trim();
                if (choice.equals("1")) {
                    answer = "yes";
                    isValidInput = true;
                } else if (choice.equals("2")) {
                    answer = "no";
                    isValidInput = true;
                } else {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                    System.out.print("[Answer]: ");
                }
            }

            userAnswers.append(answer).append(", ");
            userInput.setValue(attributes.get(i), answer);

            // Predict the political party
            probabilities = classifier.distributionForInstance(userInput);
            prediction = (int) classifier.classifyInstance(userInput);
            predictedParty = classAttribute.value(prediction);

            System.out.println("Predicted Party: " + predictedParty);
            System.out.printf("Prediction Accuracy: %.2f%%\n", probabilities[prediction] * 100);

            System.out.println("=== Prediction Probabilities ===");
            for (int j = 0; j < probabilities.length; j++) {
                System.out.printf("%s: %.2f%%\n", classAttribute.value(j), probabilities[j] * 100);
            }
        }

        System.out.println("\n Final Question");
        System.out.println("Which political party do you most identify with?");
        System.out.println("1. Democrat");
        System.out.println("2. Republican");
        System.out.println("3. Green");
        System.out.println("4. Libertarian");
        System.out.print("[Answer] (type 1-4): ");

        Scanner partyScanner = new Scanner(System.in);
        String userParty = "";

        boolean isValidInput = false;
        while (!isValidInput) {
            String choice = partyScanner.next().trim();
            switch (choice) {
                case "1":
                    userParty = "democrat";
                    isValidInput = true;
                    break;
                case "2":
                    userParty = "republican";
                    isValidInput = true;
                    break;
                case "3":
                    userParty = "green";
                    isValidInput = true;
                    break;
                case "4":
                    userParty = "libertarian";
                    isValidInput = true;
                    break;
                default:
                    System.out.println("Invalid input. Please enter 1 to 4.");
                    System.out.print("[Answer]: ");
            }

            // Append the user's political party and a newline character to complete the CSV
            userAnswers.append(userParty).append("\n");

            switch (userParty) {
                case "democrat":
                    appendToCSV(democratCsvFileName, userAnswers.toString());
                    break;
                case "republican":
                    appendToCSV(republicanCsvFileName, userAnswers.toString());
                    break;
                case "libertarian":
                    appendToCSV(libertarianCsvFileName, userAnswers.toString());
                    break;
                case "green":
                    appendToCSV(greenCsvFileName, userAnswers.toString());
                    break;
                default: {
                }
            }

            // End of Survey Message
            if (userParty.equals(predictedParty.toLowerCase())) {
                System.out.println("\n End of Survey!\n Thank you for taking the survey!\n");
            } else {
                System.out.println("Prediction and your choice don't match.");
            }
            scanner.close();

            if (probabilities[prediction] < 0.8) {
                System.out.printf("Prediction is uncertain: %s (%.2f%%)\n", predictedParty,
                        probabilities[prediction] * 100);
            }

        }
    }
}