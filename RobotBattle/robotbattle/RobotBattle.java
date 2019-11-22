package robotbattle;

import java.util.*;
import java.util.List;

public class RobotBattle {

    public static void main(String[] args) throws InterruptedException {

        boolean isPlaying = true;
        List<String> types = Arrays.asList("UNIPEDAL", "BIPEDAL", "QUADRUPEDAL", "ARACHNID", "RADIAL", "AERONAUTICAL");

        Scanner scan = new Scanner(System.in);

        while (isPlaying) {

            // Add user tasks
            List<String> userTasks = new ArrayList<String>();
            userTasks.add("do the dishes");
            userTasks.add("sweep the house");
            userTasks.add("do the laundry");
            userTasks.add("take out the recycling");
            userTasks.add("make a sammich");
            userTasks.add("mow the lawn");
            userTasks.add("rake the leaves");
            userTasks.add("give the dog a bath");
            userTasks.add("bake some cookies");
            userTasks.add("wash the car");

            // Add opponent tasks
            List<String> opponentTasks = new ArrayList<String>();
            opponentTasks.add("do the dishes");
            opponentTasks.add("sweep the house");
            opponentTasks.add("do the laundry");
            opponentTasks.add("take out the recycling");
            opponentTasks.add("make a sammich");
            opponentTasks.add("mow the lawn");
            opponentTasks.add("rake the leaves");
            opponentTasks.add("give the dog a bath");
            opponentTasks.add("bake some cookies");
            opponentTasks.add("wash the car");

            System.out.println("");
            System.out.println("WHAT TYPE OF ROBOT WILL YOU USE IN THIS ROUND OF BOTOMAT BATTLES?");
            System.out.println("UNIPEDAL, BIPEDAL, QUADRUPEDAL, ARACHNID, RADIAL, OR AERONAUTICAL");

            // Initialize type of robot variable
            String userType = "";

            // Check to see if type of robot is usable
            boolean contains = false;
            while (!contains) {
                // Collect the type of robot
                userType = scan.nextLine();
                for (int i = 0; i < types.size(); i++) {
                    if (userType.equals(types.get(i))) {
                        contains = true;
                    }
                }
                if (!contains) {
                    System.out.println("Need to pick a type from the list above (CASE SENSITIVE)");
                }
            }

            System.out.println("WHAT WILL YOU CALL YOUR " + userType + " ROBOT?");

            // Initialize user name variable
            String userName = "";

            // Check to see if length of the name is greater than 0
            boolean nameLength = false;
            while (!nameLength) {
                // Collect robot name
                userName = scan.nextLine();
                if (userName.length() > 0) {
                    nameLength = true;
                } else {
                    System.out.println("Name length needs to be greater than one");
                }
            }

            // Create robot based on the users type and name
            RobotImpl userRobot = new RobotImpl(userType, userName);
            System.out.println("");

            // Create opponent robot
            int index = ((int)(Math.random() * 6.0));
            String opponentType = types.get(index);
            String opponentName = "HAL 9000";
            Robot opponentRobot = new RobotImpl(opponentType, opponentName);

            System.out.println("YOUR ROBOT " + userName + " WILL BE GOING UP AGAINST " + opponentName + " THE " + opponentType + " ROBOT");

            // Request user for 5 tasks
            System.out.println("");
            System.out.println("NOW CHOOSE 5 OF THESE TASKS FOR " + userName + " TO COMPLETE");
            for (int i = 0; i < userTasks.size(); i++) {
                System.out.println(userTasks.get(i));
            }
            System.out.println("");

            // Fulfills users task request if exists
            for (int i = 0; i < 5; i++) {
                String task = "";
                boolean taskExists = false;
                while (!taskExists) {
                    task = scan.nextLine();
                    for (int j = 0; j < userTasks.size(); j++) {
                        if (task.equals(userTasks.get(j))) {
                            taskExists = true;
                            userRobot.doTask(task);
                            userTasks.remove(userTasks.get(j));
                            System.out.println("");
                            break;
                        }
                    }
                    if (!taskExists) {
                        System.out.println("Task does not exist or has already been completed. (case and space sensitive)");
                    }
                }
            }

            // Give opponent robot 5 random tasks
            System.out.println("YOUR OPPONENT ROBOT WILL NOW CHOOSE THEIR 5 TASKS");
            for (int i = 0; i < 5; i++) {
                int tempIndex = ((int)(Math.random() * (9.0 - i)));
                opponentRobot.doTask(opponentTasks.get(tempIndex));
                opponentTasks.remove(tempIndex);
                System.out.println("");
            }

            // Collect results and compare
            System.out.println(userName + " COMPLETED THEIR TASKS IN: " + userRobot.getTime() + "ms");
            System.out.println(opponentName + " COMPLETED THEIR TASKS IN " + opponentRobot.getTime() + "ms");
            if (userRobot.getTime() < opponentRobot.getTime()) {
                System.out.println("YOUR ROBOT WAS THE SUPERIOR ROBOT!");
                System.out.println("GREAT TRAINING!");
                System.out.println("WOULD YOU LIKE TO PLAY AGAIN? yes/no");
                String answer = scan.nextLine();
                if (answer.equals("no")) {
                    isPlaying = false;
                }
            } else {
                System.out.println("LOOKS LIKE YOU NEED TO GET BACK TO THE SHOP!");
                System.out.println("WOULD YOU LIKE TO TRY AGAIN? yes/no");
                String answer = scan.nextLine();
                if (answer.equals("no")) {
                    isPlaying = false;
                }
            }
        }

    }
}