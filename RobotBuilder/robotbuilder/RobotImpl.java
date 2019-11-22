package robotbuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class RobotImpl implements Robot {

    private String name;
    private String type;
    private int time;
    private List<String> tasks = new ArrayList<String>();
    private List<Integer> taskTimes = new ArrayList<Integer>();

    public RobotImpl(String _type, String _name) {
        this.type = _type;
        this.name = _name;
        this.time = 0;
        this.tasks.add("do the dishes");
        this.tasks.add("sweep the house");
        this.tasks.add("do the laundry");
        this.tasks.add("take out the recycling");
        this.tasks.add("make a sammich");
        this.tasks.add("mow the lawn");
        this.tasks.add("rake the leaves");
        this.tasks.add("give the dog a bath");
        this.tasks.add("bake some cookies");
        this.tasks.add("wash the car");
        this.taskTimes.add(1000);
        this.taskTimes.add(3000);
        this.taskTimes.add(10000);
        this.taskTimes.add(4000);
        this.taskTimes.add(7000);
        this.taskTimes.add(20000);
        this.taskTimes.add(18000);
        this.taskTimes.add(14500);
        this.taskTimes.add(8000);
        this.taskTimes.add(20000);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getTime() {
        return time;
    }

    // Robot comparing property if needed for future projects
    @Override
    public boolean equals(Robot otherRobot) {
        if (otherRobot == null) {
            throw new NoSuchElementException("Need to specify other Robot");
        }
        if (this.getType() == otherRobot.getType()) {
            return true;
        }
        return false;
    }

    // Gives Robot tasks and let's user know when task is complete
    @Override
    public void doTask(String task) throws InterruptedException {
        for (int i = 0; i < tasks.size(); i++) {
            if (task.equals(tasks.get(i))) {
                time += taskTimes.get(i);
                System.out.println("Current Task: " + task);
                TimeUnit.MILLISECONDS.sleep((long)taskTimes.get(i));
                System.out.println("Task completed");
                tasks.remove(i);
                taskTimes.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Need to pick task from above only ONE time");
    }
}
