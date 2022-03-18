FROM postgres
ENV POSTGRES_DB atividade
ENV POSTGRES_USER camila
ENV POSTGRES_PASSWORD 222

COPY create.sql /docker-entrypoint-initdb.d/
COPY insert.sql /docker-entrypoint-initdb.d/