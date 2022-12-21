package it.diegorigo.aws.helpers;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClientBuilder;

public class RdsHelper {

    public static AmazonRDS createRDSClient(BasicAWSCredentials credentials) {
        return AmazonRDSClientBuilder.standard()
                                     .withCredentials(new AWSStaticCredentialsProvider(
                                             credentials))
                                     .withRegion(Regions.EU_NORTH_1)
                                     .build();
    }
}
