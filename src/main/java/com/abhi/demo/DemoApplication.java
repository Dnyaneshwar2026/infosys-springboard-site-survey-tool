package com.abhi.demo;

import com.abhi.demo.model.User;
import com.abhi.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		showMenu(scanner);
	}

	private void showMenu(Scanner scanner) {
		while (true) {
			System.out.println("\n🎯 Site Survey Tool - User Management");
			System.out.println("1. 📱 Add New User");
			System.out.println("2. 👥 View All Users");
			System.out.println("3. 🔍 Find User by Email");
			System.out.println("4. 📊 User Count");
			System.out.println("5. ❌ Exit");
			System.out.print("Choose option (1-5): ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Clear buffer

			switch (choice) {
				case 1 -> addUser(scanner);
				case 2 -> viewAllUsers();
				case 3 -> findUserByEmail(scanner);
				case 4 -> showUserCount();
				case 5 -> {
					System.out.println("👋 Goodbye!");
					scanner.close();
					return;
				}
				default -> System.out.println("❌ Invalid option!");
			}
		}
	}

	private void addUser(Scanner scanner) {
		System.out.print("Enter phone number: ");
		String phone = scanner.nextLine();

		System.out.print("Enter email: ");
		String email = scanner.nextLine();

		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		try {
			User user = new User(phone, email, password);
			userRepository.save(user);
			System.out.println("✅ User created with ID: " + user.getId());
		} catch (Exception e) {
			System.out.println("❌ Error: " + e.getMessage());
		}
	}

	private void viewAllUsers() {
		var users = userRepository.findAll();
		if (users.isEmpty()) {
			System.out.println("📭 No users found!");
		} else {
			System.out.println("\n👥 All Users:");
			users.forEach(user ->
					System.out.println("ID: " + user.getId() +
							" | Phone: " + user.getPhoneNumber() +
							" | Email: " + user.getEmail())
			);
		}
	}

	private void findUserByEmail(Scanner scanner) {
		System.out.print("Enter email to search: ");
		String email = scanner.nextLine();
		userRepository.findByEmail(email).ifPresentOrElse(
				user -> System.out.println("✅ Found: " + user.getPhoneNumber()),
				() -> System.out.println("❌ User not found!")
		);
	}

	private void showUserCount() {
		long count = userRepository.count();
		System.out.println("📊 Total users: " + count);
	}
}
