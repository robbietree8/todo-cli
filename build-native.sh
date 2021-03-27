./gradlew --no-daemon assemble
sdk use java 20.3.0.r11-grl
native-image --no-server -cp build/libs/todo-cli-*-all.jar