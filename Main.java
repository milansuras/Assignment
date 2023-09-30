package Assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\milan\\Downloads\\Assignment_Timecard.xlsx - Sheet1.tsv";
        processFile(filePath);
    }

    private static void processFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;


            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // Check if the line has the expected number of elements
                if (data.length < 4) {
                    System.out.println("Incomplete line: " + line);
                    continue; // Skip to the next iteration if the line is incomplete
                }

                String name = data[0].trim();
                String position = data[1].trim();
                Date startTime = parseDate(data[2].trim());
                Date endTime = parseDate(data[3].trim());

                // Checking  for 7 consecutive days
                if (checkConsecutiveDays(startTime, endTime, 7)) {
                    System.out.println(name + " (" + position + "): Worked for 7 consecutive days");
                }

                //  Checking for less than 10 hours between shifts but greater than 1 hour
                if (checkTimeBetweenShifts(startTime, endTime, 10, 1)) {
                    System.out.println(name + " (" + position + "): Less than 10 hours between shifts but greater than 1 hour");
                }

                //  Checking for more than 14 hours in a single shift
                if ((endTime.getTime() - startTime.getTime()) / (60 * 60 * 1000) > 14) {
                    System.out.println(name + " (" + position + "): Worked for more than 14 hours in a single shift");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean checkConsecutiveDays(Date start, Date end, int consecutiveDays) {

        return false;
    }

    private static boolean checkTimeBetweenShifts(Date start, Date end, int maxHours, int minHours) {

        return false;
    }
}
