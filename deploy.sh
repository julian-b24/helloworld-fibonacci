#!/bin/bash

declare -a clients=(swarch@xhgrid18 swarch@xhgrid17)
declare server=swarch@xhgrid19

deploy_clients(){
  echo "Clients deployed in:"
  for client in ${clients[*]}; do
      scp client/build/libs/client.jar "$client":A00365918/tarea3
      echo "$client"
  done
}

deploy_server(){
  scp server/build/libs/server.jar "$server":A00365918/tarea3
  echo
}

gradle build
echo Client and Server jars built
echo .

deploy_clients
deploy_server





