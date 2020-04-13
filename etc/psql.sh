#!/usr/bin/env bash

echo "bloodmoneypwd"
psql -h localhost -U bloodmoney -p 5432 -d bloodmoney
