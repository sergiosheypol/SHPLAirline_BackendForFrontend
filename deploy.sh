docker build -t locations-bff:local . && docker run -p 3000:80 --name locations-bff -d locations-bff:local 
