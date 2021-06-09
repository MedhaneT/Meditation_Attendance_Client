package miu.edu.Client;

import miu.edu.Client.service.AttendanceRecordServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@SpringBootApplication
public class ClientApplication {


	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ClientApplication.class, args);

		AttendanceRecordServiceClient client = context.getBean(AttendanceRecordServiceClient.class);

		String accessToken = client.login("admin@miu.edu","123123");


		Scanner scanner = new Scanner(System.in);
		String barcode;
		System.out.println("Enter BarCode(sample 000-777888) at Location(" + client.getLocationCode() + ")");
		while ((barcode = scanner.nextLine()) != null) {
			client.createRecord(barcode);
			System.out.println("you are Successfully Attended ");
			System.out.println("Enter BarCode(000-677766) at Location(" + client.getLocationCode() + ")");
		}
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
