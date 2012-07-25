package skrull.rmi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * class to locate our most "secure" policy file
 *
 */
public class PolicyFileLocater {

    public static final String POLICY_FILE_NAME = "allow_all.policy";

    public static String getLocationOfPolicyFile() {
        try {
            File tempFile = File.createTempFile("rmi-base", ".policy");
            String policyFile = System.getProperty("policy.file");
            System.getProperties().list(System.out);
            InputStream is = PolicyFileLocater.class.getResourceAsStream(policyFile);
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            int read = 0;
            while((read = is.read()) != -1) {
                writer.write(read);
            }
            writer.close();
            tempFile.deleteOnExit();
            return tempFile.getAbsolutePath();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
    