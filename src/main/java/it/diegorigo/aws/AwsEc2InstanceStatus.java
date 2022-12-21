package it.diegorigo.aws;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Tag;
import it.diegorigo.aws.dto.Ec2InstanceInfoDto;
import it.diegorigo.aws.helpers.AwsHelper;
import it.diegorigo.aws.helpers.Ec2Helper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AwsEc2InstanceStatus {

    public static void main(String[] args) throws IOException {

        BasicAWSCredentials credentials = AwsHelper.getCredentials();
        AmazonEC2 client = Ec2Helper.createEc2Client(credentials);
        List<Ec2InstanceInfoDto> list = client.describeInstances()
                                              .getReservations()
                                              .stream()
                                              .map(AwsEc2InstanceStatus::generateEc2Info)
                                              .collect(Collectors.toList());

        printStates(list);
    }

    private static Ec2InstanceInfoDto generateEc2Info(Reservation item) {
        Instance instance = item.getInstances().get(0);

        Ec2InstanceInfoDto dto = new Ec2InstanceInfoDto();
        dto.setStatus(instance.getState().getName().toUpperCase());
        dto.setName(instance.getTags()
                            .stream()
                            .filter(tag -> tag.getKey()
                                              .equals("Name"))
                            .map(Tag::getValue)
                            .findFirst()
                            .orElse(""));

        return dto;
    }

    public static void printStates(List<Ec2InstanceInfoDto> list) {
        list.forEach(item -> System.out.printf("%-40s  %30s%n",
                                               item.getName(),
                                               item.getStatus()));
    }
}
