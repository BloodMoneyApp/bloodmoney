ps aux | grep java | grep '/opt/jfrog/artifactory/' | tr -s " " | cut -d" " -f2 | xargs sudo kill -TERM
