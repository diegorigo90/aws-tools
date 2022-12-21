package it.diegorigo.aws;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.model.DBInstance;
import it.diegorigo.aws.dto.RDSInstanceInfoDto;
import it.diegorigo.aws.helpers.AwsHelper;
import it.diegorigo.aws.helpers.RdsHelper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AwsRdsInstanceStatus {

    public static void main(String[] args) throws IOException {
        BasicAWSCredentials credentials = AwsHelper.getCredentials();
        AmazonRDS client = RdsHelper.createRDSClient(credentials);
        List<RDSInstanceInfoDto> list = client.describeDBInstances()
                                              .getDBInstances()
                                              .stream()
                                              .map(AwsRdsInstanceStatus::generateRdsInfo)
                                              .collect(Collectors.toList());

        printStates(list);
    }

    private static RDSInstanceInfoDto generateRdsInfo(DBInstance item) {
        RDSInstanceInfoDto dto = new RDSInstanceInfoDto();
        dto.setName(item.getDBInstanceIdentifier());
        dto.setStatus(item.getDBInstanceStatus().toUpperCase());
        return dto;
    }

    public static void printStates(List<RDSInstanceInfoDto> list) {
        list.forEach(instance -> System.out.printf("%-40s  %30s%n",
                                                   instance.getName(),
                                                   instance.getStatus()));
    }
}
