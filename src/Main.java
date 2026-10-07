void main() {

    Map<Integer, Pessoa> banco = new HashMap<>();
    Map<Integer, Pessoa> cache = new LinkedHashMap<>();

    banco.put(1, new Pessoa(1, "Ana Conda", 25));
    banco.put(2, new Pessoa(2, "Deide Costa", 30));
    banco.put(3, new Pessoa(3, "Thomas Leite", 22));
    banco.put(4, new Pessoa(4, "André Silva", 28));
    banco.put(5, new Pessoa(5, "Maria João", 35));

    Scanner scanner = new Scanner(System.in);
    IO.println("--- Sistema de Cache com Maps ---");

    while (true) {
        IO.println("\n--- MENU DO SISTEMA ---");
        IO.println("1. Cadastrar Nova Pessoa no Banco");
        IO.println("2. Buscar Pessoa (Usa Cache/Banco)");
        IO.println("3. Sair");
        IO.println("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 3) {
            IO.println("Encerrando o programa.");
            break;
        }

        if (opcao == 1) {

            System.out.print("Digite o ID da nova pessoa: ");
            int novoId = scanner.nextInt();
            scanner.nextLine();

            if (banco.containsKey(novoId)) {
                IO.println("[Erro] Já existe uma pessoa com o ID " + novoId + " no banco!");
                continue;
            }

            IO.println("Digite o Nome: ");
            String nome = scanner.nextLine();

            IO.println("Digite a Idade: ");
            int idade = scanner.nextInt();

            Pessoa novaPessoa = new Pessoa(novoId, nome, idade);
            banco.put(novoId, novaPessoa);
            IO.println("Pessoa adicionada com sucesso ao Banco de Dados!");

        } else if (opcao == 2) {

            IO.println("Digite o ID da pessoa que deseja buscar: ");
            int idBuscado = scanner.nextInt();

            if (cache.containsKey(idBuscado)) {
                IO.println("Pessoa encontrada no cache: [" + cache.get(idBuscado) + "]");
            } else if (banco.containsKey(idBuscado)) {
                Pessoa pessoaNoBanco = banco.get(idBuscado);

                if (cache.size() >= 10) {
                    int idMaisAntigo = cache.keySet().iterator().next();
                    cache.remove(idMaisAntigo);
                    IO.println("[Cache Cheio] Removendo ID mais antigo do cache: " + idMaisAntigo);
                }

                cache.put(idBuscado, pessoaNoBanco);
                IO.println("Pessoa buscada no banco e adicionada ao cache: [" + pessoaNoBanco + "]");
            } else {
                IO.println("Pessoa com ID " + idBuscado + " não encontrada em lugar nenhum.");
            }
        } else {
            IO.println("Opção inválida! Tente novamente.");
        }
    }
}

