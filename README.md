
# HeartCare Backend

Backend desenvolvido em Java utilizando Spring Framework para suportar aplicações dedicadas aos cuidados cardíacos. O projeto é estruturado em controladores RESTful, DTOs para transferência de dados, entidades JPA para mapeamento de banco de dados, além de camadas de infraestrutura, repositório, serviço e utilitários.

## Estrutura do Projeto

- **HeartCareApplication.java**: Ponto de entrada da aplicação Spring Boot.
  
### Controllers

- **AuthController.java**: Responsável pela autenticação e autorização de usuários.
- **ForgotPasswordController.java**: Lida com processos de recuperação de senha.
- **PostController.java**: Gerencia operações relacionadas a publicações na plataforma.
- **PressureController.java**: Controla as medições de pressão arterial dos usuários.
- **UserController.java**: Gerencia operações relacionadas aos perfis de usuário.

### DTOs

- Utilizados para transferência de dados entre as camadas do sistema.

### Entity

- Entidades JPA que representam os objetos persistentes no banco de dados.

### Infraestrutura

- Configurações de inicialização e configuração da aplicação.

### Repository

- Interfaces que definem operações de acesso aos dados no banco.

### Service

- Lógica de negócios e serviços essenciais da aplicação.

### Utils

- Classes utilitárias para funcionalidades diversas.

## Tecnologias Utilizadas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

## Execução

1. Clone o repositório:
    ```bash
    git clone https://github.com/sntooosk/heartcare-backend.git
    ```

2. Importe o projeto em sua IDE e inicie a aplicação.

## Contribuição

1. Faça um fork do projeto.
2. Crie uma branch para sua feature:
    ```bash
    git checkout -b minha-feature
    ```
3. Faça commit das suas alterações:
    ```bash
    git commit -m 'Adiciona minha feature'
    ```
4. Envie para o branch principal:
    ```bash
    git push origin minha-feature
    ```
5. Abra um Pull Request para revisão.

## Licença

Este projeto está licenciado sob a Licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

## Contato

Juliano Cassimiro dos Santos - [LinkedIn](https://www.linkedin.com/in/sntooosk)

---

<div align="center">
  <strong>HeartCare</strong> - Cuidando do seu coração com tecnologia
</div>
