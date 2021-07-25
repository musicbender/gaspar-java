#!/usr/bin/env bash

# set environment variables
source setenv.sh

# login to ECR
aws ecr get-login-password | docker login --username AWS --password-stdin ${GASPAR_AWS_ACCOUNT_ID}.dkr.ecr.${GASPAR_AWS_REGION}.amazonaws.com

# run gaspar
docker compose up -f docker-compose.prod.yml