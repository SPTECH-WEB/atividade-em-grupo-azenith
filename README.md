# Projeto Entrega API

  

API RESTful para gerenciamento de pedidos e cálculo de frete, desenvolvida com Spring Boot.

  

## Tecnologias Utilizadas

  

* Java
* Spring Boot
* Maven
* H2 Database - Base de dados relacional em memória
* JPA/Hibernate - ORM
* Swagger UI - Documentação

  

## Como Clonar o Projeto
```bash

git  clone https://github.com/SPTECH-WEB/atividade-em-grupo-azenith

cd  atividade-em-grupo-azenith

```

  

## Como Rodar o Projeto no IntelliJ IDEA


1.  **Abra o IntelliJ IDEA.**

2. Selecione **"Open"** ou **"Import Project"**.

3. Navegue até a pasta `atividade-em-grupo-azenith` onde você clonou o repositório e selecione a pasta `projetoentrega`.

4. O IntelliJ IDEA deve reconhecer automaticamente que é um projeto Maven. Se não, clique com o botão direito no arquivo `pom.xml` e selecione **"Add as Maven Project"**.

5. Aguarde o IntelliJ IDEA baixar as dependências (pode levar alguns minutos).

6. Localize a classe `ProjetoentregaApplication.java` em `src/main/java/com/azenithsolution/projetoentrega/`.

7. Clique com o botão direito sobre a classe `ProjetoentregaApplication` e selecione **"Run 'ProjetoentregaApplication.main()'"**.

8. O servidor será iniciado, geralmente na porta 8080 (verifique o console para a porta exata).

  

## Endpoints da API

  

A URL base da API é `http://localhost:8080/v1/pedidos` (assumindo que o servidor está rodando localmente na porta 8080).

  

### 1. Criar um Novo Pedido
*  **Método:**  `POST`
*  **Endpoint:**  `/v1/pedidos`
*  **Descrição:** Registra um novo pedido no sistema e calcula o valor total com base no tipo de entrega e peso.
*  **Corpo da Requisição (JSON):**

```json
{
	"cliente": "Nome do Cliente",
	"produto": "Descrição do Produto",
	"peso": 1.5,
	"tipoEntrega": "expressa"  // ou "economica", "externa"
}
```

*  **Exemplo de Resposta (Sucesso - 201 Created):**
```json
{
	"timestamp": "2025-04-21T10:30:00.123456", // Data e hora da resposta
	"status": 201, // Código de status HTTP
	"message": "Created", // Mensagem de status
		"data": { // Dados da resposta (Pedido criado)
			"pedido": {
			"id": 1,
			"cliente": "Nome do Cliente",
			"produto": "Descrição do Produto",
			"peso": 1.5,
			"tipoEntrega": "expressa",
			"valorTotal": 25.50, // Valor calculado do frete + possível valor base
		}
	}
}
```
*  **Exemplo de Resposta (Erro - 404 Not Found - Tipo de entrega inválido):**
```json
{
	"timestamp": "2025-04-21T10:35:00.987654",
	"status": 404,
	"message": "Not Found",
	"data": "Modalidade de entrega não encontrada" 
}

```
### 2. Listar Todos os Pedidos
 
* **Método:**  `GET`
* **Endpoint:**  `/v1/pedidos`
*  **Descrição:** Retorna uma lista com todos os pedidos cadastrados.
*  **Corpo da Requisição:** Nenhum
*  **Exemplo de Resposta (Sucesso - 200 OK):**
```json
{
	"timestamp": "2025-04-21T10:40:00.555555",
	"status": 200,
	"message": "OK",
	"data": [ // Lista de pedidos
	{
		"id": 1,
		"cliente": "Nome do Cliente 1",
		"produto": "Produto A",
		"peso": 1.5,
		"tipoEntrega": "expressa",
		"valorTotal": 25.50
	},
	{
		"id": 2,
		"cliente": "Nome do Cliente 2",
		"produto": "Produto B",
		"peso": 3.0,
		"tipoEntrega": "economica",
		"valorTotal": 18.00
	}

	]

}

```
### 3. Calcular Frete
*  **Método:**  `GET`
*  **Endpoint:**  `/v1/pedidos/frete/{modalidade}`
*  **Descrição:** Calcula o valor do frete com base na modalidade (parte da URL) e no peso (parâmetro de query).
*  **Parâmetros de URL:**
*  `modalidade`: O tipo de entrega (ex: `expressa`, `economica`, `transportadoraExterna`).
*  **Parâmetros de Query:**
*  `peso`: O peso do produto (ex: `?peso=2.5`).
*  **Exemplo de Requisição:**  `GET http://localhost:8080/v1/pedidos/frete/expressa?peso=2.5`
*  **Corpo da Requisição:** Nenhum
*  **Exemplo de Resposta (Sucesso - 200 OK):**

```json
{
	"timestamp": "2025-04-21T10:45:00.111111",
	"status": 200,
	"message": "OK",
	"data": { // Dados do cálculo do frete
	"modalidade": "expressa",
	"peso": 2.5,
	"valor": 37.50  // Valor calculado do frete
	}
}

```  

*  **Exemplo de Resposta (Erro - 404 Not Found - Modalidade inválida):**

```json
{
	"timestamp": "2025-04-21T10:50:00.222222",
	"status": 404,
	"message": "Not Found",
	"data": "Modalidade de entrega não encontrada: modalidade_invalida"
	}
```
 
## Estrutura da Resposta da API (`ApiResponseDTO`)

  

Todas as respostas da API seguem uma estrutura padrão para consistência:

 
```json

{
	"timestamp": "...", // Data e hora em que a resposta foi gerada (LocalDateTime)
	"status": ..., // Código de status HTTP (int)
	"message": "...", // Mensagem descritiva do status (String, ex: "OK", "Created", "Not Found")
	"data": ...  // Os dados reais da resposta (pode ser um objeto, uma lista ou uma mensagem de erro em caso de falha)
}

```

Isso facilita o tratamento das respostas no lado do cliente, pois sempre haverá um `timestamp`, `status` e `message`, e o campo `data` conterá o payload específico daquela requisição.

## Testando com Swagger

Para explorar e testar os endpoints da API de forma interativa, você pode usar a interface do Swagger UI. Após iniciar a aplicação, acesse:

http://localhost:8080/swagger-ui/index.html

(Lembre-se de substituir  `8080`  pela porta correta, caso ela seja diferente na sua execução).
