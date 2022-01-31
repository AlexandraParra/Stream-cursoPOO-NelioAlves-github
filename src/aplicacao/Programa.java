package aplicacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entidade.Funcionario;

public class Programa {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite o caminho do arquivo: ");
		String path = sc.nextLine();
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			List<Funcionario> list = new ArrayList<>();
			String line = br.readLine();
			while (line != null) {
				String[] emp = line.split(",");
				String nome = emp[0];
				String email = emp[1];
				double salario = Double.parseDouble(emp[2]);
				list.add(new Funcionario(nome, email, salario));
				line = br.readLine();
			}
			System.out.print("Digite o salário: ");
			double sal = sc.nextDouble();
			System.out.printf("Email das pessoas com salário maior a %.2f:\n", sal);
			Comparator<String> comp = (e1,e2) -> e1.toUpperCase().compareTo(e2.toUpperCase());
			List<String> emails = list.stream().filter(p -> p.getSalario() > sal).map(p -> p.getEmail()).sorted(comp).collect(Collectors.toList());
			emails.forEach(System.out::println);
			
			double sum = list.stream().filter(p -> p.getNome().toUpperCase().charAt(0) == 'M').map(p -> p.getSalario()).reduce(0.0, (x,y) -> x+y);
			System.out.println("Soma do salário das pessoas cujo nome começa com 'M': "+ String.format("%.2f", sum));
		}
		catch (IOException e) {
			System.out.println("Error: "+e.getMessage());
		}
		
		
		sc.close();

	}

}
