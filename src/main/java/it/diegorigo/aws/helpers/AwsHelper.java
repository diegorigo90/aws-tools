package it.diegorigo.aws.helpers;

import com.amazonaws.auth.BasicAWSCredentials;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AwsHelper {
    public static BasicAWSCredentials getCredentials() throws IOException {

        ClassLoader loader = Thread.currentThread()
                                   .getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(
                "aws.properties");

        Properties prop = new Properties();
        prop.load(stream);
        String secretKey = prop.getProperty("aws.secret-key");
        String accessKey = prop.getProperty("aws.access-key");
        return new BasicAWSCredentials(accessKey, secretKey);
    }
}
