#!/bin/bash
#
# This creates the local k8s "kind" cluster and does everything that's needed to have everything up and running.
# It does not include running below, this has to be done separately/additionally.
  #  Step  1 `kubectl proxy`
  #  Step  2 `kubectl port-forward svc/keycloak 8180:8080`
# To cleanup what's created by this script run `kind delete cluster`
#
# This script here should be kept in sync with the README regarding running things in local k8s.

# exit on error
set -e

#kind create cluster --config k8s/kind/kind-create-cluster-config.yml
echo "Waiting for cluster stabilizing..."
sleep 5
echo "Installing Ngnix - API Gateway"

#kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml

#kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.7.0/aio/deploy/recommended.yaml

# Create admin user
#kubectl --context kind-kind apply -f k8s/kind/dashboard-adminuser.yaml
# Create ClusterRoleBinding
#kubectl --context kind-kind apply -f k8s/kind/cluster-role-binding.yaml

sleep 10
echo "Waiting for Ngnix stabilizing..."
kubectl wait --namespace ingress-nginx \
  --for=condition=ready pod \
  --selector=app.kubernetes.io/component=controller \
  --timeout=100s
echo "Finished Kind Cluster and Ngnix setup"


echo "== Access the k8s dashboard =="
echo "Run"
echo "$ kubectl proxy &"
echo "Get the admin user token via by running below command"
echo "$ kubectl -n kubernetes-dashboard create token admin-user"
echo "and open 'http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/' to sign into kinD dashboard with the token."
echo ""
