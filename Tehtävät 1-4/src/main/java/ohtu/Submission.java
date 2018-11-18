package ohtu;

public class Submission {
    private int week;
    private int hours;
    private int[] exercises;
    private String course;
    private int max;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    
    

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    @Override
    public String toString() {
        String loppuosa = "";
        for (int i = 0; i < exercises.length; i++) {
            if (i == exercises.length-1) {
                loppuosa += exercises[i];
            } else {
                loppuosa += exercises[i] + ", ";
            }
            
        }
        String palautettava = "viikko " + week + ":\n" 
                + " tehtyjä tehtäviä yhteensä " 
                + exercises.length + "/"+ max+ " aikaa kului "
                + hours + " tehdyt tehtävät: ";
        
        
        return palautettava + loppuosa;
    }
    
}