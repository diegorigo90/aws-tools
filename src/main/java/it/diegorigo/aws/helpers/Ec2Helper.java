package it.diegorigo.aws.helpers;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;

public class Ec2Helper {

    public static AmazonEC2 createEc2Client(BasicAWSCredentials credentials) {
        return AmazonEC2ClientBuilder.standard()
                                     .withCredentials(new AWSStaticCredentialsProvider(
                                             credentials))
                                     .withRegion(Regions.EU_NORTH_1)
                                     .build();
    }
}
