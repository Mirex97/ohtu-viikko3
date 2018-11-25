
package ohtu;

public class Course {
    private String name;
    private String fullName;
    private String term;
    private String year;
    private int[] exercises;
    
    public int getExercise(int i) {
        return exercises[i];
    }

    public int getExercises() {
        int amount = 0;
        for (int i : exercises) {
            amount += i;
        }
        return amount;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

   

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    
}
