<h1 align="center" style="font-weight: bold;">Backend Java Spring com JWT UserAuth 🔐</h1>

[![java-spring](https://img.shields.io/badge/java-spring-brightgreen?style=for-the-badge&logo=spring)](https://spring.io/)
[![jwt](https://img.shields.io/badge/jwt-JSON%20Web%20Token-green?style=for-the-badge)](https://jwt.io/)

<p align="center">
 <a href="#tech-stack">Tecnologias Utilizadas</a> • 
 <a href="#features">Funcionalidades</a> • 
 <a href="#prerequisites">Pré-requisitos</a> • 
 <a href="#installation-and-execution">Instalação e Execução</a> •
 <a href="#endpoints">Endpoints</a> •
 <a href="#contribution">Contribuição</a> •
 <a href="#license">Licença</a>
</p>

## <a name="tech-stack"></a>Tecnologias Utilizadas

- Java Spring
- Spring Boot
- Spring Security
- JWT (JSON Web Token)

## <a name="features"></a>Funcionalidades

- Registro de usuário (Sign Up)
- Autenticação de usuário (Login)
- Proteção de rotas com JWT

## <a name="prerequisites"></a>Pré-requisitos

Antes de começar, certifique-se de ter os seguintes requisitos instalados:

- JDK 8 ou superior
- Maven
- IDE Java (como IntelliJ IDEA, Eclipse)

## <a name="installation-and-execution"></a>Instalação e Execução

1. Clone este repositório:

```bash
git clone https://github.com/seu-usuario/backend-java-spring-jwt.git
```

2. Importe o projeto em sua IDE Java.

3. Configure as propriedades do banco de dados no arquivo `application.properties`.

4. Execute a aplicação.

## <a name="endpoints"></a>Endpoints

### Registro de Usuário (Sign Up)

```
POST /api/auth/signup
```

Endpoint para registro de um novo usuário.

#### Parâmetros de Requisição

- `username`: Nome de usuário
- `email`: Endereço de e-mail
- `password`: Senha do usuário

#### Exemplo de Requisição

```json
{
  "username": "example_user",
  "email": "user@example.com",
  "password": "password123"
}
```

#### Resposta de Sucesso

Status: `201 Created`

### Autenticação de Usuário (Login)

```
POST /api/auth/signin
```

Endpoint para autenticação de usuário.

#### Parâmetros de Requisição

- `username`: Nome de usuário ou e-mail
- `password`: Senha do usuário

#### Exemplo de Requisição

```json
{
  "username": "example_user",
  "password": "password123"
}
```

#### Resposta de Sucesso

Status: `200 OK`

```json
{
  "accessToken": "seu_token_jwt"
}
```

### Rotas Protegidas

Rotas protegidas exigem um token JWT válido no cabeçalho da requisição.

Exemplo de cabeçalho:

```
Authorization: Bearer seu_token_jwt
```

<h2 id="contribute">📫 Contribuição</h2>

1. `git clone https://github.com/seu-usuario/editor-texto.git`
2. `git checkout -b feature/NOME`
3. Siga padrões de commit.
4. Abra um Pull Request explicando o problema resolvido ou funcionalidade adicionada. Se existir, anexe uma captura de tela das modificações visuais e aguarde a revisão!

## <a name="license"></a>Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).
