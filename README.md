<h1 align="center">Backend Heartcare com Java Spring e JWT 🔐</h1>

[Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

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
POST /api/v1/auth/register
```

Endpoint para registro de um novo usuário.

#### Parâmetros de Requisição

- `name`: Nome de usuário
- `email`: Endereço de e-mail
- `password`: Senha do usuário

#### Exemplo de Requisição

```json
{
  "name": "creusa",
  "email": "creusasantos88@etec.sp.gov.br",
  "password": "password123"
}
```

### Atualização de Usuário

```json
{
  "name": "creusa",
  "lastName": "aparecida do rosario",
  "dob": "1967-06-02",
  "gender": "Male",
  "auth": {
    "id": 6
  }
}
```

### Registro de Medições

```json
{
  "diastolic": "20",
  "systolic": "30",
  "pulse": "20",
  "date": "11/09/2006",
  "user": {
    "id": 1
  }
}
```

### Consultar Medições por UserID

```
GET localhost:8080/api/v1/pressure?userId=1
```

#### Exemplo de Resposta

```json
[
  {
    "id": 1,
    "diastolica": "20",
    "sistolica": null,
    "pulse": "20",
    "date": "11/09/2006"
  },
  {
    "id": 2,
    "diastolica": "20",
    "sistolica": null,
    "pulse": "20",
    "date": "11/09/2006"
  },
  {
    "id": 3,
    "diastolica": "20",
    "sistolica": "30",
    "pulse": "20",
    "date": "11/09/2006"
  }
]
```

#### Resposta de Sucesso

Status: `201 Created`

### Autenticação de Usuário (Login)

```
POST /api/v1/auth/login
```

Endpoint para autenticação de usuário.

#### Parâmetros de Requisição

- `email`: Endereço de e-mail
- `password`: Senha

#### Exemplo de Requisição

```json
{
  "email": "example_user",
  "password": "password123"
}
```

#### Resposta de Sucesso

Status: `200 OK`

```json
{
  "token": "seu_token_jwt"
}
```

### Rotas Protegidas

Rotas protegidas exigem um token JWT válido no cabeçalho da requisição.

Exemplo de cabeçalho:

```
Authorization: Bearer seu_token_jwt
```

<h2 id="contribution">📫 Contribuição</h2>

1. `git clone https://github.com/seu-usuario/editor-texto.git`
2. `git checkout -b feature/NOME`
3. Siga padrões de commit.
4. Abra um Pull Request explicando o problema resolvido ou funcionalidade adicionada. Se existir, anexe uma captura de tela das modificações visuais e aguarde a revisão!

## <a name="license"></a>Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).