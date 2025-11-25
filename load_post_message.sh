#!/usr/bin/env bash

hey -n 1000 -c 100 -m POST \
  -H "Content-Type: application/json" \
  -d '{ "message": "hello" }' \
  http://localhost:8080/message
