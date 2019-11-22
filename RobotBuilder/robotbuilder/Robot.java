package robotbuilder;

public interface Robot {

//    enum Type {UNIPEDAL, BIPEDAL, QUADRUPEDAL, ARACHNID, RADIAL, AERONAUTICAL};

    String getType();
    String getName();
    int getTime();
    boolean equals(Robot otherRobot);
    String toString();
    void doTask(String task) throws InterruptedException;

//    static String suitToString(Robot.Type type) {
//
//        switch (type) {
//            case UNIPEDAL:
//                return "Unipedal";
//            case BIPEDAL:
//                return "Bipedal";
//            case QUADRUPEDAL:
//                return "Quadrupedal";
//            case ARACHNID:
//                return "Arachnid";
//            case RADIAL:
//                return "Radial";
//            case AERONAUTICAL:
//                return "Aeronautical";
//        }
//        return null;
//    }
}

