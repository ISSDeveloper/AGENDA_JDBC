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

		System.out.printf(Color.ANSI_AZUL_FUNDO + Color.ANSI_BRANCO + "       -AGENDA-       " + Color.ANSI_RESET + "%n");

		while (true) {

			byte opcao = menu();

			switch (opcao) {
			case 1 -> listar();
			case 2 -> adicionar();
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

	private static void adicionar() {

	}
	

	private static void sair() {
		
		System.out.println("Fechando...");
		System.exit(0);
	}

}
