FROM php:8.2-apache

# Instala extensões necessárias
RUN apt-get update && apt-get install -y \
    libpq-dev \
    libzip-dev \
    unzip \
    && docker-php-ext-install pdo pdo_pgsql

# Instalar o Composer
COPY --from=composer:2.6 /usr/bin/composer /usr/bin/composer

# Ativa o mod_rewrite do Apache para URLs amigáveis
RUN a2enmod rewrite

# Define o diretório de trabalho
WORKDIR /var/www/html

# Copia os arquivos do projeto para o contêiner
COPY . .

# Expõe a porta 80
EXPOSE 80

# Define o comando padrão ao iniciar o contêiner
CMD ["apache2-foreground"]