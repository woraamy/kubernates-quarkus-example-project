
build-to-k3d:
    docker images --format json | jq -r .Repository | grep super-duper-waffle- | xargs k3d image import