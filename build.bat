call mvn clean install

docker build -t test:1.0.0 .
docker save -o target/test.tar test:1.0.0
