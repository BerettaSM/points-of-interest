# 📍 API de Pontos de Interesse

[![SPRING FRAMEWORK](https://img.shields.io/badge/Spring%20framework-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://github.com/BerettaSM/exemplo-readme/blob/main/LICENSE)
[![JAVA](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://github.com/BerettaSM/exemplo-readme/blob/main/LICENSE) 
![GitHub repo size](https://img.shields.io/github/repo-size/BerettaSM/points-of-interest?style=for-the-badge)

> O projeto foi desenvolvido como uma solução do desafio do [Backend-br](https://github.com/backend-br/desafios/blob/master/points-of-interest/PROBLEM.md).

Projeto desenvolvido para a empresa **XY Inc.**, com o objetivo de fornecer um serviço de localização de **Pontos de Interesse (POIs)** com base em coordenadas GPS.

A aplicação expõe uma API RESTful que permite:
- Cadastrar novos pontos de interesse;
- Listar todos os pontos cadastrados;
- Consultar pontos próximos a uma localização específica, dentro de um raio definido.

## ☕ Tecnologias utilizadas

- Linguagem: Java
- Framework: Spring Boot
- Banco de dados: H2
- API: REST

## 💻 Pré-requisitos

Caso queira rodar este projeto na sua própria máquina, veja os requisitos abaixo:

- Java `21`

## 🚀 Rodando o projeto

Para rodar o projeto, siga estas etapas:

1. Clone o repositório:
```bash
git clone https://github.com/BerettaSM/points-of-interest.git
```

2. Entre na pasta raiz do projeto
```bash
cd points-of-interest/
```

3. Execute o projeto com **Maven**:
```bash
./mvn spring-boot:run
```

## 🔗 Endpoints disponíveis

#### ➕ Cadastrar um novo POI

- Endpoint:
```http
  POST /poi
```

- Request Body (JSON):
```json
    {
        "nome": "Novo Ponto",
        "x": 21,
        "y": 12
    }
```

- Exemplo de requisição:

```bash
curl -XPOST \
    -H "Content-Type: application/json" \
    -H "Accept: application/json" \
    -d '{ "name": "Exemplo", "x": 1, "y": 2 }' \
    http://localhost:8080/poi
```

- Respostas:

> - **201**: POI cadastrado com sucesso.
> - **409**: Retornado quando já existe um POI com o mesmo nome ou o mesmo par de coordenadas (x, y). Isso evita duplicidade no banco.
> - **422**: Falha de validação (nome vazio ou nulo, nome com menos de 3 caracteres, coordenadas nulas).

| Parâmetro | Tipo     | Descrição                                           |
|:----------|:---------|:----------------------------------------------------|
| `name`    | `string` | **Obrigatório**. Nome do Ponto de Interesse         |
| `x`       | `int`    | **Obrigatório**. Coordenada x do Ponto de Interesse |
| `y`       | `int`    | **Obrigatório**. Coordenada y do Ponto de Interesse |

#### 📍 Buscar um POI pela slug

- Endpoint:
```http
  GET /poi/{slug}
```

- Exemplo de requisição:

```bash
curl http://localhost:8080/poi/joalheria
```

- Respostas:

> - **200**: POI obtido com sucesso.
> - **404**: POI não existe.

- Exemplo de resposta:

```json
    {
        "name": "Joalheria",
        "x": 15,
        "y": 12
    }
```

#### 📋 Listar todos os POIs

- Endpoint:
```http
  GET /poi
```

- Exemplo de requisição:

```bash
curl http://localhost:8080/poi
```

- Respostas:

> - **200**: POIs obtidos com sucesso.

- Exemplo de resposta:

```json
{
    "content": [
        {
            "name": "Lanchonete",
            "x": 27,
            "y": 12
        },
        {
            "name": "Posto",
            "x": 31,
            "y": 18
        },
        {
            "name": "Joalheria",
            "x": 15,
            "y": 12
        },
        {
            "name": "Floricultura",
            "x": 19,
            "y": 21
        },
        {
            "name": "Pub",
            "x": 12,
            "y": 8
        },
        {
            "name": "Supermercado",
            "x": 23,
            "y": 6
        },
        {
            "name": "Churrascaria",
            "x": 28,
            "y": 2
        },
        {
            "name": "Exemplo",
            "x": 1,
            "y": 2
        }
    ],
    "page": {
        "size": 20,
        "number": 0,
        "totalElements": 8,
        "totalPages": 1
    }
}
```

#### 📍 Buscar POIs por proximidade

- Endpoint:
```http
  GET /poi/near?x=20&y=10&radius=10
```

- Exemplo de requisição:

```bash
curl 'http://localhost:8080/poi/near?x=20&y=10&radius=10'
```

- Respostas:

> - **200**: POIs obtidos com sucesso.
> - **422**: Coordenadas 'x' ou 'y' não foram passadas.

- Exemplo de resposta:

```json
{
    "page": {
        "size": 20,
        "number": 0,
        "totalElements": 4,
        "totalPages": 1
    },
    "content": [
        {
            "name": "Supermercado",
            "x": 23,
            "y": 6,
            "distance": 5
        },
        {
            "name": "Joalheria",
            "x": 15,
            "y": 12,
            "distance": 5.39
        },
        {
            "name": "Lanchonete",
            "x": 27,
            "y": 12,
            "distance": 7.28
        },
        {
            "name": "Pub",
            "x": 12,
            "y": 8,
            "distance": 8.25
        }
    ],
    "extra": {
        "criteria": {
            "radius": 10,
            "x": 20,
            "y": 10
        }
    }
}
```

| Parâmetro | Tipo     | Descrição                                                       | Valor padrão |
|:----------|:---------|:----------------------------------------------------------------|:-------------|
| `radius`  | `int`    | **Opcional**. Raio                                              | **10**       |
| `x`       | `int`    | **Obrigatório**. Coordenada x                                   | N/A          |
| `y`       | `int`    | **Obrigatório**. Coordenada y                                   | N/A          |
| `page`    | `int`    | **Opcional**. Número da página                                  | **0**        |
| `size`    | `int`    | **Opcional**. Quantidade de POIs por página                     | **20**       |
| `name`    | `string` | **Opcional**. Atributo do POI usado como critério de ordenação  | N/A          |

## 🧮 Lógica de Cálculo da Distância

A distância entre dois pontos (X1, Y1) e (X2, Y2) é calculada usando a fórmula euclidiana:

```cpp
distância = sqrt((X2 - X1)² + (Y2 - Y1)²)
```

Apenas os POIs que estiverem a uma distância menor ou igual ao valor fornecido (radius) serão retornados.

## 📚 Seed inicial do Banco de Dados

- Lanchonete (27, 12)

- Posto (31, 18)

- Joalheria (15, 12)

- Floricultura (19, 21)

- Pub (12, 8)

- Supermercado (23, 6)

- Churrascaria (28, 2)

## 📄 Licença

Este projeto é licenciado sob os termos da [MIT License](LICENSE).
