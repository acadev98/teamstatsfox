#!/bin/bash

# Construir la imagen Docker
docker build -t be-teamstatsfoxs .

# Iniciar los contenedores usando docker-compose
docker-compose up -d