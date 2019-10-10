// This class provides a stubbed-out environment.
// You are expected to implement the methods.
// Accessing an undefined variable should throw an exception.

// TODO: Need to create the exception and call the put when an id is reconized with a value.
//Fumbling the future Xerox!!!

// Hint!
// Use the Java API to implement your Environment.
// Browse:
//   https://docs.oracle.com/javase/tutorial/tutorialLearningPaths.html
// Read about Collections.
// Focus on the Map interface and HashMap implementation.
// Also:
//   https://www.tutorialspoint.com/java/java_map_interface.htm
//   http://www.javatpoint.com/java-map
// and elsewhere.

import java.util.HashMap;

public class Environment {
    private HashMap<Integer, Integer> environment;

    public Environment() {
        environment = new HashMap<>();
    }

    public double put(String var, double val) {
        environment.put(var.hashCode(), (int) val);
        return val;
    }
    public double get(int pos, String var) throws EvalException {
        int key = var.hashCode();
        return environment.get(key);
    }

}
