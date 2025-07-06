
# 🐾 Pet Love

**Pet Love** é uma aplicação Java Spring Boot voltada à gestão de clínicas veterinárias. A plataforma permite o cadastro e gerenciamento de usuários, pets, espécies, raças, consultas e solicitações de adoção, com autenticação de acesso e separação por perfis.

---

## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
  - Spring Web
  - Spring Security
  - Spring Data JPA
- **Maven**
- **PostgreSQL**
- **DTO Pattern**
- **Camadas MVC**

---

## ⚙️ Como Executar o Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/lucas-gitirana/pet-love.git
cd pet-love/demo
```

### 2. Compile e rode o projeto

```bash
./mvnw spring-boot:run
```

Ou, se estiver usando uma IDE (como IntelliJ ou Eclipse):

- Importe o projeto como **Maven Project**
- Rode a classe `DemoApplication.java`

### 3. Acesse no navegador

```
http://localhost:8080
```

> A API REST estará disponível em rotas como `/pets`, `/usuarios`, `/solicitacoes`, etc.

---

## 🗃️ Scripts de Banco de Dados

Dentro da pasta `scripts/` você encontrará:

- `limpa-banco.sql`: Limpa os dados da base
- `povoamento-racas-especies.sql`: Insere espécies e raças pré-definidas

---

## Funcionalidades

- Cadastro e login de usuários
- Gerenciamento de pets
- Cadastro de pessoas e funcionários
- Registro de consultas
- Solicitação de adoção
- Autenticação e controle de acesso

---

## 👨‍💻 Autores

Desenvolvido por: 
[Lucas Gitirana](https://github.com/lucas-gitirana); [Erick Augusto Warmling](https://github.com/ErickWarmling); [Marco Antonio Garlini Possamai](https://github.com/MarcoPossamai)
