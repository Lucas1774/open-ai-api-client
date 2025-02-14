#!/bin/bash
# Compiles into a server.jar file using prod profile, unless next line is commented out
# sed -i 's/active: .*/active: prod/' src/main/resources/application.yml
./mvnw.cmd clean package
if [ -f "openAiApiClient.jar" ]; then
    rm -f openAiApiClient.jar
fi
mv -f target/openAiApiClient-0.0.1-SNAPSHOT.jar openAiApiClient.jar
sed -i 's/active: .*/active: dev/' src/main/resources/application.yml
echo "Press any key to continue."
read -r -n 1
