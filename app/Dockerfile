# Usando a imagem Node.js oficial como base
FROM node:18

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos do projeto para o container
COPY . .

# Instala as dependências
RUN yarn install

# Compila o projeto
RUN yarn build

# Expõe a porta usada pela aplicação
EXPOSE 3000

# Comando para rodar os scripts de inicialização do banco e iniciar o app
CMD yarn typeorm schema:drop -d src/data-source.ts && \
    yarn typeorm schema:sync -d src/data-source.ts && \
    yarn start