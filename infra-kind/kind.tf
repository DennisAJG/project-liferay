terraform {
  required_providers {
    null = {
      source  = "hashicorp/null"
      version = "3.2.1"
    }
  }
}

provider "null" {}

resource "null_resource" "create_kind_cluster" {
  provisioner "local-exec" {
    command = <<EOT
      kind create cluster --name kind --config=config.yaml
    EOT
  }
}

output "kubeconfig" {
  value = "Cluster kind criado com sucesso. use o comando kubectl config view para listar o cluster"
}