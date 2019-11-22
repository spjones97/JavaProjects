package robotbuilder;

import java.util.*;

public class RobotBuilder {

    public static void main(String[] args) throws InterruptedException {

        boolean isPlaying = true;
        List<Robot> robots = new ArrayList<Robot>();
        List<String> types = Arrays.asList("UNIPEDAL", "BIPEDAL", "QUADRUPEDAL", "ARACHNID", "RADIAL", "AERONAUTICAL");

        Scanner scan = new Scanner(System.in);

        while (isPlaying) {

            // Create list of tasks
            List<String> tasks = new ArrayList<String>();
            tasks.add("do the dishes");
            tasks.add("sweep the house");
            tasks.add("do the laundry");
            tasks.add("take out the recycling");
            tasks.add("make a sammich");
            tasks.add("mow the lawn");
            tasks.add("rake the leaves");
            tasks.add("give the dog a bath");
            tasks.add("bake some cookies");
            tasks.add("wash the car");

            System.out.println("");
            System.out.println("What type of Robot would you like to choose?");
            System.out.println("UNIPEDAL, BIPEDAL, QUADRUPEDAL, ARACHNID, RADIAL, OR AERONAUTICAL");

            // Initialize type of robot variable
            String type = "";

            // Check to see if type of robot is usable
            boolean contains = false;
            while (!contains) {
                // Collect the type of robot
                type = scan.nextLine();
                for (int i = 0; i < types.size(); i++) {
                    if (type.equals(types.get(i))) {
                        contains = true;
                    }
                }
                if (!contains) {
                    System.out.println("Need to pick a type from the list above (CASE SENSITIVE)");
                }
            }

            System.out.println("What would you like your robot's name to be?");

            // Initialize user name variable
            String name = "";

            // Check to see if length of the name is greater than 0
            boolean nameLength = false;
            while (!nameLength) {
                // Collect robot name
                name = scan.nextLine();
                if (name.length() > 0) {
                    nameLength = true;
                } else {
                    System.out.println("Name length needs to be greater than one");
                }
            }

            // Create robot based on the type and name
            Robot robot = new RobotImpl(type, name);

            // Request user for 5 tasks
            System.out.println("Now choose 5 of these tasks for " + name + " to complete:");

            // Prints out lists of usable tasks
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(tasks.get(i));
            }
            System.out.println("");

            // Fulfills user's task request
            for (int i = 0; i < 5; i++) {
                String task = "";
                boolean taskExists = false;
                while (!taskExists) {
                    task = scan.nextLine();
                    for (int j = 0; j < tasks.size(); j++) {
                        if (task.equals(tasks.get(j))) {
                            taskExists = true;
                            robot.doTask(task);
                            tasks.remove(tasks.get(j));
                            System.out.println("");
                            break;
                        }
                    }
                    if (!taskExists) {
                        System.out.println("Task does not exist or has already been completed. (case and space sensitive)");
                    }
                }
            }

            // Print out the length of time it took for the robot to complete the tasks
            System.out.println(robot.getName() + " completed these tasks in " + robot.getTime() + " milliseconds!");
            System.out.println("");

            // Ask user whether or not they would like to continue playing
            System.out.println("Would you like to build another robot? yes/no");
            String answer = scan.nextLine();

            // If user inputs 'no' the game stops
            if (answer.equals("no")) {
                isPlaying = false;
            }
            robots.add(robot);
        }

        // Order the leaderboard from smallest time to largest
        robots.sort(Comparator.comparing(Robot::getTime));

        // Print out leaderboard
        System.out.println("");
        System.out.println("Leaderboard:");
        int rank = 1;
        for (int i = 0; i < robots.size(); i++, rank += 1) {
            System.out.println(rank + ". " + robots.get(i).getName() + " completed their tasks in "
                    + robots.get(i).getTime() + " milliseconds");
        }

        scan.close();
    }
}

