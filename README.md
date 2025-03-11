# drone-management-api

This project deploys the Drone Management API using Kubernetes and Quarkus.

## Setup Kubernetes Cluster with Kind and Ingress

### 1. Create a Kubernetes Cluster using Kind

```shell
kind create cluster
```

### 2. Load Docker Image into Kind

Ensure your Docker image is built:

```shell
./mvnw package -Dquarkus.container-image.build=true
```

Then load the image into the Kind cluster:

```shell
kind load docker-image drone-management-api/drone-management-api:1.0-SNAPSHOT
```

### 3. Apply Kubernetes Configurations

Apply the necessary Kubernetes configurations for deploying the application and PostgreSQL:

```shell
kubectl apply -f k8s/skaffold/deployment.yaml
kubectl apply -f k8s/postgres.yaml
```

### 4. Set Up Ingress

Install Ingress Controller:

```shell
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml
```

### 5. Verify Ingress

Verify that the Ingress is running:

```shell
kubectl get pods -n ingress-nginx
kubectl get svc -n ingress-nginx
```

Port-forward the Ingress Controller:

```shell
kubectl port-forward --namespace ingress-nginx service/ingress-nginx-controller 8080:80
```

This will expose the Ingress at `http://localhost:8080`.

---

## Running the Application Locally in Dev Mode

You can run the application in dev mode, which enables live coding, using:

```shell
./mvnw compile quarkus:dev
```

The application will be available at `http://localhost:8080`.

---

## Building and Packaging the Application

To package the application as a JAR, run:

```shell
./mvnw package -Dnet.bytebuddy.experimental=true
```

To create a Docker image, use:

```shell
./mvnw package -Dquarkus.container-image.build=true
```

---

## Native Executable (Optional)

To create a native executable, run:

```shell
./mvnw package -Dnative
```

Alternatively, if GraalVM is not installed, build the native image in a container:

```shell
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

The native executable can be found in `./target/drone-management-api-1.0-SNAPSHOT-runner`.

---

## Notes

- This project uses Quarkus for building the Drone Management API.
- The application can be packaged as either a JAR or a native executable for improved performance.
- Ingress is used to expose the application when running in Kubernetes.

--- 

For more information about Kubernetes, Quarkus, and related configurations, refer to their respective documentation.