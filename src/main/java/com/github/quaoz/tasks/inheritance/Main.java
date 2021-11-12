package com.github.quaoz.tasks.inheritance;

import com.github.quaoz.common.arrayutils.Shuffle;
import com.github.quaoz.common.searches.LinearSearch;
import com.github.quaoz.common.sorts.IntroSort;

class Main {
	public static void main(String[] args) {
		System.out.println("Megacorp Payroll");
		Person user1 = new Person("Alice", 45);
		user1.display();

		Employee user2 = new Employee("Bob", 22, 8.36);
		SalesPerson user3 = new SalesPerson("Charlie", 29, 10.5);
		user2.display();
		user3.display();

		System.out.println("Pay packet:" + user2.takeHomePay(6));
		user3.addCommission(100);

		System.out.println("Pay packet:" + user3.takeHomePay(6));
		System.out.println("Pay packet:" + user3.takeHomePay(50));

		System.out.println("\n");

		Employee[] employees = new Employee[7];
		employees[0] = new Employee("Anton", 52, 9.75);
		employees[1] = new SalesPerson("Bjorn", 19, 8.55);
		employees[2] = new SalesPerson("Celeste", 31, 12.35);
		employees[3] = new Employee("Darina", 44, 10.52);
		employees[4] = new Employee("Edgar", 43, 10.52);
		employees[5] = new SalesPerson("Fope", 27, 9.25);
		employees[6] = new Employee("Giselle", 30, 9.75);

		Shuffle.shuffle(employees);

		for (Employee employee : employees) {
			employee.display();
		}

		IntroSort.sort(employees);
		System.out.println();

		for (Employee employee : employees) {
			employee.display();
		}

		System.out.println(LinearSearch.findSorted(employees, new Employee("Anton", 52, 9.75)));
	}
}
