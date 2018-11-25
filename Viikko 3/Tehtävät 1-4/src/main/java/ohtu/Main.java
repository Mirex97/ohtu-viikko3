package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";
        String courseUrl = "https://studies.cs.helsinki.fi/courses/courseinfo";
        String bodyText = Request.Get(url).execute().returnContent().asString();
        String courseText = Request.Get(courseUrl).execute().returnContent().asString();
        System.out.println(courseText);

        String nextURL = "https://studies.cs.helsinki.fi/courses/ohtu2018/stats";
        String statsResponse = Request.Get(nextURL).execute().returnContent().asString();
        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(statsResponse).getAsJsonObject();
//        System.out.println(parsittuData);

//        System.out.println("json-muotoinen data:");
//        System.out.println( bodyText );
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        Course[] courses = mapper.fromJson(courseText, Course[].class);
        HashMap<Course, ArrayList<Submission>> sortedSubs = new HashMap<>();
        HashMap<String, Course> names = new HashMap<>();
        for (Course course : courses) {
            if (sortedSubs.get(course) == null) {
                sortedSubs.put(course, new ArrayList<>());
                names.put(course.getName(), course);
            }
        }

        for (Submission submission : subs) {
            Course kurssi = names.get(submission.getCourse());
            sortedSubs.get(kurssi).add(submission);
        }
        System.out.println("opiskelijanumero " + studentNr);
        for (Course name : names.values()) {
            ArrayList<Submission> subas = sortedSubs.get(name);
            if (subas.isEmpty()) {
                continue;
            }
            System.out.println("\n" + name.getFullName() + " " + name.getTerm() + " " + name.getYear() + "\n");
            int aika = 0;
            int tehtavatYht = 0;

            for (Submission submission : subas) {
                aika += submission.getHours();
                tehtavatYht += submission.getExercises().length;
                submission.setMax(name.getExercise(submission.getWeek()));
                System.out.println(submission);
            }
            int ex = name.getExercises();
            System.out.println("\nyhteensä: " + tehtavatYht + "/" + ex + " tehtävää " + aika + " tuntia");
        }

//        System.out.println("Oliot:");
    }
}
