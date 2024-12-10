package DependencyCheck;

// Class made just to test all the functionalities of JUnit
@SuppressWarnings("all")
public class Calculator {
    public int calcualteSum(int a, int b){
        return a + b;
    }

    public int calculateDifference(int a, int b){
        if(a > b){
            return a - b;
        } else {
            return b - a;
        }
    }
}
