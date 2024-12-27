import java.io.*;

public class ID_File {
    private static final String ID_FILE = "SavedIDs.csv";

    // Saving all fields in CSV format
    public static void saveID(EgyptianID id) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ID_FILE, true))) {
            File file = new File(ID_FILE);

            if (file.length() == 0) {
                writer.write("Name,Gender,Religion,Governorate,Birthdate,IssueDate,Occupation,UniqueID,PicturePath");
                writer.newLine();
            }

            // Writing all the ID data including the picture path
            writer.write(String.join(",",
                    id.getName(),
                    id.getGender(),
                    id.getReligion(),
                    id.getGovernorate(),
                    id.getBirthdate(),
                    id.getIssueDate(),
                    id.getOccupation(),
                    id.getUniqueID(),
                    id.getPicturePath() 
            ));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to " + ID_FILE);
        }
    }


    // Check if a unique ID exists in the file to avoid duplicating 
    public static boolean checkForID(String uniqueID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ID_FILE))) {
            String line;
            reader.readLine(); // to skip the header row which contains the names of each column 
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 6 && fields[7].trim().equals(uniqueID)) {
                    return true; // if ID found
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading " + ID_FILE);
        }
        return false; // if ID not found
    } 
    
    
    
    // Retrieving information for a specific ID
    public static String getIDInfo(String uniqueID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ID_FILE))) {
            String line;
            reader.readLine(); // to skip the header row which contains the names of each column 
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 9 && fields[7].trim().equals(uniqueID)) { 
                    return "Name: " + fields[0].trim() + "\n" +
                           "Gender: " + fields[1].trim() + "\n" +
                           "Religion: " + fields[2].trim() + "\n" +
                           "Governorate: " + fields[3].trim() + "\n" +
                           "Birthdate: " + fields[4].trim() + "\n" +
                           "Occupation: " + fields[6].trim() + "\n" +
                           "Issue Date: " + fields[5].trim() + "\n" +
                           "Unique ID: " + fields[7].trim() + "\n" +
                           "Picture Path: " + fields[8].trim(); 
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from " + ID_FILE);
        }
        return "ID not found";
    }

}

