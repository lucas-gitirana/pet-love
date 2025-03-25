FROM php:8.2-apache

# Instala extensões necessárias
RUN apt-get update && apt-get install -y \
    libpq-dev \
    && docker-php-ext-install pdo pdo_pgsql

# Ativa o mod_rewrite do Apache para URLs amigáveis
RUN a2enmod rewrite

# Define o diretório de trabalho
WORKDIR /var/www/html

# Copia os arquivos do projeto para o contêiner
COPY . .
COPY config/apache.conf /etc/apache2/sites-available/000-default.conf

# Expõe a porta 80
EXPOSE 80

# Define o comando padrão ao iniciar o contêiner
CMD ["apache2-foreground"]