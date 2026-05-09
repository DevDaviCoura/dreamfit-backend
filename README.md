# Dream Fit

Projeto completo com frontend React + Vite e backend Java Spring Boot.

## Estrutura

- `frontend`: app React com tela publica, calculadora de IMC, cadastro interativo e dashboard fitness.
- `backend`: API REST Spring Boot com dados em memoria, CORS liberado para o frontend e camadas `controller`, `service`, `model` e `dto`.

## Como rodar

### Backend

```bash
cd backend
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`.

Sem Maven instalado, use o Maven Wrapper:

```bash
cd backend
./mvnw spring-boot:run
```

No Windows:

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

Rotas principais:

- `POST /api/usuarios/cadastrar`: cadastra usuario e retorna o dashboard inicial calculado.
- `POST /api/usuarios/login`: login simples em memoria.
- `GET /api/dashboard/{usuarioId}`: retorna IMC, classificacao, objetivo, meta semanal, treino e dieta.
- `POST /api/avaliacoes/{usuarioId}`: salva avaliacao fisica e calcula percentual de gordura, massa gorda, massa magra e recomendacoes refinadas.
- `GET /api/avaliacoes/{usuarioId}`: lista avaliacoes do usuario.

O cadastro sozinho ja gera recomendacoes basicas. A avaliacao fisica e opcional e apenas melhora as recomendacoes quando preenchida.

## Regras de recomendacao

Sem avaliacao fisica completa, o sistema usa peso, altura, idade, sexo, objetivo, meta de peso, nivel de atividade, experiencia com treino e restricoes para entregar IMC, meta, treino inicial, dieta inicial e cards do dashboard.

Com avaliacao fisica completa, o sistema adiciona percentual de gordura, massa magra, massa gorda, medidas corporais, dobras cutaneas, evolucao corporal e relatorio corporal. Com esses dados, o treino vira uma recomendacao mais especifica em modelo ABC e a dieta passa a ter porcoes sugeridas em gramas.

### Frontend

```bash
cd frontend
npm install
npm run dev
```

O app sobe em `http://localhost:5173`.
