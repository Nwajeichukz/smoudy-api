FROM jenkins/jenkins:lts
USER root

# Install Maven, Git, Docker CLI, curl, and gnupg
RUN apt-get update && apt-get install -y \
    maven \
    git \
    docker.io \
    curl \
    gnupg \
    && rm -rf /var/lib/apt/lists/*

# Setup Docker's official APT repository
RUN mkdir -p /etc/apt/keyrings && \
    curl -fsSL https://download.docker.com/linux/debian/gpg | gpg --dearmor -o /etc/apt/keyrings/docker.gpg && \
    echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/debian $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null

# Install Compose and Buildx
RUN apt-get update && apt-get install -y -o Dpkg::Options::="--force-overwrite" \
    docker-compose-plugin \
    docker-buildx-plugin \
    && rm -rf /var/lib/apt/lists/*

# Add jenkins user to the host docker group (using GID 119 as per your guide)
RUN groupadd -g 119 docker_host && usermod -aG docker_host jenkins

USER jenkins