#!/usr/bin/env bash

ps aux | grep java | grep -v 'JetBrains' | tr -s " " | cut -d" " -f2 | xargs sudo kill -TERM
