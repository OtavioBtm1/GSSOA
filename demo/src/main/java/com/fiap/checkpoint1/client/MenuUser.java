package com.fiap.checkpoint1.client;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MenuUser {

    private static final String BASE_URL = "http://localhost:8080/usuarios";

    private static String jwtToken = null; // 🔥 Armazena token

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- MENU SISTEMA DE USUÁRIOS ---");
            System.out.println("1. Cadastrar novo usuário");
            System.out.println("2. Fazer login");
            System.out.println("3. Modo administrador (requer login)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrar(scanner);
                case 2 -> login(scanner);
                case 3 -> {
                    if (jwtToken == null) {
                        System.out.println("⚠️ Faça login primeiro!");
                    } else {
                        menuAdministrador(scanner);
                    }
                }
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void cadastrar(Scanner sc) throws IOException {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        String json = String.format("{\"nome\":\"%s\", \"email\":\"%s\", \"senha\":\"%s\"}", nome, email, senha);
        sendRequest("POST", BASE_URL + "/cadastro", json);
    }

    private static void login(Scanner sc) throws IOException {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        String json = String.format("{\"email\":\"%s\", \"senha\":\"%s\"}", email, senha);

        String response = sendRequest("POST", BASE_URL + "/login", json);

        if (response != null && !response.isBlank()) {
            jwtToken = response.replace("\"", "");
            System.out.println("🔐 Token salvo com sucesso!");
        }
    }

    private static void menuAdministrador(Scanner scanner) throws IOException {
        int opcaoAdmin;

        do {
            System.out.println("\n--- ADMIN ---");
            System.out.println("1. Listar usuários");
            System.out.println("2. Buscar usuário por ID");
            System.out.println("3. Deletar usuário");
            System.out.println("0. Voltar");
            opcaoAdmin = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoAdmin) {
                case 1 -> sendRequest("GET", BASE_URL, null);
                case 2 -> {
                    System.out.print("ID: ");
                    long id = scanner.nextLong();
                    sendRequest("GET", BASE_URL + "/" + id, null);
                }
                case 3 -> {
                    System.out.print("ID: ");
                    long id = scanner.nextLong();
                    sendRequest("DELETE", BASE_URL + "/" + id, null);
                }
            }

        } while (opcaoAdmin != 0);
    }

    private static String sendRequest(String method, String urlString, String body) throws IOException {

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");

        // 🔥 Se tiver token, manda Bearer
        if (jwtToken != null) {
            conn.setRequestProperty("Authorization", "Bearer " + jwtToken);
        }

        if (body != null) {
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes());
            }
        }

        int status = conn.getResponseCode();
        System.out.println("\n▶ HTTP " + status);

        InputStream stream = status < 400 ? conn.getInputStream() : conn.getErrorStream();
        StringBuilder response = new StringBuilder();

        if (stream != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line).append("\n");
                }
            }
        }

        conn.disconnect();
        System.out.println(response);

        return response.toString();
    }
}
