package com.isaac.agenda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.isaac.agenda.business.ContatoBss;
import com.isaac.agenda.color.Color;
import com.isaac.agenda.domain.Contato;
import com.isaac.agenda.exception.BssException;

public class Agenda {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static ContatoBss contatoBss = new ContatoBss();

	public static void main(String[] args) throws IOException, BssException {

		System.out
				.printf(Color.ANSI_AZUL_FUNDO + Color.ANSI_BRANCO + "       -AGENDA-       " + Color.ANSI_RESET + "%n");

		while (true) {

			byte opcao = menu();

			switch (opcao) {
			case 1 -> listar();
			case 2 -> adicionar();
			case 3 -> alterar();
			case 4 -> remover();
			case 5 -> sair();
			}
		}
	}

	private static byte menu() throws IOException {

		System.out.println(Color.ANSI_VERDE_FUNDO + Color.ANSI_BRANCO + "         MENU         " + Color.ANSI_RESET);
		System.out.println("---------------------");
		System.out.println("1 - Lista de contatos");
		System.out.println("2 - Adicionar contato");
		System.out.println("3 - Alterar contato");
		System.out.println("4 - Remover contato");
		System.out.println("5 - Sair");
		System.out.println("---------------------");
		System.out.print(Color.ANSI_VERDE + "Digite uma opção: " + Color.ANSI_RESET);

		String op = br.readLine();

		if (!("1".equals(op) || "2".equals(op) || "3".equals(op) || "4".equals(op) || "5".equals(op))) {

			System.out.println(Color.ANSI_VERMELHO + "Opção invalida!" + Color.ANSI_RESET);
			System.out.println();

			return menu();
		}

		System.out.println();
		return Byte.valueOf(op);
	}

	private static void listar() throws BssException {

		System.out.println(Color.ANSI_VERDE_FUNDO + Color.ANSI_BRANCO + "         LISTA        " + Color.ANSI_RESET);
		System.out.println();

		List<Contato> lista = contatoBss.getList();

		System.out.printf("----------------------------------------------------------------------%n");
		System.out.printf("| %-8s | %-40s | %12s |%n", "CÓDIGO", "NOME", "TELEFONE");
		System.out.printf("----------------------------------------------------------------------%n");

		lista.forEach(
				c -> System.out.printf("| %-8s | %-40s | %12s |%n", c.getCodContato(), c.getNome(), c.getTelefone()));

		System.out.printf("----------------------------------------------------------------------%n");
		System.out.println();
	}

	private static void adicionar() throws IOException, NumberFormatException, BssException {

		System.out.println(Color.ANSI_VERDE_FUNDO + Color.ANSI_BRANCO + "       ADICIONAR      " + Color.ANSI_RESET);
		System.out.println();

		System.out.println(Color.ANSI_AMARELO + "Para cancelar operação digite: * " + Color.ANSI_RESET);

		String nome = getNome();

		if (nome == null)
			return;

		String telefone = getTelefone();

		if (telefone == null)
			return;

		Contato contato = contatoBss.create(new Contato(nome, Long.valueOf(telefone)));

		System.out.println();

		List<Contato> lista = contatoBss.getList();

		System.out.printf("----------------------------------------------------------------------%n");
		System.out.printf("| %-8s | %-40s | %12s |%n", "CÓDIGO", "NOME", "TELEFONE");
		System.out.printf("----------------------------------------------------------------------%n");

		for (Contato c : lista) {
			if (c.getCodContato() == contato.getCodContato()) {
				System.out.printf(Color.ANSI_VERDE + "| %-8s | %-40s | %12s |" + Color.ANSI_RESET + "%n",
						"+" + c.getCodContato(), c.getNome(), c.getTelefone());
			} else {
				System.out.printf("| %-8s | %-40s | %12s |%n", c.getCodContato(), c.getNome(), c.getTelefone());
			}
		}

		System.out.printf("----------------------------------------------------------------------%n");
		System.out.println();
	}

	public static String getNome() throws IOException {

		System.out.print("Nome: ");
		String nome = br.readLine();

		if ("*".equals(nome)) {

			System.out.println(Color.ANSI_AMARELO + "Operação cancelada!" + Color.ANSI_RESET + "\n");
			return null;
		}

		if (nome.length() > 40) {

			System.out.println(Color.ANSI_VERMELHO + "Limite de caracteres alcançado!" + Color.ANSI_RESET);
			return getNome();
		}

		return nome;
	}

	public static String getTelefone() throws IOException {

		System.out.print("Telefone: ");
		String telefone = br.readLine();

		if ("*".equals(telefone)) {

			System.out.println(Color.ANSI_AMARELO + "Operação cancelada!" + Color.ANSI_RESET + "\n");
			return null;
		}

		if (!isTelefone(telefone)) {

			System.out.println(Color.ANSI_VERMELHO + "Telefone invalido!" + Color.ANSI_RESET);
			return getTelefone();
		}

		return telefone;
	}

	private static boolean isTelefone(String telefone) {

		if (telefone.length() != 11)
			return false;

		try {
			Long.parseLong(telefone);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static void alterar() {

	}

	private static void remover() {

	}

	private static void sair() {

		System.out.println("Fechando...");
		System.exit(0);
	}

}
