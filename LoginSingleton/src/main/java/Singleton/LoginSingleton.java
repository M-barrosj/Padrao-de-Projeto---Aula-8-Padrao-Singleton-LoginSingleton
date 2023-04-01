/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Singleton;

/**
 *
 * @author JCMB
 */
import java.util.Random;
import java.util.Scanner;

import java.util.Random;
import java.util.Scanner;

public class LoginSingleton {
    private static LoginSingleton instance;
    private boolean TesteAntiBot;
    private String CodigoAntiBot;

    private LoginSingleton() {
        // Construtor privado para garantir que a classe não possa ser instanciada externamente
        this.TesteAntiBot = false;
        this.CodigoAntiBot = gerarCodigoAntiBot();
    }

    public static synchronized LoginSingleton getInstance() {
        // Método estático que garante a criação de apenas uma instância da classe
        if (instance == null) {
            instance = new LoginSingleton();
        }
        return instance;
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao sistema de Login!");

        System.out.print("Nome de usuário: ");
        String username = scanner.nextLine();

        System.out.print("Senha: ");
        String password = scanner.nextLine();

        if (!TesteAntiBot) {
            System.out.println("Por favor, digite o código anti-bot abaixo:");
            System.out.println(CodigoAntiBot);

            String antiBotInput = scanner.nextLine();

            if (antiBotInput.equals(CodigoAntiBot)) {
                TesteAntiBot = true;
                System.out.println("Código anti-bot verificado!");
            } else {
                System.out.println("Código anti-bot incorreto. Tente novamente.");
                return;
            }
        }

        System.out.println("Login efetuado com sucesso! Bem-vindo, " + username + "!");
    }

    private String gerarCodigoAntiBot() {
        // Método privado que gera um código anti-bot aleatório
        Random random = new Random();
        String simbolos = "!@#$%^&*()_+-=[]{}|;':\",./<>?\\";
        String codigo = "";

        for (int i = 0; i < 6; i++) {
            if (random.nextBoolean()) {
                // Gera um número aleatório entre 0 e 9
                codigo += random.nextInt(10);
            } else {
                // Gera um símbolo aleatório
                codigo += simbolos.charAt(random.nextInt(simbolos.length()));
            }
        }

        return codigo;
    }
    
    public static void main(String[] args) {
        LoginSingleton loginSingleton = LoginSingleton.getInstance();
        loginSingleton.login();
    }
}


