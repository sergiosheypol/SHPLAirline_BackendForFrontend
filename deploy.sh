docker build -t shpl-bff:local . && docker run -p 3000:80 --name shpl-bff -d shpl-bff:local 
