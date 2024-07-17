# Makefile for Docker Compose operations

# Variables
COMPOSE_FILE := docker-compose.yml

# Default target
.DEFAULT_GOAL := help

# Help target
help:
	@echo "Available commands:"
	@echo "  make up      - Start Docker Compose services"
	@echo "  make down    - Stop and remove Docker Compose services"
	@echo "  make restart - Restart Docker Compose services"
	@echo "  make logs    - View logs of all services"

# Start Docker Compose services
up:
	docker-compose -f $(COMPOSE_FILE) up -d  --remove-orphans

# Stop and remove Docker Compose services
down:
	docker-compose -f $(COMPOSE_FILE) down

# Restart Docker Compose services
restart: down up

# View logs of all services
logs:
	docker-compose -f $(COMPOSE_FILE) logs -f

# Ensure that these targets are always executed, even if a file with the same name exists
.PHONY: help up down restart logs